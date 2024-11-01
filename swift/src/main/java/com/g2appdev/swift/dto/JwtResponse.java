// src/main/java/com/g2appdev/swift/dto/JwtResponse.java
package com.g2appdev.swift.dto;

public class JwtResponse {
    private String token;

    // Constructor
    public JwtResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
