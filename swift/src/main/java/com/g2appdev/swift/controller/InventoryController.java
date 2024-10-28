package com.g2appdev.swift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.g2appdev.swift.entity.InventoryEntity;
import com.g2appdev.swift.service.InventoryService;

@RestController
@RequestMapping(method = RequestMethod.GET, path="/api/inventory")
public class InventoryController {
	@Autowired
	InventoryService iserv;
	
	@GetMapping("/print")
	public String print() {
		return "Welcome to your inventory";
	}
	@PostMapping("/postInventoryRecord")
	public InventoryEntity postInvetoryRecord(@RequestBody InventoryEntity inventory) {
		return iserv.postInventoryRecord(inventory);
	}
	@GetMapping("/getAllInventory")
	public List<InventoryEntity> getAllInventory(){
		return iserv.getAllInventory();
	}
	@PutMapping("/putInventoryDetails")
	public InventoryEntity putInventoryDetails(@RequestParam int inventoryId, @RequestBody InventoryEntity newInventoryDetails) {
		return iserv.putInventoryDetails(inventoryId,newInventoryDetails);
	}
	@DeleteMapping("/deleteInventoryDetails/{inventoryId}")
	public String deleteInventory(@PathVariable int inventoryId) {
		return iserv.deleteInventory(inventoryId);
	}
}
