package com.example.armstorage.services.impl;

import com.example.armstorage.dto.CreateItemRequest;
import com.example.armstorage.dto.CreateStorageRequest;
import com.example.armstorage.entities.CategoryEntity;
import com.example.armstorage.entities.ItemEntity;
import com.example.armstorage.entities.StorageEntity;
import com.example.armstorage.exceptions.CategoryNotFoundException;
import com.example.armstorage.exceptions.ItemNotFoundException;
import com.example.armstorage.exceptions.StorageNotFoundException;
import com.example.armstorage.repositories.CategoriesRepository;
import com.example.armstorage.repositories.ItemRepository;
import com.example.armstorage.repositories.OperationRepository;
import com.example.armstorage.repositories.StorageRepository;
import com.example.armstorage.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    private final CategoriesRepository categoriesRepository;
    private final ItemRepository itemRepository;
    private final OperationRepository operationRepository;
    private final StorageRepository storageRepository;

    public StorageServiceImpl(CategoriesRepository categoriesRepository, ItemRepository itemRepository, OperationRepository operationRepository, StorageRepository storageRepository) {
        this.categoriesRepository = categoriesRepository;
        this.itemRepository = itemRepository;
        this.operationRepository = operationRepository;
        this.storageRepository = storageRepository;
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
    public StorageEntity createStorage(CreateStorageRequest request){
        StorageEntity storageEntity = StorageEntity.builder()
                .name(request.getName())
                .cell_numbers(request.getCellNumber()).build();
        storageRepository.save(storageEntity);
        return storageEntity;
    }



}
