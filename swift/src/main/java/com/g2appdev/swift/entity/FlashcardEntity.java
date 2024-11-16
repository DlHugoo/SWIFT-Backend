package com.g2appdev.swift.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
//import java.util.List;
@Entity
public class FlashcardEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flashcard_id;
	
	private String term;
	private String definition;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "set_id")
	@JsonBackReference // Prevents infinite recursion with FlashcardSetEntity
	private FlashcardSetEntity flashcardset;

	public FlashcardSetEntity getFlashcardSet() {
        return flashcardset;
    }

    public void setFlashcardSet(FlashcardSetEntity flashcardset) {
        this.flashcardset = flashcardset;
    }

	
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
