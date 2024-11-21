package com.g2appdev.swift.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ShopEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemId;

	private String itemName;
	private int itemCost;
	private String itemUrl;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item", orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonManagedReference(value = "item-reference")
	private List<InventoryEntity> inventory;

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

	public List<InventoryEntity> getInventory() {
		return inventory;
	}

	public void setInventory(List<InventoryEntity> inventory) {
		this.inventory = inventory;
	}

}
