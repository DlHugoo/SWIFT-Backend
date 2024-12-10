package com.g2appdev.swift.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class FlashcardSetEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int set_id;

    private String title;
    private String description;

	@OneToOne(mappedBy = "flashcardset", cascade = CascadeType.ALL)
	private QuizEntity quiz;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flashcardset", cascade = CascadeType.ALL)
	private List<FlashcardEntity> flashcard;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "userID")
	private UserEntity user;

	public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public FlashcardSetEntity(){
        super();
    }

    public FlashcardSetEntity(int set_id, String title, String description) {
		super();
		this.set_id = set_id;
		this.title = title;
		this.description = description;
	}

	public void setSetId(int set_id){
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
}