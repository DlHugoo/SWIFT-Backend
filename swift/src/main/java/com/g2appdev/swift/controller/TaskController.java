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

import com.g2appdev.swift.entity.TaskEntity;
import com.g2appdev.swift.service.TaskService;

@RestController
@RequestMapping(method = RequestMethod.GET,path="/api/task")
public class TaskController {
	@Autowired
	TaskService sserv;
	@GetMapping("/print")
	public String print() {
		return "Hello, Laboratory Exercise";
	}
	//CREATE
	@PostMapping("/posttaskrecord")
	public TaskEntity postTaskRecord(@RequestBody TaskEntity task) {
		return sserv.postTaskRecord(task);
	}
	//READ
	@GetMapping("/getAllTasks")
	public List<TaskEntity>getAllTasks(){
		return sserv.getAllTasks();
	}
	//UPDATE
	@PutMapping("/putTaskDetails")
	public TaskEntity putTaskDetails(@RequestParam int id, @RequestBody TaskEntity newTaskDetails) {
		return sserv.putTaskDetails(id,newTaskDetails);
	}
	//DELETE
	@DeleteMapping("/deleteTaskDetails/{id}")
	public String deleteTask(@PathVariable int id) {
		return sserv.deleteTask(id);
	}
}
