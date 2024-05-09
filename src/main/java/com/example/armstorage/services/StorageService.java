package com.example.armstorage.services;

import com.example.armstorage.dto.CreateItemRequest;
import com.example.armstorage.dto.CreateStorageRequest;
import com.example.armstorage.dto.EditUserInStorageRequest;
import com.example.armstorage.entities.CategoryEntity;
import com.example.armstorage.entities.ItemEntity;
import com.example.armstorage.entities.StorageEntity;
import com.example.armstorage.exceptions.CategoryNotFoundException;
import com.example.armstorage.exceptions.ItemNotFoundException;
import com.example.armstorage.exceptions.StorageNotFoundException;
import com.example.armstorage.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Set;

public interface StorageService {
    StorageEntity getStorageById(Long id) throws StorageNotFoundException;

    CategoryEntity getCategoryById(Long id) throws CategoryNotFoundException;

    ItemEntity getItemById(Long id) throws ItemNotFoundException;

    CategoryEntity createCategory(String name);

    List<CategoryEntity> getAllCategories();

    ItemEntity createItem(CreateItemRequest request) throws CategoryNotFoundException;

    StorageEntity createStorage(CreateStorageRequest request);

    List<StorageEntity> getAllStorages();


    Set<StorageEntity> getAllUserStorages(Long userId);

    boolean editUserInStorage(EditUserInStorageRequest request, boolean isAdd)
            throws UserNotFoundException, StorageNotFoundException;
}
