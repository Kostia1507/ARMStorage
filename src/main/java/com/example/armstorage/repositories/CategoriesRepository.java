package com.example.armstorage.repositories;

import com.example.armstorage.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findCategoryEntityByName(String login);

}