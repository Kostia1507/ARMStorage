package com.example.armstorage.controllers;

import com.example.armstorage.dto.AddItemToStorageRequest;
import com.example.armstorage.dto.CategoryWithItemsResponse;
import com.example.armstorage.dto.FoundItemResponse;
import com.example.armstorage.dto.MoveItemRequest;
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

import java.util.ArrayList;
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

    @PostMapping("/remove-item")
    public ResponseEntity<String> removeItemFromStorage(@RequestBody AddItemToStorageRequest request){
        try{
            return ResponseEntity.ok().body(String.valueOf(storageService.removeItemToStorage(request, 0)));
        }catch(ItemNotFoundException | StorageNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/give-item")
    public ResponseEntity<String> giveItemFromStorage(@RequestBody AddItemToStorageRequest request){
        try{
            return ResponseEntity.ok().body(String.valueOf(storageService.removeItemToStorage(request, 1)));
        }catch(ItemNotFoundException | StorageNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/move-item")
    public ResponseEntity<String> moveItem(@RequestBody MoveItemRequest request){
        try{
            return ResponseEntity.ok().body(String.valueOf(storageService.moveItem(request)));
        }catch(ItemNotFoundException | StorageNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/items/available")
    public Set<ItemEntity> getAvailableItems(){
        try {
            UserEntity user = userService.findUserById(getUserDetails().getId());
            return storageService.getAllAvailableItems(user, "");
        }catch(UserNotFoundException e){
            return null;
        }
    }

    @GetMapping("/items/available/{loop}")
    public Set<ItemEntity> getAvailableItems(@PathVariable String loop){
        try {
            UserEntity user = userService.findUserById(getUserDetails().getId());
            return storageService.getAllAvailableItems(user, loop);
        }catch(UserNotFoundException e){
            return null;
        }
    }


    @GetMapping("/items/all/{id}")
    public List<ItemsInStorageEntity> getAllItemsInStorage(@PathVariable Long id){
        try {
            StorageEntity storageEntity = storageService.getStorageById(id);
            return storageEntity.getItemsInStorage();
        }catch(StorageNotFoundException e){
            return new ArrayList<>();
        }
    }

    // I never tested this end-point
    @GetMapping("/items/bycategory")
    public List<CategoryWithItemsResponse> getAllItemsByCategory(){
        List<CategoryEntity> categoryEntities = storageService.getAllCategories();
        List<CategoryWithItemsResponse> byCategories = new ArrayList<CategoryWithItemsResponse>();
        for(CategoryEntity category : categoryEntities){
            byCategories.add(CategoryWithItemsResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .items(category.getItems())
                    .build());
        }
        return byCategories;
    }

    @GetMapping("/items/found/{itemId}")
    public ResponseEntity<List<FoundItemResponse>> foundItemInStorages(@PathVariable Long itemId){
        ItemEntity item = null;
        try{
            item = storageService.getItemById(itemId);
        }catch(ItemNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
        UserEntity user = null;
        try{
            user = userService.findUserById(getUserDetails().getId());
        }catch(UserNotFoundException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ArrayList<>());
        }
        return ResponseEntity.ok().body(storageService.foundAvailableItems(user, item));
    }
}