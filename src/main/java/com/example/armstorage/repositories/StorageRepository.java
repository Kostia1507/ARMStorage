package com.example.armstorage.repositories;

import com.example.armstorage.entities.StorageEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<StorageEntity, Long> {
    Optional<StorageRepository> findUserEntityByName(String name);

}