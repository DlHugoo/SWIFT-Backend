package com.g2appdev.swift.entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblinventory")
public class InventoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inventoryId;

	private String itemList;
	private int totalCoins;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "inventory", cascade = CascadeType.ALL)
	private List<ShopEntity> items;

	@OneToOne(mappedBy = "inventory")
	private UserEntity user;

	public InventoryEntity() {
		this.totalCoins = 1000; // Set the default value to 1000
	}

	public InventoryEntity(int inventoryId, String itemList, int totalCoins, List<ShopEntity> items, UserEntity user) {
		this.inventoryId = inventoryId;
		this.itemList = itemList;
		this.totalCoins = totalCoins;
		this.items = items;
		this.user = user;
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
		if (totalCoins < 0) {
			throw new IllegalArgumentException("Total coins cannot be negative");
		}
		this.totalCoins = totalCoins;
	}

	public List<ShopEntity> getItems() {
		return items;
	}

	public void setItems(List<ShopEntity> items) {
		this.items = items;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
