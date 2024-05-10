package com.example.armstorage.services;

import com.example.armstorage.dto.CreateUserRequest;
import com.example.armstorage.dto.EditUserRequest;
import com.example.armstorage.dto.UserLoginRequest;
import com.example.armstorage.entities.RoleEntity;
import com.example.armstorage.entities.UserEntity;
import com.example.armstorage.exceptions.InvalidRequestDataException;
import com.example.armstorage.exceptions.RoleNotFoundException;
import com.example.armstorage.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Map;

public interface UserService{
    List<UserEntity> getAllUsers();

    UserEntity findUserById(Long id) throws UserNotFoundException;
    UserEntity findUserByLogin(String login) throws UserNotFoundException;

    Map<String, String> refreshTokens(String oldRefreshToken) throws InvalidRequestDataException;

    Map<String, String> login(UserLoginRequest request) throws InvalidRequestDataException, UserNotFoundException;

    List<RoleEntity> getAllRoles();

    UserEntity register(CreateUserRequest request) throws InvalidRequestDataException;

    UserEntity editUser(EditUserRequest request) throws UserNotFoundException, RoleNotFoundException;
}
