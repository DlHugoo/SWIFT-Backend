package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.DailyQuestEntity;
import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.repository.DailyQuestRepository;
import com.g2appdev.swift.repository.UserRepository;

@Service
public class DailyQuestService {
    @Autowired
    DailyQuestRepository drepo;

    @Autowired
    UserRepository urepo;
    
    public DailyQuestService() {
        super();
    }
    
    //Create of CRUD
    public DailyQuestEntity postDailyQuestRecord(DailyQuestEntity dailyQuest) {
        if (dailyQuest.getUser() == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        UserEntity user = urepo.findById(dailyQuest.getUser().getUserID())
                .orElseThrow(() -> new NoSuchElementException("DailyQ Quest with ID " + dailyQuest.getUser().getUserID() + " does not exist."));
                
        dailyQuest.setUser(user);
        return drepo.save(dailyQuest);
    }
        
    //Read of CRUD 
    public List<DailyQuestEntity> getAllDailyQuest(){
        return drepo.findAll();
    }
    
    //Read by User ID
    public List<DailyQuestEntity> getDailyQuestByUserID(int userID) {
        UserEntity user = urepo.findById(userID)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + userID + " does not exist."));
        return drepo.findByUser(user);
    }
    
    //Update of CRUD
    @SuppressWarnings("finally")
    public DailyQuestEntity putDailyQuestDetails(int id, DailyQuestEntity newDailyQuestDetails) {
        DailyQuestEntity dailyQuest = new DailyQuestEntity();
            
        try {
            dailyQuest = drepo.findById(id).get();
                
            dailyQuest.setTitle(newDailyQuestDetails.getTitle());
            dailyQuest.setDescription(newDailyQuestDetails.getDescription());
            dailyQuest.setStatus(newDailyQuestDetails.getStatus());
            dailyQuest.setCoinsEarned(newDailyQuestDetails.getCoinsEarned());
        }catch(NoSuchElementException nex) {
            throw new NameNotFoundException("Daily Quest "+ id + " not found");
        }finally {
            return drepo.save(dailyQuest);
        }
    }
    
    //Delete of CRUD
    @SuppressWarnings("unused")
    public String deleteDailyQuest(int id) {
        String msg = "";
        
        if(drepo.findById(id) != null) {
            drepo.deleteById(id);
            msg = "Daily Quest Record successfully deleted";
        }else
            msg = id + " NOT found!";
        return msg;
    }

    public void createDefaultDailyQuestsForUser(UserEntity user) {
        // Check if default quests are already present
        List<DailyQuestEntity> existingQuests = drepo.findByUser(user);
        
        // To determine if default quests are already created for the user
        boolean hasLoginQuest = existingQuests.stream().anyMatch(quest -> "Log in".equals(quest.getTitle()));
        boolean hasTodosQuest = existingQuests.stream().anyMatch(quest -> "To Do's".equals(quest.getTitle()));
        boolean hasQuizzesQuest = existingQuests.stream().anyMatch(quest -> "Quizzes".equals(quest.getTitle()));
    
        // Create missing quests
        if (!hasLoginQuest) {
            DailyQuestEntity loginQuest = new DailyQuestEntity();
            loginQuest.setTitle("Log in");
            loginQuest.setDescription("Log in today");
            loginQuest.setStatus("Pending");
            loginQuest.setCoinsEarned(30);
            loginQuest.setUser(user);
            drepo.save(loginQuest);
        }
    
        if (!hasTodosQuest) {
            DailyQuestEntity todosQuest = new DailyQuestEntity();
            todosQuest.setTitle("To Do's");
            todosQuest.setDescription("Complete 5 to do's today");
            todosQuest.setStatus("Pending");
            todosQuest.setCoinsEarned(30);
            todosQuest.setUser(user);
            drepo.save(todosQuest);
        }
    
        if (!hasQuizzesQuest) {
            DailyQuestEntity quizzesQuest = new DailyQuestEntity();
            quizzesQuest.setTitle("Quizzes");
            quizzesQuest.setDescription("Complete 2 Flashcard Quiz today");
            quizzesQuest.setStatus("Pending");
            quizzesQuest.setCoinsEarned(50);
            quizzesQuest.setUser(user);
            drepo.save(quizzesQuest);
        }
    }
}
