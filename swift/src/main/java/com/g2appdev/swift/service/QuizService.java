package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.FlashcardSetEntity;
import com.g2appdev.swift.entity.QuizEntity;
import com.g2appdev.swift.repository.FlashcardSetRepository;
import com.g2appdev.swift.repository.QuizRepository;

import jakarta.transaction.Transactional;

@Service
public class QuizService {

    @Autowired
    QuizRepository qrepo;

    @Autowired
    FlashcardSetRepository fsrepo;

    public QuizService() {
        super();
    }

    public QuizEntity postQuizRecord(QuizEntity quiz) {
        // Check if the flashcard set associated with the quiz is not null
        if (quiz.getFlashcardSet() == null) {
            throw new IllegalArgumentException("FlashcardSet cannot be null.");
        }
        
        // Check if the flashcard set associated with the quiz exists in the database
        FlashcardSetEntity flashcardSet = fsrepo.findById(quiz.getFlashcardSet().getSetId())
                .orElseThrow(() -> new NoSuchElementException("FlashcardSet with ID " + quiz.getFlashcardSet().getSetId() + " does not exist."));
        
        // Set the flashcard set in the quiz
        quiz.setFlashcardSet(flashcardSet);
        
        // Recalculate total score based on individual question scores
        quiz.setTotalScore(calculateTotalScore(quiz.getQuestions()));
        
        // Save and return the quiz record
        return qrepo.save(quiz);
    }

    // Method to calculate the total score for a quiz
    private int calculateTotalScore(List<QuizEntity.Question> questions) {
        return questions.stream().mapToInt(QuizEntity.Question::getScore).sum();
    }

    // Method to get all quizzes
    public List<QuizEntity> getAllQuizzes() {
        return qrepo.findAll();
    }

    // Method to update an existing quiz
    public QuizEntity putQuizDetails(int quiz_id, QuizEntity newQuizDetails) {
        QuizEntity quiz = new QuizEntity();
        try {
            // Search for the quiz by its ID
            quiz = qrepo.findById(quiz_id).orElseThrow(() -> 
                new NoSuchElementException("Quiz " + quiz_id + " not found")
            );

            // Update the title, questions, and associated flashcard set
            quiz.setFlashcardSet(newQuizDetails.getFlashcardSet());
            quiz.setTitle(newQuizDetails.getTitle());
            quiz.setQuestions(newQuizDetails.getQuestions()); // This now includes questions and their scores
            
            // Recalculate total score based on the updated questions
            quiz.setTotalScore(calculateTotalScore(newQuizDetails.getQuestions()));
        } catch (NoSuchElementException nex) {
            throw new NoSuchElementException("Quiz " + quiz_id + " not found");
        }

        // Save the updated quiz
        return qrepo.save(quiz);
    }

    // Method to delete a quiz by ID
    @Transactional
    public String deleteQuiz(int quiz_id) {
        if (qrepo.existsById(quiz_id)) {
            qrepo.deleteById(quiz_id);
            return "Quiz record successfully deleted";
        } else {
            return "Quiz with ID " + quiz_id + " not found!";
        }
    }
}
