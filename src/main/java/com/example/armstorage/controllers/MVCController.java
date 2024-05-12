package com.example.armstorage.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        model.addAttribute("token", session.getAttribute("token"));
        return "createitem";
    }

    @GetMapping("/edititem")
    public String editItemPage(Model model, HttpSession session){
        model.addAttribute("token", session.getAttribute("token"));
        return "edititem";
    }

    @GetMapping("/createcategory")
    public String createCategoryPage(Model model, HttpSession session){
        model.addAttribute("token", session.getAttribute("token"));
        return "createcategory";
    }

    @GetMapping("/createstorage")
    public String createStoragePage(Model model, HttpSession session){
        model.addAttribute("token", session.getAttribute("token"));
        return "createstorage";
    }

    @GetMapping("/adminpage")
    public String adminPage(Model model, HttpSession session){
        model.addAttribute("token", session.getAttribute("token"));
        return "admin";
    }

    @GetMapping("/reguser")
    public String regUserPage(Model model, HttpSession session){
        model.addAttribute("token", session.getAttribute("token"));
        return "reguser";
    }

    @GetMapping("/edit-user/{userId}")
    public String regUserPage(Model model, HttpSession session, @PathVariable Long userId){
        model.addAttribute("token", session.getAttribute("token"));
        model.addAttribute("userId", userId);
        return "useredit";
    }

    @GetMapping("/adduser/{userId}")
    public String addUserPage(Model model, HttpSession session, @PathVariable Long userId){
        model.addAttribute("token", session.getAttribute("token"));
        model.addAttribute("userId", userId);
        return "adduser";
    }

    @GetMapping("/removeuser/{userId}")
    public String removeUserPage(Model model, HttpSession session, @PathVariable Long userId){
        model.addAttribute("token", session.getAttribute("token"));
        model.addAttribute("userId", userId);
        return "removeuser";
    }

    @GetMapping("/additem")
    public String addItemPage(Model model, HttpSession session){
        model.addAttribute("token", session.getAttribute("token"));
        return "additemtostorage";
    }

    @GetMapping("/removeitem")
    public String removeItemPage(Model model, HttpSession session){
        model.addAttribute("token", session.getAttribute("token"));
        return "removeitemfromstorage";
    }

}
