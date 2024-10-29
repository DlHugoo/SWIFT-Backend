package com.g2appdev.swift.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbltask")
public class TaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.READ_ONLY)
	private int taskId;
	
	private String title;
	private String description;
	private String deadline;
	private int priority;
	private boolean status;
	
	public TaskEntity() {
		super();
	}
	
	public TaskEntity(int taskId, String title, String description, String deadline, int priority, boolean status) {
		super();
		this.taskId = taskId;
		this.title = title;
		this.description = description;
		this.deadline = deadline;
		this.priority = priority;
		this.status = status;
	}
	
	// Getters and Setters
	public String getTitle() {
	    return title;
	}

	public void setTitle(String title) {
	    this.title = title;
	}

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}

	public String getDeadline() {
	    return deadline;
	}

	public void setDeadline(String deadline) {
	    this.deadline = deadline;
	}

	public int getPriority() {
	    return priority;
	}

	public void setPriority(int priority) {
	    this.priority = priority;
	}

	public boolean getStatus() {
	    return status;
	}

	public void setStatus(boolean status) {
	    this.status = status;
	}
}
