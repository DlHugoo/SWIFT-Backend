package com.g2appdev.swift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g2appdev.swift.entity.ShopEntity;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity, Integer> {
	public ShopRepository findByitemName(String itemName);
}
