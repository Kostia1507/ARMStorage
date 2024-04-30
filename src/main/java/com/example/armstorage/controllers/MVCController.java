package com.example.armstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MVCController {
    @GetMapping("/")
    public String index(Model model) {
        return "login";
    }
}
