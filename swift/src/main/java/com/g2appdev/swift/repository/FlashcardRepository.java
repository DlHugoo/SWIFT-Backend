package com.g2appdev.swift.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g2appdev.swift.entity.FlashcardEntity;
import com.g2appdev.swift.entity.FlashcardSetEntity;

@Repository
public interface FlashcardRepository extends JpaRepository<FlashcardEntity, Integer>{
	
	//this is user-defined method to search a student record by last name
	public FlashcardEntity findByDefinition(String definition);

	List<FlashcardEntity> findByFlashcardset(FlashcardSetEntity flashcardset);

}
