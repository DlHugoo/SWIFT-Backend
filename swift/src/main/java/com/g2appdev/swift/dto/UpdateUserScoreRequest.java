package com.g2appdev.swift.dto;

public class UpdateUserScoreRequest {
    private int quizId;
    private int userScore;

    // Getters and setters
    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }
}