package com.example.armstorage.services;

import com.example.armstorage.dto.*;
import com.example.armstorage.entities.*;
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

    List<ItemEntity> getAllItems();

    ItemEntity createItem(CreateItemRequest request) throws CategoryNotFoundException;

    ItemEntity editItem(EditItemRequest request) throws ItemNotFoundException, CategoryNotFoundException;

    StorageEntity createStorage(CreateStorageRequest request);

    List<StorageEntity> getAllStorages();


    Set<StorageEntity> getAllUserStorages(Long userId);

    boolean editUserInStorage(EditUserInStorageRequest request, boolean isAdd)
            throws UserNotFoundException, StorageNotFoundException;

    boolean addItemToStorage(AddItemToStorageRequest request)
            throws StorageNotFoundException, ItemNotFoundException;

    Set<ItemEntity> getAllAvailableItems(UserEntity user);

    boolean removeItemToStorage(AddItemToStorageRequest request, int action)
            throws StorageNotFoundException, ItemNotFoundException;

    List<FoundItemResponse> foundAvailableItems(UserEntity user, ItemEntity item);
}
