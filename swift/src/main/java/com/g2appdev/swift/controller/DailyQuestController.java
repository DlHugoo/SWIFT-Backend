package com.g2appdev.swift.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.g2appdev.swift.entity.DailyQuestEntity;
import com.g2appdev.swift.service.DailyQuestService;

import java.util.List;

@RestController
@RequestMapping("/api/dailyquest")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React frontend
public class DailyQuestController {
    @Autowired
    DailyQuestService dserv;

    // Create a new Daily Quest Record
    @PostMapping("/postDailyQuestRecord")
    public DailyQuestEntity postDailyQuestRecord(@RequestBody DailyQuestEntity dailyQuest) {
        return dserv.postDailyQuestRecord(dailyQuest);
    }

    // Get all Daily Quests
    @GetMapping("/getAllDailyQuest")
    public List<DailyQuestEntity> getAllDailyQuest() {
        return dserv.getAllDailyQuest();
    }

    // Get Daily Quests by User ID
    @GetMapping("/getDailyQuestByUserID/{userID}")
    public List<DailyQuestEntity> getDailyQuestByUserID(@PathVariable int userID) {
        return dserv.getDailyQuestByUserID(userID);
    }

    // Update Daily Quest Details
    @PutMapping("/putDailyQuestDetails")
    public DailyQuestEntity putDailyQuestDetails(@RequestParam int id, @RequestBody DailyQuestEntity newDailyQuestDetails) {
        return dserv.putDailyQuestDetails(id, newDailyQuestDetails);
    }

    // Delete a Daily Quest Record
    @DeleteMapping("/deleteDailyQuestDetails/{id}")
    public String deleteDailyQuest(@PathVariable int id) {
        return dserv.deleteDailyQuest(id);
    }

    // Update Quest Status based on Task Completion
    @PutMapping("/updateQuestStatus/{userID}")
    public String updateQuestStatus(@PathVariable int userID) {
        dserv.updateQuestStatusBasedOnToDoCompletion(userID);
        return "Quest status updated based on task completion.";
    }

    // Update Quest Status for Login
    @PutMapping("/updateLoginQuestStatus/{userID}")
    public String updateLoginQuestStatus(@PathVariable int userID) {
        dserv.updateQuestStatusForLogin(userID);
        return "Login quest status updated.";
    }

    // Update Quest Status for Quiz
    @PutMapping("/updateQuizQuestStatus/{userID}")
    public String updateQuizQuestStatus(@PathVariable int userID) {
        dserv.updateQuizQuestStatus(userID);
        return "Quiz quest status updated.";
    }
}
