package com.g2appdev.swift.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g2appdev.swift.entity.FlashcardSetEntity;
import com.g2appdev.swift.entity.QuizEntity;
import com.g2appdev.swift.entity.UserEntity;

@Repository
public interface QuizRepository extends JpaRepository<QuizEntity, Integer>{
	
	//this is user-defined method to search a student record by last name
	public QuizEntity findByTitle(String title);

	    // Custom query method to find quizzes by setId
	List<QuizEntity> findByFlashcardset(FlashcardSetEntity flashcardset);

	 // Custom query method to find quizzes by userId
    List<QuizEntity> findByFlashcardset_User(UserEntity user); 
}