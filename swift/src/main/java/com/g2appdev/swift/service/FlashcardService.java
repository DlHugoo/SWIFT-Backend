package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.FlashcardEntity;
import com.g2appdev.swift.entity.FlashcardSetEntity;
import com.g2appdev.swift.repository.FlashcardRepository;

@Service
public class FlashcardService {

	@Autowired
	FlashcardRepository frepo;
	
	public FlashcardService() {
		super();
	}
	
	public FlashcardEntity postFlashcardRecord(FlashcardEntity flashcard) {
		return frepo.save(flashcard);
	}
	
	public List<FlashcardEntity>getAllFlashcards(){
		return frepo.findAll();
	}
	
	@SuppressWarnings("finally")
	public FlashcardEntity putFlashcardDetails(int flashcard_id, FlashcardEntity newFlashCardDetails) {
		FlashcardEntity student = new FlashcardEntity();
		try {
			//search the id number
			student = frepo.findById(flashcard_id).get();
			
			student.setTerm(newFlashCardDetails.getTerm());
			student.setDefinition(newFlashCardDetails.getDefinition());
		}catch(NoSuchElementException nex) {
			throw new NameNotFoundException ("Flashcard " + flashcard_id + "not found");
		}finally {
			return frepo.save(student);
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
}