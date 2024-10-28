package com.g2appdev.swift.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.FetchType;
//import java.util.List;

@Entity
public class QuizEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int quiz_id;
	
	private String title;
	private String questions;
	private int score;
	
	public QuizEntity() {
		super();
	}
	
	public QuizEntity(int quiz_id, String title, String questions, int score) {
		super();
		this.quiz_id = quiz_id;
		this.title = title;
		this.questions = questions;
		this.score = score;
	}

    public int getQuizId(){
        return quiz_id;
    }

    public void setQuizId(int quiz_id){
        this.quiz_id = quiz_id;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}