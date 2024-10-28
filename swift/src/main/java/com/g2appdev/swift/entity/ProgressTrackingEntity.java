package com.g2appdev.swift.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tblprogresstracking")
public class ProgressTrackingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int progressId;
	//private int userId;
	private float completionRate;
	private float learningProgress;
	private float quizScores;
	private float timeSpent;
	
	
	public ProgressTrackingEntity() {
		super();
	}
	
	public ProgressTrackingEntity(int progressId, float completionRate, float learningProgress, float quizScores,float timeSpent) {
		super();
		this.progressId = progressId;
		this.setCompletionRate(completionRate);
		this.setLearningProgress(learningProgress);
		this.setQuizScores(quizScores);
		this.setTimeSpent(timeSpent);
	}
	
	public int getProgressId() {
		return progressId;
	}

	public void setProgressId(int progressId) {
		this.progressId = progressId;
	}

	public float getCompletionRate() {
		return completionRate;
	}

	public void setCompletionRate(float completionRate) {
		this.completionRate = completionRate;
	}

	public float getLearningProgress() {
		return learningProgress;
	}

	public void setLearningProgress(float learningProgress) {
		this.learningProgress = learningProgress;
	}

	public float getQuizScores() {
		return quizScores;
	}

	public void setQuizScores(float quizScores) {
		this.quizScores = quizScores;
	}

	public float getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(float timeSpent) {
		this.timeSpent = timeSpent;
	}
	
	
}
