package com.g2appdev.swift.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.List;

@Entity
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quiz_id;

    @OneToOne
    @JoinColumn(name = "set_id")
    private FlashcardSetEntity flashcardset;

    public FlashcardSetEntity getFlashcardSet() {
        return flashcardset;
    }

    public void setFlashcardSet(FlashcardSetEntity flashcardset) {
        this.flashcardset = flashcardset;
    }

    private String title;
    private int score;

    @ElementCollection
    private List<Question> questions;

    // Default constructor
    public QuizEntity() {
        super();
    }

    // Constructor with parameters
    public QuizEntity(int quiz_id, String title, List<Question> questions, int score) {
        super();
        this.quiz_id = quiz_id;
        this.title = title;
        this.questions = questions;
        this.score = score;
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
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Embeddable class for Question
    @Embeddable
    public static class Question {
        private String text;
        private List<String> options;
        private int correctAnswerIndex;

        // Default constructor
        public Question() {
            super();
        }

        // Constructor with parameters
        public Question(String text, List<String> options, int correctAnswerIndex) {
            this.text = text;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
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
    }
}
