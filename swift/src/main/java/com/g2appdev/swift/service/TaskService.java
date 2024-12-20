package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.TaskEntity;
import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.repository.TaskRepository;
import com.g2appdev.swift.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class TaskService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    TaskRepository trepo;

    @Autowired
    UserRepository urepo;

    @Autowired
    DailyQuestService dailyQuestService; // Inject DailyQuestService to update quests based on task completion

    public TaskService() {
        super();
    }

    // CREATE Task for specific user
    public TaskEntity postTaskRecord(TaskEntity task) {
        if (task.getUser() == null || task.getUser().getUserID() == 0) {
            throw new IllegalArgumentException("User cannot be null or missing.");
        }
        UserEntity user = urepo.findById(task.getUser().getUserID())
                .orElseThrow(() -> new NoSuchElementException("User " + task.getUser().getUserID() + " not found"));

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

    // UPDATE Task details
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

    // UPDATE Task Status and update corresponding quest status if needed
    public TaskEntity updateTaskStatus(int id, boolean status) {
        TaskEntity task = trepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Task " + id + " not found"));
        
        // Update task status
        task.setStatus(status);
        TaskEntity updatedTask = trepo.save(task);

        // Trigger quest update if task status is marked as complete
        if (status && task.getUser() != null) {
            dailyQuestService.updateQuestStatusBasedOnToDoCompletion(task.getUser().getUserID());
        }

        return updatedTask;
    }

    // DELETE Task by ID
    public String deleteTask(int taskId) {
        if (trepo.findById(taskId).isPresent()) {
            trepo.deleteById(taskId);
            return "Task record successfully deleted.";
        } else {
            throw new NoSuchElementException("Task " + taskId + " not found.");
        }
    }
}
