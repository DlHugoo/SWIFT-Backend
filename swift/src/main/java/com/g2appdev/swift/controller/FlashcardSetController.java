package com.g2appdev.swift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.g2appdev.swift.entity.FlashcardSetEntity;
import com.g2appdev.swift.service.FlashcardSetService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/flashcardset")
public class FlashcardSetController {
	@Autowired
	FlashcardSetService fsserv;
	
    @GetMapping("/print")
    public String print() {
        return "Hello, Flashcard Sets";
    }
    @PostMapping("/postflashcardsetrecord")
    public FlashcardSetEntity postFlashcardSetRecord(@RequestBody FlashcardSetEntity flashcardset) {
        return fsserv.postFlashcardSetRecord(flashcardset);
    }

    @GetMapping("/getAllFlashcardSet")
    public List<FlashcardSetEntity> getAllFlashcardSets() {
        return fsserv.getAllFlashcardSets();
    }

        // New endpoint to get flashcard sets by user
        @GetMapping("/getAllFlashcardSetByUser/{userId}")
    public List<FlashcardSetEntity> getFlashcardSetsByUser(@PathVariable int userId) {
        return fsserv.getFlashcardSetsByUser(userId);
    }

    @PutMapping("/putFlashcardSetDetails/{set_id}")
    public FlashcardSetEntity putFlashcardSetDetails(@PathVariable int set_id, @RequestBody FlashcardSetEntity newFlashCardSetDetails) {
        return fsserv.putFlashcardSetDetails(set_id, newFlashCardSetDetails);
    }

    @DeleteMapping("/deleteFlashcardSetDetails/{set_id}")
    public String deleteFlashcardSet(@PathVariable int set_id) {
    	return fsserv.deleteFlashcardSet(set_id);
    }
}
