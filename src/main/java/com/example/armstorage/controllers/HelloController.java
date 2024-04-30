package com.example.armstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class HelloController {
    @GetMapping("/hello")
    public String greeting(Model model) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        model.addAttribute("now",  dateFormat.format(currentDate));
        return "hello";
    }
}