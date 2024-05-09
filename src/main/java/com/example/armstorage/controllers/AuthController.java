package com.example.armstorage.controllers;

import com.example.armstorage.dto.CreateUserRequest;
import com.example.armstorage.dto.UserLoginRequest;
import com.example.armstorage.entities.RoleEntity;
import com.example.armstorage.entities.UserEntity;
import com.example.armstorage.exceptions.InvalidRequestDataException;
import com.example.armstorage.exceptions.UserNotFoundException;
import com.example.armstorage.services.UserService;
import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class AuthController {

    UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public boolean login(@RequestBody UserLoginRequest request, HttpSession session){
        try{
            Map<String, String> responseBody = new HashMap<>(userService.login(request));
            session.setAttribute("token", responseBody.get("accessToken"));
            UserEntity user = userService.findUserByLogin(request.getLogin());
            session.setAttribute("role", user.getRole().getName());
            session.setAttribute("roleLocale", user.getRole().getLocale());
            session.setAttribute("fullname", user.getFullname());
            return true;
        }catch(InvalidRequestDataException | UserNotFoundException e){
            return false;
        }
    }

    @GetMapping("/roles")
    public List<RoleEntity> getAllRoles(){
        return userService.getAllRoles();
    }


}