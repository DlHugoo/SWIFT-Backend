package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.TaskEntity;
import com.g2appdev.swift.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	TaskRepository trepo;
	
	public TaskService() {
		super();
	}
	
	//CREATE
	public TaskEntity postTaskRecord(TaskEntity task) {
		return trepo.save(task);
	}
	
	//READ
	public List<TaskEntity> getAllTasks() {
		return trepo.findAll();
	}
	
	//UPDATE
	@SuppressWarnings("finally")
	public TaskEntity putTaskDetails(int id, TaskEntity newTaskDetails) {
		TaskEntity task = new TaskEntity();
		try {
			task = trepo.findById(id).get();
			task.setTitle(newTaskDetails.getTitle());
			task.setDescription(newTaskDetails.getDescription());
			task.setDeadline(newTaskDetails.getDeadline());
			task.setPriority(newTaskDetails.getPriority());
			task.setStatus(newTaskDetails.getStatus());
		} catch (NoSuchElementException nex) {
			throw new NoSuchElementException("Task " + id + " not found.");
		} finally {
			return trepo.save(task);
		}
	}
	
	//DELETE
	public String deleteTask(int id) {
		String msg = "";
		if (trepo.findById(id).isPresent()) {
			trepo.deleteById(id);
			msg = "Task record successfully deleted.";
		} else {
			msg = id + " NOT FOUND!";
		}
		return msg;
	}
		
}
