package com.g2appdev.swift.dto;

import com.g2appdev.swift.entity.FlashcardSetEntity;
import com.g2appdev.swift.entity.QuizEntity;

public class QuizRequestDTO {

    private QuizEntity quiz;
    private FlashcardSetEntity flashcardSet;

    // Getters and setters
    public QuizEntity getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizEntity quiz) {
        this.quiz = quiz;
    }

    public FlashcardSetEntity getFlashcardSet() {
        return flashcardSet;
    }

    public void setFlashcardSet(FlashcardSetEntity flashcardSet) {
        this.flashcardSet = flashcardSet;
    }
}
