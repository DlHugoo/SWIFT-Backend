package com.g2appdev.swift.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g2appdev.swift.entity.InventoryEntity;
import com.g2appdev.swift.entity.UserEntity;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Integer> {
    Optional<InventoryEntity> findByUser(UserEntity user);
}