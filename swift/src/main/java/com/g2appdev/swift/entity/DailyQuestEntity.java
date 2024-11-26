package com.g2appdev.swift.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbldailyquest")
public class DailyQuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dailyQuestId;
    private String title;
    private String description;
    private String status; // Possible values: "incomplete", "complete"
    private int coinsEarned;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "userID")
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public DailyQuestEntity() {
        super();
        this.status = "incomplete"; // Default status
    }

    public DailyQuestEntity(int dailyQuestId, String title, String description, String status, int coinsEarned) {
        super();
        this.dailyQuestId = dailyQuestId;
        this.setTitle(title);
        this.setDescription(description);
        this.setStatus(status);
        this.setCoinsEarned(coinsEarned);
    }

    public int getDailyQuestId() {
        return dailyQuestId;
    }

    public void setDailyQuestId(int dailyQuestId) {
        this.dailyQuestId = dailyQuestId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCoinsEarned() {
        return coinsEarned;
    }

    public void setCoinsEarned(int coinsEarned) {
        this.coinsEarned = coinsEarned;
    }
}
