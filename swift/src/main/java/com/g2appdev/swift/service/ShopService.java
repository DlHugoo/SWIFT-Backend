package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.ShopEntity;

import com.g2appdev.swift.repository.ShopRepository;

@Service
public class ShopService {
	@Autowired
	private ShopRepository srepo; // Repository for ShopEntity

	// Repository for InventoryEntity

	public ShopService() {
		super();
	}

	public ShopEntity postShopRecord(ShopEntity shop) {
		return srepo.save(shop);
	}

	public List<ShopEntity> getAllShop() {
		return srepo.findAll();
	}

	public ShopEntity putShopDetails(int itemId, ShopEntity newShopDetails) {
		return srepo.findById(itemId).map(shop -> {
			shop.setItemName(newShopDetails.getItemName());
			shop.setItemCost(newShopDetails.getItemCost());
			shop.setItemUrl(newShopDetails.getItemUrl());
			return srepo.save(shop);
		}).orElseThrow(() -> new NoSuchElementException("Item not found"));
	}

	public String deleteShop(int itemId) {
		if (srepo.findById(itemId).isPresent()) {
			srepo.deleteById(itemId);
			return "Shop Record successfully deleted!";
		} else {
			return itemId + " Not Found!";
		}
	}

	public void createDefaultShopItems() {
		// Check if predefined items already exist in the shop
		boolean hasOfficeRoom = srepo.existsByItemName("Office Room");
		boolean hasFarm = srepo.existsByItemName("Farm");
		boolean hasPinkRoom = srepo.existsByItemName("Pink Room");
		boolean hasPurpleRoom = srepo.existsByItemName("Purple Room");

		// Add missing shop items
		if (!hasOfficeRoom) {
			ShopEntity officeRoom = new ShopEntity();
			officeRoom.setItemName("Office Room");
			officeRoom.setItemCost(200);
			officeRoom.setItemUrl("2.png");
			srepo.save(officeRoom);
		}

		if (!hasFarm) {
			ShopEntity farm = new ShopEntity();
			farm.setItemName("Farm");
			farm.setItemCost(200);
			farm.setItemUrl("3.png");
			srepo.save(farm);
		}

		if (!hasPinkRoom) {
			ShopEntity pinkRoom = new ShopEntity();
			pinkRoom.setItemName("Pink Room");
			pinkRoom.setItemCost(200);
			pinkRoom.setItemUrl("4.png");
			srepo.save(pinkRoom);
		}

		if (!hasPurpleRoom) {
			ShopEntity purpleRoom = new ShopEntity();
			purpleRoom.setItemName("Purple Room");
			purpleRoom.setItemCost(200);
			purpleRoom.setItemUrl("5.png");
			srepo.save(purpleRoom);
		}
	}
}