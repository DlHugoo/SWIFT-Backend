package com.g2appdev.swift.entity;

import jakarta.persistence.*;
//import java.util.List;
@Entity
public class FlashcardEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flashcard_id;
	
	private String term;
	private String definition;
	
	public FlashcardEntity(){
		super();
	}
	
	public FlashcardEntity(int flashcard_id, String term, String definition) {
		super();
		this.flashcard_id = flashcard_id;
		this.term = term;
		this.definition = definition;
	}

	public int getFlashcardId() {
		return flashcard_id;
	}
	
	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

}
