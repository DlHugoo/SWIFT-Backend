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
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int userID;

	private String username;
	private String email;
	private String password;
	private int coinBalance = 0; // Default progress

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<FlashcardSetEntity> flashcardset;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<DailyQuestEntity> dailyQuest;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<TaskEntity> tasks;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)

	private List<InventoryEntity> inventory;

	// Getters and Setters
	public List<TaskEntity> getTasks() {
		return tasks;
	}

	public void setTasks(List<TaskEntity> tasks) {
		this.tasks = tasks;
	}

	public List<InventoryEntity> getInventory() {
		return inventory;
	}

	public void setInventory(List<InventoryEntity> inventory) {
		this.inventory = inventory;
	}

	public UserEntity() {
		super();
	}

	public UserEntity(int userID, String username, String email, String password, int coinBalance) {
		super();
		this.userID = userID;
		this.username = username;
		this.email = email;
		this.password = password;
		this.coinBalance = coinBalance;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String Username) {
		this.username = Username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCoinBalance() {
		return coinBalance;
	}

	public void setCoinBalance(int coinBalance) {
		this.coinBalance = coinBalance;
	}

}
