package com.example.armstorage.controllers;

import com.example.armstorage.entities.CategoryEntity;
import com.example.armstorage.entities.ItemEntity;
import com.example.armstorage.exceptions.CategoryNotFoundException;
import com.example.armstorage.exceptions.ItemNotFoundException;
import com.example.armstorage.services.StorageService;
import com.example.armstorage.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/storage")
public class StorageController {

    UserService userService;
    StorageService storageService;

    public StorageController(UserService userService, StorageService storageService){
        this.userService = userService;
        this.storageService = storageService;
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryEntity> getCategory(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(storageService.getCategoryById(id));
        }catch(CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CategoryEntity());
        }
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<ItemEntity> getItem(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(storageService.getItemById(id));
        }catch(ItemNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ItemEntity());
        }
    }

    @GetMapping("/category/all")
    public List<CategoryEntity> getAllCategories(){
        return storageService.getAllCategories();
    }

    @GetMapping("/items/all")
    public List<ItemEntity> getAllItems(){
        return storageService.getAllItems();
    }

}