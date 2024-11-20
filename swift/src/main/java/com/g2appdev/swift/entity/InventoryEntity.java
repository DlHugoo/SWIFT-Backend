package com.g2appdev.swift.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class InventoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inventoryId;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userID")
	private UserEntity user;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "itemID")
	private ShopEntity item;

	public InventoryEntity() {

	}

	public InventoryEntity(int inventoryId) {
		super();
		this.inventoryId = inventoryId;

	}

	public int getInventoryId() {
		return inventoryId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public ShopEntity getItem() {
		return item;
	}

	public void setItem(ShopEntity item) {
		this.item = item;
	}

}
