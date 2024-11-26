package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.DailyQuestEntity;
//import com.g2appdev.swift.entity.TaskEntity;
import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.repository.DailyQuestRepository;
import com.g2appdev.swift.repository.TaskRepository;
import com.g2appdev.swift.repository.UserRepository;

@Service
public class DailyQuestService {
    @Autowired
    DailyQuestRepository drepo;

    @Autowired
    UserRepository urepo;

    @Autowired
    TaskRepository trepo; // Repository for tasks

    public DailyQuestService() {
        super();
    }

    // Create a new Daily Quest Record
    public DailyQuestEntity postDailyQuestRecord(DailyQuestEntity dailyQuest) {
        if (dailyQuest.getUser() == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        UserEntity user = urepo.findById(dailyQuest.getUser().getUserID())
                .orElseThrow(() -> new NoSuchElementException("User with ID " + dailyQuest.getUser().getUserID() + " does not exist."));

        dailyQuest.setUser(user);
        return drepo.save(dailyQuest);
    }

    // Read all Daily Quests
    public List<DailyQuestEntity> getAllDailyQuest() {
        return drepo.findAll();
    }

    // Read Daily Quests by User ID
    public List<DailyQuestEntity> getDailyQuestByUserID(int userID) {
        UserEntity user = urepo.findById(userID)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + userID + " does not exist."));
        return drepo.findByUser(user);
    }

    // Update a Daily Quest Record
    public DailyQuestEntity putDailyQuestDetails(int id, DailyQuestEntity newDailyQuestDetails) {
        DailyQuestEntity dailyQuest = drepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Daily Quest with ID " + id + " not found"));

        dailyQuest.setTitle(newDailyQuestDetails.getTitle());
        dailyQuest.setDescription(newDailyQuestDetails.getDescription());
        dailyQuest.setStatus(newDailyQuestDetails.getStatus());
        dailyQuest.setCoinsEarned(newDailyQuestDetails.getCoinsEarned());

        return drepo.save(dailyQuest);
    }

    // Delete a Daily Quest Record
    public String deleteDailyQuest(int id) {
        if (drepo.existsById(id)) {
            drepo.deleteById(id);
            return "Daily Quest Record successfully deleted";
        } else {
            return id + " NOT found!";
        }
    }

    // Create Default Daily Quests for a User
    public void createDefaultDailyQuestsForUser(UserEntity user) {
        List<DailyQuestEntity> existingQuests = drepo.findByUser(user);

        boolean hasTodosQuest = existingQuests.stream().anyMatch(quest -> "Complete 5 to do's today".equals(quest.getTitle()));

        if (!hasTodosQuest) {
            DailyQuestEntity todosQuest = new DailyQuestEntity();
            todosQuest.setTitle("Complete 5 to do's today");
            todosQuest.setDescription("Complete 5 to do's today");
            todosQuest.setStatus("incomplete"); // Default status is incomplete
            todosQuest.setCoinsEarned(30);
            todosQuest.setUser(user);
            drepo.save(todosQuest);
        }
    }

    public void updateQuestStatusBasedOnToDoCompletion(int userID) {
        UserEntity user = urepo.findById(userID)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + userID + " does not exist."));
    
        // Find the "Complete 5 to do's today" quest
        DailyQuestEntity todosQuest = drepo.findByUser(user).stream()
                .filter(quest -> "Complete 5 to do's today".equals(quest.getTitle()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Complete 5 to do's today quest not found"));
    
        // Count completed tasks for the user
        long completedTasks = trepo.countByUserAndStatus(user, true);  // Count tasks with status as `true` (completed)
    
        // If 5 tasks are completed, mark the quest as complete
        if (completedTasks >= 5 && "incomplete".equals(todosQuest.getStatus())) {
            todosQuest.setStatus("complete");
            drepo.save(todosQuest);
        }
    }
}
