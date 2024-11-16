package com.g2appdev.swift.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quiz_id;

    @OneToOne
    @JoinColumn(name = "set_id")
    @JsonBackReference // Prevents recursion issues
    private FlashcardSetEntity flashcardset;

    @JsonProperty("flashcardSetTitle")
    public String getFlashcardSetTitle() {
        return flashcardset != null ? flashcardset.getTitle() : null;
    }

    public FlashcardSetEntity getFlashcardSet() {
        return flashcardset;
    }

    public void setFlashcardSet(FlashcardSetEntity flashcardset) {
        this.flashcardset = flashcardset;
    }

    private String title;
    private int totalScore;

    @ElementCollection
    private List<Question> questions;

    // Default constructor
    public QuizEntity() {
        super();
    }

    // Constructor with parameters
    public QuizEntity(int quiz_id, String title, List<Question> questions) {
        super();
        this.quiz_id = quiz_id;
        this.title = title;
        this.questions = questions;
        this.totalScore = calculateTotalScore(); // Calculate total score based on questions
    }

    // Getters and setters
    public int getQuizId() {
        return quiz_id;
    }

    public void setQuizId(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        this.totalScore = calculateTotalScore(); // Recalculate total score if questions change
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore){
        this.totalScore = totalScore;
    }

    // Helper method to calculate total score
    private int calculateTotalScore() {
        return questions.stream().mapToInt(Question::getScore).sum();
    }

    // Embeddable class for Question
    @Embeddable
    public static class Question {
        private String text;
        private List<String> options;
        private int correctAnswerIndex;
        private int score; // Add score field for individual question

        // Default constructor
        public Question() {
            super();
        }

        // Constructor with parameters
        public Question(String text, List<String> options, int correctAnswerIndex, int score) {
            this.text = text;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
            this.score = score; // Set score for the question
        }

        // Getters and setters
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<String> getOptions() {
            return options;
        }

        public void setOptions(List<String> options) {
            this.options = options;
        }

        public int getCorrectAnswerIndex() {
            return correctAnswerIndex;
        }

        public void setCorrectAnswerIndex(int correctAnswerIndex) {
            this.correctAnswerIndex = correctAnswerIndex;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}