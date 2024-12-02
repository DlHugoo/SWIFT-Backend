package com.g2appdev.swift.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.FlashcardSetEntity;
import com.g2appdev.swift.entity.QuizEntity;
import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.repository.FlashcardSetRepository;
import com.g2appdev.swift.repository.QuizRepository;
import com.g2appdev.swift.repository.UserRepository;

@Service
public class QuizService {

    @Autowired
    QuizRepository qrepo;

    @Autowired
    FlashcardSetRepository fsrepo;

    @Autowired
    UserRepository urepo;

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

    // Method to get quizzes associated with a user
    public List<QuizEntity> getQuizzesByUserId(int userId) {
        // Find the user by ID
        UserEntity user = urepo.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + userId + " does not exist."));

        // Find quizzes for the user by querying the FlashcardSet associated with the user
        return qrepo.findByFlashcardset_User(user);
    }

    // Method to get analytics on quiz scores (e.g., average score across quizzes)
    public double getAverageScoreByUserId(int userId) {
        List<QuizEntity> quizzes = getQuizzesByUserId(userId);
        return quizzes.stream()
            .mapToInt(QuizEntity::getUserScore)
            .average()
            .orElse(0.0);
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

    public String deleteQuiz(int quiz_id) {
        if (qrepo.existsById(quiz_id)) {
            QuizEntity quiz = qrepo.findById(quiz_id).orElse(null);
            if (quiz != null) {
                quiz.setFlashcardSet(null); // Detach the flashcard set
                qrepo.delete(quiz);
            }
            return "Quiz record successfully deleted";
        } else {
            return "Quiz with ID " + quiz_id + " not found!";
        }
    }
    

public List<QuizEntity> getFlashcardsByUserId(int userId) {
    // Check if the user exists
    UserEntity user = urepo.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("User with ID " + userId + " does not exist."));

    // Retrieve and return the list of flashcards associated with the specified user
    List<FlashcardSetEntity> flashcardSets = fsrepo.findByUser(user);  // Assuming there's a method to get FlashcardSets by user
    
    // Get the list of flashcards from each set
    List<QuizEntity> flashcards = new ArrayList<>();
    for (FlashcardSetEntity flashcardSet : flashcardSets) {
        flashcards.addAll(qrepo.findByFlashcardset(flashcardSet));
    }

    return flashcards;
}

public String updateUserScore(int quizId, int userScore) {
    // Fetch the quiz by ID
    QuizEntity quiz = qrepo.findById(quizId)
            .orElseThrow(() -> new NoSuchElementException("Quiz with ID " + quizId + " does not exist."));

    // Update the userScore in the QuizEntity
    quiz.setUserScore(userScore);

    // Save the updated QuizEntity
    qrepo.save(quiz);

    return "User score updated successfully!";
}
    
}
