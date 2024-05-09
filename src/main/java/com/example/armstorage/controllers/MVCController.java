package com.example.armstorage.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/createitem")
    public String createItemPage(Model model, HttpSession session){
        model.addAttribute(session.getAttribute("token"));
        return "createitem";
    }

    @GetMapping("/createcategory")
    public String createCategoryPage(Model model, HttpSession session){
        model.addAttribute(session.getAttribute("token"));
        return "createcategory";
    }

    @GetMapping("/createstorage")
    public String createStoragePage(Model model, HttpSession session){
        model.addAttribute(session.getAttribute("token"));
        return "createstorage";
    }

    @GetMapping("/adminpage")
    public String adminPage(Model model, HttpSession session){
        model.addAttribute(session.getAttribute("token"));
        return "admin";
    }

    @GetMapping("/reguser")
    public String regUserPage(Model model, HttpSession session){
        model.addAttribute(session.getAttribute("token"));
        return "reguser";
    }
}
