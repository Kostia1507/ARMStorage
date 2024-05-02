package com.example.armstorage.services;

import com.example.armstorage.entities.CategoryEntity;
import com.example.armstorage.entities.ItemEntity;
import com.example.armstorage.entities.StorageEntity;
import com.example.armstorage.exceptions.CategoryNotFoundException;
import com.example.armstorage.exceptions.ItemNotFoundException;
import com.example.armstorage.exceptions.StorageNotFoundException;

public interface StorageService {
    StorageEntity getStorageById(Long id) throws StorageNotFoundException;

    CategoryEntity getCategoryById(Long id) throws CategoryNotFoundException;

    ItemEntity getItemById(Long id) throws ItemNotFoundException;

    CategoryEntity createCategory(String name);
}
