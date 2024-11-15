package com.g2appdev.swift.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class FlashcardSetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int set_id;

    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID") // Foreign key to UserEntity
    private UserEntity user;

    @OneToOne(mappedBy = "flashcardset")
    private QuizEntity quiz;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "flashcardset", orphanRemoval = true,
    cascade = CascadeType.ALL)
    private List<FlashcardEntity> flashcard;

    public FlashcardSetEntity() {
        super();
    }

    public FlashcardSetEntity(int set_id, String title, String description, UserEntity user) {
        super();
        this.set_id = set_id;
        this.title = title;
        this.description = description;
        this.user = user;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
