package com.example.armstorage.services;

import com.example.armstorage.dto.CreateItemRequest;
import com.example.armstorage.dto.CreateStorageRequest;
import com.example.armstorage.entities.CategoryEntity;
import com.example.armstorage.entities.ItemEntity;
import com.example.armstorage.entities.StorageEntity;
import com.example.armstorage.exceptions.CategoryNotFoundException;
import com.example.armstorage.exceptions.ItemNotFoundException;
import com.example.armstorage.exceptions.StorageNotFoundException;

import java.util.List;

public interface StorageService {
    StorageEntity getStorageById(Long id) throws StorageNotFoundException;

    CategoryEntity getCategoryById(Long id) throws CategoryNotFoundException;

    ItemEntity getItemById(Long id) throws ItemNotFoundException;

    CategoryEntity createCategory(String name);

    List<CategoryEntity> getAllCategories();

    ItemEntity createItem(CreateItemRequest request) throws CategoryNotFoundException;

    StorageEntity createStorage(CreateStorageRequest request);
}
