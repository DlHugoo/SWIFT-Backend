package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.FlashcardSetEntity;
import com.g2appdev.swift.entity.QuizEntity;
import com.g2appdev.swift.repository.FlashcardSetRepository;

@Service
public class FlashcardSetService {

    @Autowired
    FlashcardSetRepository fsrepo;

    public FlashcardSetService(){
        super();
    }

    public FlashcardSetEntity postFlashcardSetRecord(FlashcardSetEntity flashcardset) {
		return fsrepo.save(flashcardset);
	}

	public List<FlashcardSetEntity>getAllFlashcardSets(){
		return fsrepo.findAll();
	}

	@SuppressWarnings("finally")
	public FlashcardSetEntity putFlashcardSetDetails(int set_id, FlashcardSetEntity newFlashCardSetDetails) {
		FlashcardSetEntity student = new FlashcardSetEntity();
		try {
			//search the id number
			student = fsrepo.findById(set_id).get();
			
			student.setTitle(newFlashCardSetDetails.getTitle());
			student.setDescription(newFlashCardSetDetails.getDescription());
		}catch(NoSuchElementException nex) {
			throw new NameNotFoundException ("Flashcard Set " + set_id + "not found");
		}finally {
			return fsrepo.save(student);
		}
	}

	@SuppressWarnings("unused")
	public String deleteFlashcardSet(int set_id) {
		String msg = "";
		if(fsrepo.findById(set_id) != null) {
			fsrepo.deleteById(set_id);	
			msg = "Flashcard Set Record successfully deleted";
		}else
			msg = set_id + "NOT found!";
		return msg;
	}

    public static Optional<QuizEntity> findById(int setId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
