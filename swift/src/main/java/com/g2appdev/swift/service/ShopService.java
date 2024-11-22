package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.InventoryEntity;
import com.g2appdev.swift.entity.ShopEntity;
import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.repository.InventoryRepository;
import com.g2appdev.swift.repository.ShopRepository;

@Service
public class ShopService {
	@Autowired
	private ShopRepository srepo; // Repository for ShopEntity

	@Autowired
	private InventoryRepository irepo; // Repository for InventoryEntity

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

	public void createDefaultShopForInventory(InventoryEntity inventory) {
		// Retrieve the user associated with the inventory
		UserEntity user = inventory.getUser();
		if (user == null) {
			throw new IllegalArgumentException("Inventory must have an associated user.");
		}

		// Check if specific items are already associated with this inventory
		List<InventoryEntity> existingInventory = irepo.findByUser(user);

		boolean hasOfficeRoom = existingInventory.stream()
				.anyMatch(inv -> inv.getItem().getItemName().equals("Office Room"));
		boolean hasFarm = existingInventory.stream()
				.anyMatch(inv -> inv.getItem().getItemName().equals("Farm"));
		boolean hasPinkRoom = existingInventory.stream()
				.anyMatch(inv -> inv.getItem().getItemName().equals("Pink Room"));
		boolean hasPurpleRoom = existingInventory.stream()
				.anyMatch(inv -> inv.getItem().getItemName().equals("Purple Room"));

		// Add missing items to the inventory
		if (!hasOfficeRoom) {
			InventoryEntity officeInventory = new InventoryEntity();
			officeInventory.setUser(user);

			ShopEntity officeRoom = new ShopEntity();
			officeRoom.setItemName("Office Room");
			officeRoom.setItemCost(200);
			officeRoom.setItemUrl("2.png");
			officeInventory.setItem(officeRoom);

			irepo.save(officeInventory);
		}

		if (!hasFarm) {
			InventoryEntity farmInventory = new InventoryEntity();
			farmInventory.setUser(user);

			ShopEntity farm = new ShopEntity();
			farm.setItemName("Farm");
			farm.setItemCost(200);
			farm.setItemUrl("3.png");
			farmInventory.setItem(farm);

			irepo.save(farmInventory);
		}

		if (!hasPinkRoom) {
			InventoryEntity pinkRoomInventory = new InventoryEntity();
			pinkRoomInventory.setUser(user);

			ShopEntity pinkRoom = new ShopEntity();
			pinkRoom.setItemName("Pink Room");
			pinkRoom.setItemCost(200);
			pinkRoom.setItemUrl("4.png");
			pinkRoomInventory.setItem(pinkRoom);

			irepo.save(pinkRoomInventory);
		}

		if (!hasPurpleRoom) {
			InventoryEntity purpleRoomInventory = new InventoryEntity();
			purpleRoomInventory.setUser(user);

			ShopEntity purpleRoom = new ShopEntity();
			purpleRoom.setItemName("Purple Room");
			purpleRoom.setItemCost(200);
			purpleRoom.setItemUrl("5.png");
			purpleRoomInventory.setItem(purpleRoom);

			irepo.save(purpleRoomInventory);
		}
	}
}