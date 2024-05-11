package com.example.armstorage.repositories;

import com.example.armstorage.entities.ItemsInStorageEntity;
import com.example.armstorage.entities.ItemsInStorageEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsInStorageRepository extends JpaRepository<ItemsInStorageEntity, ItemsInStorageEntityId> {

}