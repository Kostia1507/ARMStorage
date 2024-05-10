package com.example.armstorage.services.impl;

import com.example.armstorage.dto.CreateUserRequest;
import com.example.armstorage.dto.EditUserRequest;
import com.example.armstorage.dto.UserLoginRequest;
import com.example.armstorage.entities.RoleEntity;
import com.example.armstorage.entities.UserEntity;
import com.example.armstorage.exceptions.InvalidRequestDataException;
import com.example.armstorage.exceptions.RoleNotFoundException;
import com.example.armstorage.exceptions.UserNotFoundException;
import com.example.armstorage.repositories.RoleRepository;
import com.example.armstorage.repositories.UserRepository;
import com.example.armstorage.security.jwt.JWTProvider;
import com.example.armstorage.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.armstorage.security.jwt.JWTType.ACCESS;
import static com.example.armstorage.security.jwt.JWTType.REFRESH;
import static java.lang.String.format;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;

    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTProvider jwtProvider, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserEntity> getAllUsers(){
        return userRepository.findAll();
    }


    @Override
    public UserEntity findUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(format("user with id:%d not found", id)));
    }

    @Override
    public UserEntity findUserByLogin(String login) throws UserNotFoundException {
        return userRepository.findUserEntityByLogin(login)
                .orElseThrow(() -> new UserNotFoundException(format("user with login:%s not found", login)));
    }

    @Override
    public Map<String, String> refreshTokens(String oldRefreshToken) throws InvalidRequestDataException {
        log.info("IN userService -> refreshTokens()");

        UserEntity user = userRepository.findUserEntityByRefreshToken(oldRefreshToken)
                .orElseThrow(() -> new InvalidRequestDataException("refresh token was not found"));

        //Update token
        Map<String, String> tokens = generatesTokens(user.getLogin());
        user.setRefreshToken(tokens.get("refreshToken"));
        userRepository.save(user);
        return tokens;
    }

    @Override
    public Map<String, String> login(UserLoginRequest request) throws UserNotFoundException, InvalidRequestDataException {
        log.info("IN UserService -> login()");

        UserEntity user = userRepository.findUserEntityByLogin(request.getLogin())
                .orElseThrow(() -> new UserNotFoundException("User is not registered"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidRequestDataException("Wrong password");
        }

        //update refresh token
        Map<String, String> tokens = generatesTokens(user.getLogin());
        user.setRefreshToken(tokens.get("refreshToken"));
        userRepository.save(user);
        return tokens;
    }

    private Map<String, String> generatesTokens(String login) {
        log.info("IN UserService -> generatesTokens(): gen tokens for user[login:{}]", login);

        String accessToken = jwtProvider.generateToken(ACCESS, login);
        String accessExpiration = jwtProvider.getExpirationDate(accessToken, ACCESS).toString();
        String refreshToken = jwtProvider.generateToken(REFRESH, login);
        String refreshExpiration = jwtProvider.getExpirationDate(refreshToken, REFRESH).toString();

        return Map.of(
                "accessToken", accessToken,
                "accessTokenExpiration", accessExpiration.substring(0, accessExpiration.indexOf("[")),
                "refreshToken", refreshToken,
                "refreshTokenExpiration", refreshExpiration.substring(0, refreshExpiration.indexOf("["))
        );
    }

    @Override
    public List<RoleEntity> getAllRoles(){
        return roleRepository.findAll();
    }

    @Override
    public UserEntity register(CreateUserRequest request) throws InvalidRequestDataException {
        log.info("register()");
        Optional<UserEntity> user = userRepository.findUserEntityByLogin(request.getLogin());
        if (user.isPresent()){
            throw new InvalidRequestDataException("Such login already exists");
        }

        RoleEntity role = roleRepository.findRoleEntityByName(request.getRole()).orElseThrow(
                () -> new InvalidRequestDataException("role not found"));

        UserEntity userEntity = UserEntity.builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullname(request.getFullname())
                .role(role)
                .build();
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity editUser(EditUserRequest request) throws UserNotFoundException, RoleNotFoundException{
        UserEntity user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new UserNotFoundException("user not found"));
        RoleEntity role = roleRepository.findRoleEntityByName(request.getRole()).orElseThrow(
                () -> new RoleNotFoundException("role not found"));

        user.setFullname(request.getFullname());
        user.setLogin(request.getLogin());
        user.setRole(role);
        userRepository.save(user);
        return user;
    }
}
