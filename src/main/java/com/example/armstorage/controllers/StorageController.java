package com.example.armstorage.controllers;

import com.example.armstorage.dto.UserLoginRequest;
import com.example.armstorage.entities.CategoryEntity;
import com.example.armstorage.exceptions.CategoryNotFoundException;
import com.example.armstorage.exceptions.InvalidRequestDataException;
import com.example.armstorage.exceptions.UserNotFoundException;
import com.example.armstorage.services.StorageService;
import com.example.armstorage.services.UserService;
import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/storage")
public class StorageController {

    UserService userService;
    StorageService storageService;

    public StorageController(UserService userService, StorageService storageService){
        this.userService = userService;
        this.storageService = storageService;
    }

    @GetMapping("/category/create/{name}")
    public ResponseEntity<CategoryEntity> createCategory(@PathVariable String name){
        return ResponseEntity.ok().body(storageService.createCategory(name));
    }

    @GetMapping("/category/create/{id}")
    public ResponseEntity<CategoryEntity> getCategory(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(storageService.getCategoryById(id));
        }catch(CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CategoryEntity());
        }
    }

}