package com.g2appdev.swift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.g2appdev.swift.entity.InventoryEntity;
import com.g2appdev.swift.entity.ShopEntity;
import com.g2appdev.swift.service.InventoryService;

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT, RequestMethod.DELETE })

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	@Autowired
	InventoryService iserv;

	@GetMapping("/print")
	public String print() {
		return "Welcome to your inventory";
	}

	// CREATE Inventory Record
	@PostMapping("/postInventoryRecord")
	public InventoryEntity postInventoryRecord(@RequestBody InventoryEntity inventory) {
		System.out.println("Received Inventory: " + inventory);
		return iserv.postInventoryRecord(inventory);
	}

	// READ all Inventory
	@GetMapping("/getAllInventory")
	public List<InventoryEntity> getAllInventory() {
		return iserv.getAllInventory();
	}

	// READ Inventory for a specific user
	@GetMapping("/getInventoryByUserId/{userID}")
	public List<InventoryEntity> getInventoryByUserID(@PathVariable int userID) {
		return iserv.getInventoryByUserID(userID);
	}

	// UPDATE Inventory Details
	@PutMapping("/putInventoryDetails/{inventoryId}")
	public InventoryEntity putInventoryDetails(@PathVariable int inventoryId,
			@RequestBody InventoryEntity newInventoryDetails) {
		return iserv.putInventoryDetails(inventoryId, newInventoryDetails);
	}

	// DELETE Inventory
	@DeleteMapping("/deleteInventory/{inventoryId}")
	public String deleteInventory(@PathVariable int inventoryId) {
		return iserv.deleteInventory(inventoryId);
	}

	@PostMapping("/purchase")
	public ResponseEntity<String> purchaseItem(@RequestParam int userId, @RequestParam int itemId) {
		try {
			iserv.addItemToInventory(userId, itemId);
			return ResponseEntity.ok("Item added to inventory");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/purchased/{userID}")
	public ResponseEntity<List<ShopEntity>> getPurchasedItems(@PathVariable int userID) {
		List<ShopEntity> items = iserv.getPurchasedItemsByUserID(userID);
		return ResponseEntity.ok(items);
	}
}
