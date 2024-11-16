package com.g2appdev.swift.entity;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblinventory")
public class InventoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inventoryId;

	private String itemList;
	private int totalCoins;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "itemId", cascade = CascadeType.ALL)
	private List<ShopEntity> items;

	public InventoryEntity() {

	}

	public InventoryEntity(int inventoryId, String itemList, int totalCoins) {
		super();
		this.inventoryId = inventoryId;
		this.itemList = itemList;
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

	public List<ShopEntity> getItems() {
		return items;
	}

	public void setItems(List<ShopEntity> items) {
		this.items = items;
	}
}
