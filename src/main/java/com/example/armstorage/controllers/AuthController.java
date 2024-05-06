package com.example.armstorage.controllers;

import com.example.armstorage.dto.UserLoginRequest;
import com.example.armstorage.entities.UserEntity;
import com.example.armstorage.exceptions.InvalidRequestDataException;
import com.example.armstorage.exceptions.UserNotFoundException;
import com.example.armstorage.services.UserService;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class AuthController {

    UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public boolean mainPage(@RequestBody UserLoginRequest request, HttpSession session){
        try{
            Map<String, String> responseBody = new HashMap<>(userService.login(request));
            session.setAttribute("token", responseBody.get("accessToken"));
            UserEntity user = userService.findUserByLogin(request.getLogin());
            session.setAttribute("fullname", user.getFullname());
            return true;
        }catch(InvalidRequestDataException | UserNotFoundException e){
            return false;
        }
    }
}