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
	private int itemCost;
	private String itemUrl;

	/*
	 * @ManyToMany(mappedBy = "items")
	 * private Set<InventoryEntity> inventories;
	 */
	public ShopEntity() {

	}

	public ShopEntity(int itemId, String itemName, int itemCost, String itemUrl) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;

		this.itemCost = itemCost;
		this.itemUrl = itemUrl;

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

	public int getItemCost() {
		return itemCost;
	}

	public void setItemCost(int itemCost) {
		this.itemCost = itemCost;
	}

	public String getItemUrl() {
		return itemUrl;
	}

	public void setItemUrl(String itemUrl) {
		this.itemUrl = itemUrl;
	}
}
