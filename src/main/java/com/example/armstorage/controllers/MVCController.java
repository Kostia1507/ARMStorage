package com.example.armstorage.controllers;

import com.example.armstorage.dto.UserLoginRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MVCController {
    @GetMapping("/")
    public String index(Model model) {
        return "login";
    }

    @GetMapping("/main")
    public String mainPage(){
        return "main";
    }
}
