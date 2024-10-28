package com.g2appdev.swift.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InventoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inventoryId;
	
	private String itemList;
	private int totalCoins;
	
	/*@ManyToMany
	@JoinTable(
	        name = "inventory_shop_items",
	        joinColumns = @JoinColumn(name = "inventory_id"),
	        inverseJoinColumns = @JoinColumn(name = "shop_id")
	    )
	 private Set<ShopEntity> items;*/
	public InventoryEntity() {
		
	}
	public InventoryEntity(int inventoryId, String itemList, int totalCoins) {
		super();
		this.inventoryId = inventoryId;
		this.itemList=itemList;
		this.totalCoins = totalCoins;
		
	}
	public int getInventoryId() {
		return inventoryId;
	}
	public String getItemList() {
		return itemList;
	}
	public void setItemList(String itemList) {
		this.itemList = itemList;
	}
	public int getTotalCoins() {
		return totalCoins;
	}
	public void setTotalCoins(int totalCoins) {
		this.totalCoins = totalCoins;
	}
	/*public Set<ShopEntity> getItems() {
		return items;
	}
	public void setItems(Set<ShopEntity> items) {
		this.items = items;
	}*/
}
