package com.example.armstorage.services.impl;

import com.example.armstorage.dto.*;
import com.example.armstorage.entities.*;
import com.example.armstorage.exceptions.CategoryNotFoundException;
import com.example.armstorage.exceptions.ItemNotFoundException;
import com.example.armstorage.exceptions.StorageNotFoundException;
import com.example.armstorage.exceptions.UserNotFoundException;
import com.example.armstorage.repositories.*;
import com.example.armstorage.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    private final CategoriesRepository categoriesRepository;
    private final ItemRepository itemRepository;
    private final OperationRepository operationRepository;
    private final StorageRepository storageRepository;
    private final ItemsInStorageRepository itemsInStorageRepository;

    private final UserRepository userRepository;

    public StorageServiceImpl(CategoriesRepository categoriesRepository, ItemRepository itemRepository, OperationRepository operationRepository, StorageRepository storageRepository, ItemsInStorageRepository itemsInStorageRepository, UserRepository userRepository) {
        this.categoriesRepository = categoriesRepository;
        this.itemRepository = itemRepository;
        this.operationRepository = operationRepository;
        this.storageRepository = storageRepository;
        this.itemsInStorageRepository = itemsInStorageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public StorageEntity getStorageById(Long id) throws StorageNotFoundException{
        return storageRepository.findById(id).orElseThrow(() -> new StorageNotFoundException("Складу не існує"));
    }

    @Override
    public CategoryEntity getCategoryById(Long id) throws CategoryNotFoundException {
        return categoriesRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Категорії не існує"));
    }

    @Override
    public ItemEntity getItemById(Long id) throws ItemNotFoundException {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Предмету не існує"));
    }

    @Override
    public CategoryEntity createCategory(String name){
        CategoryEntity category = CategoryEntity.builder().name(name).build();
        return categoriesRepository.save(category);
    }

    @Override
    public List<CategoryEntity> getAllCategories(){
        return categoriesRepository.findAll();
    }

    @Override
    public List<ItemEntity> getAllItems(){
        return itemRepository.findAll();
    }

    @Override
    public ItemEntity createItem(CreateItemRequest request) throws CategoryNotFoundException {
        CategoryEntity category = getCategoryById(request.getCategoryId());
        ItemEntity item = ItemEntity.builder()
                .category(category)
                .name(request.getName())
                .measurement(request.getMeasurement())
                .price(request.getPrice()).build();
        itemRepository.save(item);
        return item;
    }

    @Override
    public ItemEntity editItem(EditItemRequest request) throws ItemNotFoundException, CategoryNotFoundException {
        CategoryEntity category = getCategoryById(request.getCategoryId());
        ItemEntity item = getItemById(request.getItemId());
        System.out.println("id " + item.getId());
        item.setCategory(category);
        item.setPrice(request.getPrice());
        item.setName(request.getName());
        item.setMeasurement(request.getMeasurement());
        itemRepository.save(item);
        System.out.println("id " + item.getId());
        return item;
    }

    @Override
    public StorageEntity createStorage(CreateStorageRequest request){
        StorageEntity storageEntity = StorageEntity.builder()
                .name(request.getName())
                .cell_numbers(request.getCellNumber()).build();
        storageRepository.save(storageEntity);
        return storageEntity;
    }

    @Override
    public List<StorageEntity> getAllStorages(){
        return storageRepository.findAll();
    }
    @Override
    public Set<StorageEntity> getAllUserStorages(Long userId){
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        if(userEntityOptional.isEmpty()){
            return Collections.emptySet();
        }
        UserEntity user = userEntityOptional.get();
        return user.getStorages();
    }

    @Override
    public boolean editUserInStorage(EditUserInStorageRequest request, boolean isAdd)
            throws UserNotFoundException, StorageNotFoundException {
        UserEntity user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new UserNotFoundException("user not found"));
        StorageEntity storage = storageRepository.findById(request.getStorageId()).orElseThrow(
                () -> new StorageNotFoundException("storage not found"));
        Set<StorageEntity> storages = user.getStorages();
        if(isAdd){
            storages.add(storage);
        }else{
            storages.remove(storage);
        }
        user.setStorages(storages);
        userRepository.save(user);
        return true;
    }


    @Override
    public boolean addItemToStorage(AddItemToStorageRequest request)
            throws StorageNotFoundException, ItemNotFoundException {
        StorageEntity storageEntity = storageRepository.findById(request.getStorageId()).orElseThrow(() ->
                (new StorageNotFoundException("storage not found")));
        ItemEntity itemEntity = itemRepository.findById(request.getItemId()).orElseThrow(() ->
                (new ItemNotFoundException("item not found")));
        ItemsInStorageEntity currentItemInCell = null;
        List<ItemsInStorageEntity> itemsInStorage = storageEntity.getItemsInStorage();
        for(ItemsInStorageEntity item : itemsInStorage){
            if(item.getCell().equals(request.getCell())){
                currentItemInCell = item;
            }
        }
        Long currentCount = 0L;
        if(currentItemInCell != null){
            if(currentItemInCell.getItem().getId().equals(request.getItemId())){
                currentCount = currentItemInCell.getCount();
            }else{
                throw new ItemNotFoundException("Cell occupied with another item");
            }
        }

        ItemsInStorageEntity itemsInStorageEntity = ItemsInStorageEntity.builder()
                .storage(storageEntity)
                .item(itemEntity)
                .count(request.getCount()+currentCount)
                .cell(request.getCell()).build();
        itemsInStorageRepository.save(itemsInStorageEntity);
        return true;
    }



}
