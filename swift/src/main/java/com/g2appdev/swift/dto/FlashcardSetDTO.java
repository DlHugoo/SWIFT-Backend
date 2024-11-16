package com.g2appdev.swift.dto;

public class FlashcardSetDTO {
    private int set_id;
    private String title;
    private String description;
    private int userID;

    public FlashcardSetDTO(int set_id, String title, String description, int userID) {
        this.set_id = set_id;
        this.title = title;
        this.description = description;
        this.userID = userID;
    }

    public void setSetId(int set_id) {
        this.set_id = set_id;
    }

    public int getSetId() {
        return set_id;
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
