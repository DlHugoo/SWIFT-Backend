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
		// Check if the default inventory already exists for the user
		List<InventoryEntity> existingInventory = irepo.findByUser(user);
		boolean hasDefaultInventory = existingInventory.stream()
				.anyMatch(inventory -> inventory.getItem().getItemId() == 1); // Check for itemId = 1

		// Create the default inventory if it does not exist
		if (!hasDefaultInventory) {
			InventoryEntity defaultInventory = new InventoryEntity();

			defaultInventory.setUser(user);

			// Create a default item with itemId = 1
			ShopEntity defaultItem = new ShopEntity();
			defaultItem.setItemName("Default");
			defaultItem.setItemCost(0);
			defaultItem.setItemUrl("theme.png");
			defaultInventory.setItem(defaultItem);
			// Associate the item with the inventor
			// Save the inventory to the repository
			irepo.save(defaultInventory);
		}

	}

	public InventoryEntity getDefaultInventoryForUser(UserEntity user) {
		return irepo.findByUser(user).stream()
				.findFirst()
				.orElseGet(() -> {
					InventoryEntity newInventory = new InventoryEntity();
					newInventory.setUser(user);
					return irepo.save(newInventory);
				});
	}
}
