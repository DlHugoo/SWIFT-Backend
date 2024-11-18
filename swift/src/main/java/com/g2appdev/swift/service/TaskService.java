package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.TaskEntity;
import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.repository.TaskRepository;
import com.g2appdev.swift.repository.UserRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository trepo;

	@Autowired
	UserRepository urepo; // Inject UserRepository to fetch user data

	public TaskService() {
		super();
	}

	// CREATE Task for specific user
	public TaskEntity postTaskRecordForUser(TaskEntity task, int userId) {
		UserEntity user = urepo.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("User " + userId + " not found"));
	
		// Optional: Avoid saving duplicate tasks
		List<TaskEntity> existingTasks = user.getTasks();
		if (existingTasks.stream().anyMatch(t -> t.getTitle().equalsIgnoreCase(task.getTitle()))) {
			throw new IllegalArgumentException("A task with this title already exists for the user.");
		}
	
		task.setUser(user);
		return trepo.save(task);
	}

	// READ all tasks
	public List<TaskEntity> getAllTasks() {
		return trepo.findAll();
	}

	// READ Tasks for a specific user
	public List<TaskEntity> getTasksByUser(int userId) {
		UserEntity user = urepo.findById(userId)
				.orElseThrow(() -> new NoSuchElementException("User " + userId + " not found"));
		return user.getTasks(); // Fetch tasks associated with the user
	}

	// UPDATE
	public TaskEntity putTaskDetails(int id, TaskEntity newTaskDetails) {
		TaskEntity task = trepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Task " + id + " not found"));
		task.setTitle(newTaskDetails.getTitle());
		task.setDescription(newTaskDetails.getDescription());
		task.setDeadline(newTaskDetails.getDeadline());
		task.setPriority(newTaskDetails.getPriority());
		task.setStatus(newTaskDetails.getStatus());
		return trepo.save(task);
	}

	// UPDATE STATUS
	public TaskEntity updateTaskStatus(int id, boolean status) {
		TaskEntity task = trepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Task " + id + " not found"));
		task.setStatus(status);
		return trepo.save(task);
	}

	// DELETE
	public String deleteTask(int taskId) {
		if (trepo.findById(taskId).isPresent()) {
			trepo.deleteById(taskId);
			return "Task record successfully deleted.";
		} else {
			throw new NoSuchElementException("Task " + taskId + " not found.");
		}
	}
}
