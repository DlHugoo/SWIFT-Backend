package com.g2appdev.swift.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.InventoryEntity;
import com.g2appdev.swift.entity.ShopEntity;
import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.repository.InventoryRepository;
import com.g2appdev.swift.repository.ShopRepository;

@Service
public class InventoryService {
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private ShopRepository shopRepository;

	public InventoryEntity getInventoryByUser(UserEntity user) {
		return inventoryRepository.findByUser(user)
				.orElseGet(() -> createNewInventoryForUser(user));
	}

	public InventoryEntity getInventoryById(int id) {
		Optional<InventoryEntity> optionalInventory = inventoryRepository.findById(id);
		return optionalInventory.orElseThrow(() -> new RuntimeException("Inventory not found with ID: " + id));
	}

	public InventoryEntity createInventory(InventoryEntity inventory) {
		return inventoryRepository.save(inventory);
	}

	public InventoryEntity updateInventory(int id, InventoryEntity inventory) {
		InventoryEntity existingInventory = getInventoryById(id);
		existingInventory.setTotalCoins(inventory.getTotalCoins());
		existingInventory.setItemList(inventory.getItemList());
		return inventoryRepository.save(existingInventory);
	}

	public void deleteInventory(int id) {
		inventoryRepository.deleteById(id);
	}

	public InventoryEntity addItemToInventory(InventoryEntity inventory, ShopEntity item) {
		inventory.getItems().add(item);
		inventory.setTotalCoins(inventory.getTotalCoins() - item.getItemCost());
		return inventoryRepository.save(inventory);
	}

	private InventoryEntity createNewInventoryForUser(UserEntity user) {
		InventoryEntity newInventory = new InventoryEntity();
		newInventory.setUser(user);
		return inventoryRepository.save(newInventory);
	}

	public List<InventoryEntity> findAll() {
		return inventoryRepository.findAll();
	}

}
