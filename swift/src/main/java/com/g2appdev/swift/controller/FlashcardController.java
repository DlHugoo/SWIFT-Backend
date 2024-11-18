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

import com.g2appdev.swift.entity.FlashcardEntity;
import com.g2appdev.swift.service.FlashcardService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/flashcard")
public class FlashcardController {
	@Autowired
	FlashcardService fserv;
	
    @GetMapping("/print")
    public String print() {
        return "Hello, Flashcards";
    }

    @PostMapping("/postflashcardrecord")
    public FlashcardEntity postFlashcardRecord(@RequestBody FlashcardEntity flashcard) {
        return fserv.postFlashcardRecord(flashcard);
    }

    @GetMapping("/getAllFlashcards")
    public List<FlashcardEntity> getAllFlashcards() {
        return fserv.getAllFlashcards();
    }

    
    @PutMapping("/putFlashcardsDetails/{flashcard_id}")
    public FlashcardEntity putFlashcardsDetails(@PathVariable int flashcard_id, @RequestBody FlashcardEntity newFlashcardDetails) {
        return fserv.putFlashcardDetails(flashcard_id, newFlashcardDetails);
    }
    
    @DeleteMapping("/deleteFlashcardDetails/{flashcard_id}")
    public String deleteFlashcard(@PathVariable int flashcard_id) {
    	return fserv.deleteFlashcard(flashcard_id);
    }

    @GetMapping("/getFlashcardsBySetId/{setId}")
    public List<FlashcardEntity> getFlashcardsBySetId(@PathVariable int setId) {
        return fserv.getFlashcardsBySetId(setId);
    }
    
}
