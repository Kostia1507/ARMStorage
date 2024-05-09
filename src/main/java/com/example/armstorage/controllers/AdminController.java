package com.example.armstorage.controllers;

import com.example.armstorage.dto.CreateItemRequest;
import com.example.armstorage.dto.CreateStorageRequest;
import com.example.armstorage.dto.CreateUserRequest;
import com.example.armstorage.entities.CategoryEntity;
import com.example.armstorage.entities.ItemEntity;
import com.example.armstorage.entities.StorageEntity;
import com.example.armstorage.entities.UserEntity;
import com.example.armstorage.exceptions.CategoryNotFoundException;
import com.example.armstorage.exceptions.InvalidRequestDataException;
import com.example.armstorage.services.StorageService;
import com.example.armstorage.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/admin")
public class AdminController {

    private final UserService userService;
    private final StorageService storageService;

    public AdminController(UserService userService, StorageService storageService){
        this.userService = userService;
        this.storageService = storageService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody CreateUserRequest request){
        try{
            UserEntity newUser = userService.register(request);
            return ResponseEntity.ok().body(newUser);
        }catch(InvalidRequestDataException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserEntity());
        }
    }

    @GetMapping("/category/create/{name}")
    public ResponseEntity<CategoryEntity> createCategory(@PathVariable String name){
        return ResponseEntity.ok().body(storageService.createCategory(name));
    }

    @PostMapping("/item/create")
    public ResponseEntity<ItemEntity> createItem(@RequestBody CreateItemRequest request){
        try{
            return ResponseEntity.ok().body(storageService.createItem(request));
        }catch(CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ItemEntity());
        }
    }

    @PostMapping("/create-storage")
    public ResponseEntity<StorageEntity> createStorage(@RequestBody CreateStorageRequest request){
        return ResponseEntity.ok().body(storageService.createStorage(request));
    }
}
