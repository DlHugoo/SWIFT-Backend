package com.g2appdev.swift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.g2appdev.swift.entity.InventoryEntity;
import com.g2appdev.swift.entity.ShopEntity;
import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.service.InventoryService;
import com.g2appdev.swift.service.ShopService;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private ShopService shopService;

	@GetMapping("/user/{userId}")
	public ResponseEntity<InventoryEntity> getInventoryByUser(@PathVariable int userId) {
		UserEntity user = new UserEntity();
		user.setUserId(userId);
		InventoryEntity inventory = inventoryService.getInventoryByUser(user);

		if (inventory == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if not found
		}

		return ResponseEntity.ok(inventory);
	}

	@GetMapping("/getAllInventory")
	public ResponseEntity<List<InventoryEntity>> getAllInventory() {
		List<InventoryEntity> inventories = inventoryService.findAll(); // Ensure this method exists in your service
		return ResponseEntity.ok(inventories);
	}

	@PostMapping
	public ResponseEntity<InventoryEntity> createInventory(@RequestBody InventoryEntity inventory) {
		InventoryEntity savedInventory = inventoryService.createInventory(inventory);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedInventory);
	}

	@PutMapping("/{id}")
	public ResponseEntity<InventoryEntity> updateInventory(@PathVariable int id,
			@RequestBody InventoryEntity inventory) {
		InventoryEntity updatedInventory = inventoryService.updateInventory(id, inventory);
		return ResponseEntity.ok(updatedInventory);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteInventory(@PathVariable int id) {
		inventoryService.deleteInventory(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{inventoryId}/add-item/{shopId}")
	public ResponseEntity<InventoryEntity> addItemToInventory(@PathVariable int inventoryId, @PathVariable int shopId) {
		InventoryEntity inventory = inventoryService.getInventoryById(inventoryId);
		ShopEntity item = shopService.getShopItemById(shopId);
		InventoryEntity updatedInventory = inventoryService.addItemToInventory(inventory, item);
		return ResponseEntity.ok(updatedInventory);
	}
}
