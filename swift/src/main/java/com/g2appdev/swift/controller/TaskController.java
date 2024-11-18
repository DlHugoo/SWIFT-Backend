package com.g2appdev.swift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.g2appdev.swift.entity.TaskEntity;
import com.g2appdev.swift.service.TaskService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(method = RequestMethod.GET, path = "/api/task")
public class TaskController {
	@Autowired
	TaskService sserv;

	// CREATE Task for specific user
	@PostMapping("/posttaskrecord")
public TaskEntity postTaskRecord(@RequestBody TaskEntity task) {
    return sserv.postTaskRecord(task);
}

	// READ all tasks
	@GetMapping("/getAllTasks")
	public List<TaskEntity> getAllTasks() {
		return sserv.getAllTasks();
	}

	// READ Tasks for a specific user
	@GetMapping("/getTasksByUser/{userId}")
	public List<TaskEntity> getTasksByUser(@PathVariable int userId) {
		return sserv.getTasksByUser(userId);
	}

	// UPDATE Task details
	@PutMapping("/putTaskDetails")
	public TaskEntity putTaskDetails(@RequestParam int id, @RequestBody TaskEntity newTaskDetails) {
		return sserv.putTaskDetails(id, newTaskDetails);
	}

	// UPDATE Task Status
	@PutMapping("/updateTaskStatus")
	public TaskEntity updateTaskStatus(@RequestParam int id, @RequestParam boolean status) {
		return sserv.updateTaskStatus(id, status);
	}

	// DELETE
	@DeleteMapping("/deleteTaskDetails/{taskId}")
	public String deleteTask(@PathVariable int taskId) {
		return sserv.deleteTask(taskId);
	}
}
