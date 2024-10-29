package com.g2appdev.swift.entity;

import jakarta.persistence.*;

@Entity
public class FlashcardSetEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int set_id;

    private String title;
    private String description;

    public FlashcardSetEntity(){
        super();
    }

    public FlashcardSetEntity(int set_id, String title, String description) {
		super();
		this.set_id = set_id;
		this.title = title;
		this.description = description;
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
