package com.g2appdev.swift.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShopEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemId;
	
	private String itemName;
	private String description;
	private int itemCost;
	private String itemType;
	/*@ManyToMany(mappedBy = "items")
    private Set<InventoryEntity> inventories;*/
	public ShopEntity() {
		
	}
	public ShopEntity(int itemId, String itemName,String description, int itemCost, String itemType ) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.description = description;
		this.itemCost = itemCost;
		this.itemType = itemType;
			
	}
	public int getItemId() {
		return itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getItemCost() {
		return itemCost;
	}
	public void setItemCost(int itemCost) {
		this.itemCost = itemCost;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
}
