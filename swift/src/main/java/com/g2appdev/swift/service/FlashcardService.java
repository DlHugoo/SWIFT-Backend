package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.FlashcardEntity;
import com.g2appdev.swift.entity.FlashcardSetEntity;
import com.g2appdev.swift.repository.FlashcardRepository;
import com.g2appdev.swift.repository.FlashcardSetRepository;

@Service
public class FlashcardService {

	@Autowired
	FlashcardRepository frepo;

	@Autowired
    FlashcardSetRepository fsrepo;
	
	
	public FlashcardService() {
		super();
	}
	
	public FlashcardEntity postFlashcardRecord(FlashcardEntity flashcard) {
		        // Check if the flashcard set associated with the quiz is not null
				if (flashcard.getFlashcardSet() == null) {
					throw new IllegalArgumentException("FlashcardSet cannot be null.");
				}
				
				// Check if the flashcard set associated with the quiz exists in the database
				FlashcardSetEntity flashcardSet = fsrepo.findById(flashcard.getFlashcardSet().getSetId())
						.orElseThrow(() -> new NoSuchElementException("FlashcardSet with ID " + flashcard.getFlashcardSet().getSetId() + " does not exist."));
				
				// Set the flashcard set in the quiz (this step is optional as it's already done)
				flashcard.setFlashcardSet(flashcardSet);
				
				// Save and return the quiz record
				return frepo.save(flashcard);
	}
	
	public List<FlashcardEntity>getAllFlashcards(){
		return frepo.findAll();
	}
	
	@SuppressWarnings("finally")
	public FlashcardEntity putFlashcardDetails(int flashcard_id, FlashcardEntity newFlashCardDetails) {
		FlashcardEntity flashcard = new FlashcardEntity();
		try {
			//search the id number
			flashcard = frepo.findById(flashcard_id).get();
			
			
			flashcard.setTerm(newFlashCardDetails.getTerm());
			flashcard.setDefinition(newFlashCardDetails.getDefinition());
		}catch(NoSuchElementException nex) {
			throw new NameNotFoundException ("Flashcard " + flashcard_id + "not found");
		}finally {
			return frepo.save(flashcard);
		}
	}
	
	@SuppressWarnings("unused")
	public String deleteFlashcard(int flashcard_id) {
		String msg = "";
		if(frepo.findById(flashcard_id) != null) {
			frepo.deleteById(flashcard_id);	
			msg = "Flashcard Record successfully deleted";
		}else
			msg = flashcard_id + "NOT found!";
		return msg;
	}

	public FlashcardSetEntity postFlashcardSetRecord(FlashcardSetEntity flashcardset) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'postFlashcardSetRecord'");
	}

	public List<FlashcardEntity> getFlashcardsBySetId(int setId) {
		// Check if the flashcard set exists in the database
		FlashcardSetEntity flashcardSet = fsrepo.findById(setId)
				.orElseThrow(() -> new NoSuchElementException("FlashcardSet with ID " + setId + " does not exist."));
		
		// Retrieve and return the list of flashcards associated with the specified set_id
		return frepo.findByFlashcardset(flashcardSet);
	}
}