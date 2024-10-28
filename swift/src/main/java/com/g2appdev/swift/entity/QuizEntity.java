package com.g2appdev.swift.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int quiz_id;

    private String title;
    private List<String> questions;
    private int score;
    
    public QuizEntity() {
        super();
    }

    public QuizEntity(int quiz_id, String title, List<String> questions, int score) {
        super();
        this.quiz_id = quiz_id;
        this.title = title;
        this.questions = questions;
        this.score = score;
    }

    public int getQuizId() {
        return quiz_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getQuestions() {
        return questions; // Update the getter
    }

    public void setQuestions(List<String> questions) { // Update the setter
        this.questions = questions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
