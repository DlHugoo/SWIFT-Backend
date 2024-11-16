package com.g2appdev.swift.entity;

import java.util.List;

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
    private int progressData = 0; // Default progress

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FlashcardSetEntity> flashcardSets;

    public UserEntity() {
        super();
    }

    public UserEntity(int userID, String username, String email, String password, int progressData) {
        super();
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = password;
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

    public int getProgressData() {
        return progressData;
    }

    public void setProgressData(int progressData) {
        this.progressData = progressData;
    }

    public List<FlashcardSetEntity> getFlashcardSets() {
        return flashcardSets;
    }

    public void setFlashcardSets(List<FlashcardSetEntity> flashcardSets) {
        this.flashcardSets = flashcardSets;
    }
}
