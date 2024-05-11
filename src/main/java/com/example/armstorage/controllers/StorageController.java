package com.example.armstorage.controllers;

import com.example.armstorage.dto.AddItemToStorageRequest;
import com.example.armstorage.entities.*;
import com.example.armstorage.exceptions.CategoryNotFoundException;
import com.example.armstorage.exceptions.ItemNotFoundException;
import com.example.armstorage.exceptions.StorageNotFoundException;
import com.example.armstorage.exceptions.UserNotFoundException;
import com.example.armstorage.security.jwt.UserDetailsImpl;
import com.example.armstorage.services.StorageService;
import com.example.armstorage.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;


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

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long userId){
        try{
            return ResponseEntity.ok().body(userService.findUserById(userId));
        }catch(UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserEntity());
        }
    }

    @GetMapping("/mystorages")
    public ResponseEntity<Set<StorageEntity>> getMyStorages(){
        return ResponseEntity.ok().body(storageService.getAllUserStorages(getUserDetails().getId()));
    }

    @GetMapping("/storage/{id}")
    public ResponseEntity<StorageEntity> getStorageById(@PathVariable Long id){
        try{
            return ResponseEntity.ok().body(storageService.getStorageById(id));
        }catch(StorageNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StorageEntity());
        }
    }

    @GetMapping("/category/items/{id}")
    public ResponseEntity<Set<ItemEntity>> getItemsFromCategory(@PathVariable Long id){
        try{
            CategoryEntity category = storageService.getCategoryById(id);
            return ResponseEntity.ok().body(category.getItems());
        }catch(CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptySet());
        }
    }

    public UserDetailsImpl getUserDetails(){
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("/add-item")
    public ResponseEntity<String> addItemToStorage(@RequestBody AddItemToStorageRequest request){
        try{
            return ResponseEntity.ok().body(String.valueOf(storageService.addItemToStorage(request)));
        }catch(ItemNotFoundException | StorageNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}