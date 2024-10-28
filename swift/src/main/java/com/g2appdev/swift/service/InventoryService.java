package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.InventoryEntity;
import com.g2appdev.swift.repository.InventoryRepository;

@Service
public class InventoryService {
	@Autowired
	InventoryRepository irepo;
	
	public InventoryService() {
		super();
	}
	
	public InventoryEntity postInventoryRecord(InventoryEntity inventory) {
		return irepo.save(inventory);
	}
	public List<InventoryEntity> getAllInventory(){
		return irepo.findAll();
	}
	@SuppressWarnings("finally")
	public InventoryEntity putInventoryDetails(int inventoryId, InventoryEntity newInventoryDetails) {
		InventoryEntity inventory = new InventoryEntity();
		try {
			inventory = irepo.findById(inventoryId).get();
			
			inventory.setItemList(newInventoryDetails.getItemList());
			inventory.setTotalCoins(newInventoryDetails.getTotalCoins());
			
		}catch(NoSuchElementException nex) {
			throw new NameNotFoundException("Item not found");
		}finally {
			return irepo.save(inventory);
		}
	}
	
	public String deleteInventory(int inventoryId) {
		String msg = "";
		if(irepo.findById(inventoryId).isPresent()) {
			irepo.deleteById(inventoryId);
			msg = "Shop Record successfully deleted!";
		}else {
			msg = inventoryId+"Not Found!";
		}return msg;
	}
	
	
}
