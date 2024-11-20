package com.g2appdev.swift.dto;

public class UserDTO {
    private String username;
    private String email;
    private String password;
    private int coinBalance = 0;

    // Constructors
    public UserDTO() {}

    public UserDTO(String username, String email, String password, int coinBalance) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.coinBalance= coinBalance;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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