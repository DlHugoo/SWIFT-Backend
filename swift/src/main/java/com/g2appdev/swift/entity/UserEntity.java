package com.g2appdev.swift.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int userID;
	
	private String username;
	private String email;
	private String password;
	private int avatarID = 0; // Default avatar
	private int progressData = 0; // Default progress
	
	
	public UserEntity() {
		super();
	}
	
	public UserEntity(int userID, String username, String email, String password, int avatarID, int progressData) {
		super();
		this.userID = userID;
		this.username = username;
		this.email = email;
		this.password =  password;
		this.avatarID = avatarID;
		this.progressData = progressData;
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
	
	public int getAvatarID() {
		return avatarID;
	}
	
	public void setAvatarID(int avatarID) {
		this.avatarID = avatarID;
	}
	
	public int getProgressData() {
		return progressData;
	}
	
	public void setProgressData(int progressData) {
		this.progressData = progressData;
	}

}
