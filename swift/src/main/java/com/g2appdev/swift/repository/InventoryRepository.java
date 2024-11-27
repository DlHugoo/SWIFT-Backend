package com.g2appdev.swift.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g2appdev.swift.entity.InventoryEntity;
import com.g2appdev.swift.entity.ShopEntity;
import com.g2appdev.swift.entity.UserEntity;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Integer> {
    InventoryEntity findByInventoryId(int inventoryId);

    List<InventoryEntity> findByItem(ShopEntity item);

    List<InventoryEntity> findByUser(UserEntity user);

    List<InventoryEntity> findByItemAndUser(ShopEntity item, UserEntity user);

    boolean existsByUserAndItem_ItemName(UserEntity user, String itemName);
}
