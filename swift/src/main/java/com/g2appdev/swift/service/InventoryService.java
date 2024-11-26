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
import com.g2appdev.swift.repository.UserRepository;

@Service
public class InventoryService {

	@Autowired
	InventoryRepository irepo;

	@Autowired
	UserRepository urepo;
	@Autowired
	ShopRepository itrepo;

	public InventoryService() {
		super();
	}

	// CREATE Inventory Record
	public InventoryEntity postInventoryRecord(InventoryEntity inventory) {
		if (inventory.getUser() == null || inventory.getUser().getUserID() == 0) {
			throw new IllegalArgumentException("User cannot be null or missing.");
		}

		// Retrieve user and handle potential absence
		UserEntity user = urepo.findById(inventory.getUser().getUserID())
				.orElseThrow(
						() -> new NoSuchElementException("User " + inventory.getUser().getUserID() + " not found"));

		// Retrieve item and handle potential absence
		ShopEntity item = itrepo.findById(inventory.getItem().getItemId())
				.orElseThrow(
						() -> new NoSuchElementException("Item " + inventory.getItem().getItemId() + " not found"));

		// Check if an inventory record already exists for the same user and item
		List<InventoryEntity> existingRecords = irepo.findByItemAndUser(item, user);
		if (!existingRecords.isEmpty()) {
			throw new IllegalArgumentException("Inventory record already exists for this user and item.");
		}

		// Set user and item for the inventory record
		inventory.setItem(item);
		inventory.setUser(user);

		// Save the new inventory record
		return irepo.save(inventory);
	}

	// READ all inventory
	public List<InventoryEntity> getAllInventory() {

		return irepo.findAll();
	}

	// READ Inventory for a specific user
	public List<InventoryEntity> getInventoryByUserID(int userID) {

		UserEntity user = urepo.findById(userID)
				.orElseThrow(() -> new NoSuchElementException("User with ID " + userID + " does not exist."));
		return irepo.findByUser(user); // Assuming you have a method to fetch by user
	}

	// UPDATE Inventory Details
	public InventoryEntity putInventoryDetails(int inventoryId, InventoryEntity newInventoryDetails) {

		InventoryEntity inventory = irepo.findById(inventoryId)
				.orElseThrow(() -> new NoSuchElementException("Inventory item " + inventoryId + " not found."));

		inventory.setItem(newInventoryDetails.getItem());
		inventory.setUser(newInventoryDetails.getUser()); // Assuming you want to update user too
		return irepo.save(inventory);
	}

	// DELETE Inventory
	public String deleteInventory(int inventoryId) {

		if (irepo.findById(inventoryId).isPresent()) {
			irepo.deleteById(inventoryId);

			return "Inventory record successfully deleted.";
		} else {
			throw new NoSuchElementException("Inventory item " + inventoryId + " not found.");
		}
	}

	public void createDefaultInventoryForUser(UserEntity user) {
		// Check if the default item already exists in the shop
		ShopEntity defaultItem = itrepo.findById(5).orElse(null);

		if (defaultItem == null) {
			// Create the default item if it doesn't exist
			defaultItem = new ShopEntity();
			defaultItem.setItemName("Default");
			defaultItem.setItemCost(0);
			defaultItem.setItemUrl("theme.png");
			itrepo.save(defaultItem);
		}

		// Check if the user already has the default inventory
		List<InventoryEntity> existingInventory = irepo.findByUser(user);
		boolean hasDefaultInventory = existingInventory.stream()
				.anyMatch(inventory -> inventory.getItem().getItemId() == 5);

		if (!hasDefaultInventory) {
			// Create a new inventory for the user with the default item
			InventoryEntity defaultInventory = new InventoryEntity();
			defaultInventory.setUser(user);
			defaultInventory.setItem(defaultItem); // Associate with the existing default item
			irepo.save(defaultInventory);
		}
	}

}
