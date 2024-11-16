package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.dto.FlashcardSetDTO;
import com.g2appdev.swift.entity.FlashcardSetEntity;
import com.g2appdev.swift.entity.QuizEntity;
import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.repository.FlashcardSetRepository;
import com.g2appdev.swift.repository.UserRepository; // Assuming you have this repository for UserEntity

@Service
public class FlashcardSetService {

    @Autowired
    FlashcardSetRepository fsrepo;

    @Autowired
    UserRepository userrepo; // Inject the User repository to access UserEntity

    public FlashcardSetService() {
        super();
    }

    // Post method to create a FlashcardSet and associate it with a User
    public FlashcardSetEntity postFlashcardSetRecord(FlashcardSetEntity flashcardset, int userID) {
        // Fetch the user from the database
        UserEntity user = userrepo.findById(userID).orElseThrow(() -> new NoSuchElementException("User not found"));
        
        // Set the user for the FlashcardSet
        flashcardset.setUser(user);
        
        // Save and return the FlashcardSet
        return fsrepo.save(flashcardset);
    }

    // Get all FlashcardSets
    public List<FlashcardSetEntity> getAllFlashcardSets() {
        return fsrepo.findAll();
    }

	public List<FlashcardSetDTO> getFlashcardSetsByUser(int userID) {
        List<FlashcardSetEntity> entities = fsrepo.findByUserUserID(userID); // Example query
        return entities.stream()
                .map(entity -> new FlashcardSetDTO(
                        entity.getSetId(),
                        entity.getTitle(),
                        entity.getDescription(),
                        entity.getUserID())) // Convenience method from step 1
                .collect(Collectors.toList());
    }
    
    // Update method to edit FlashcardSet details
    @SuppressWarnings("finally")
    public FlashcardSetEntity putFlashcardSetDetails(int set_id, FlashcardSetEntity newFlashCardSetDetails) {
        FlashcardSetEntity student = new FlashcardSetEntity();
        try {
            student = fsrepo.findById(set_id).get();
            
            student.setTitle(newFlashCardSetDetails.getTitle());
            student.setDescription(newFlashCardSetDetails.getDescription());
            // Optionally, handle user update if necessary
        } catch (NoSuchElementException nex) {
            throw new NameNotFoundException("Flashcard Set " + set_id + " not found");
        } finally {
            return fsrepo.save(student);
        }
    }

    // Delete FlashcardSet
    public String deleteFlashcardSet(int set_id) {
        String msg = "";
        if (fsrepo.findById(set_id).isPresent()) {
            fsrepo.deleteById(set_id);
            msg = "Flashcard Set Record successfully deleted";
        } else {
            msg = set_id + " NOT found!";
        }
        return msg;
    }

    public static Optional<QuizEntity> findById(int setId) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
}
