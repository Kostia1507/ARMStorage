package com.example.armstorage.repositories;

import com.example.armstorage.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByLogin(String login);

    Optional<UserEntity> findUserEntityByRefreshToken(String refreshToken);
}