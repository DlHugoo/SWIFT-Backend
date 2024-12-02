package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.DailyQuestEntity;
import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.entity.QuizEntity;
import com.g2appdev.swift.repository.DailyQuestRepository;
import com.g2appdev.swift.repository.TaskRepository;
import com.g2appdev.swift.repository.UserRepository;
import com.g2appdev.swift.repository.QuizRepository;

@Service
public class DailyQuestService {
    @Autowired
    DailyQuestRepository drepo;

    @Autowired
    UserRepository urepo;

    @Autowired
    TaskRepository trepo;

    @Autowired
    QuizRepository qrepo;

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

        boolean hasTodosQuest = existingQuests.stream().anyMatch(quest -> "To Do's".equals(quest.getTitle()));
        boolean hasLoginQuest = existingQuests.stream().anyMatch(quest -> "Login".equals(quest.getTitle()));
        boolean hasQuizQuest = existingQuests.stream().anyMatch(quest -> "Quiz".equals(quest.getTitle()));

        // Create "Complete 5 to do's today" quest
        if (!hasTodosQuest) {
            DailyQuestEntity todosQuest = new DailyQuestEntity();
            todosQuest.setTitle("To Do's");
            todosQuest.setDescription("Complete 5 to do's today");
            todosQuest.setStatus("incomplete"); // Default status is incomplete
            todosQuest.setCoinsEarned(30);
            todosQuest.setUser(user);
            drepo.save(todosQuest);
        }

        // Create "Login Today" quest
        if (!hasLoginQuest) {
            DailyQuestEntity loginQuest = new DailyQuestEntity();
            loginQuest.setTitle("Login");
            loginQuest.setDescription("Login Today");
            loginQuest.setStatus("incomplete"); // Default status is incomplete
            loginQuest.setCoinsEarned(20);
            loginQuest.setUser(user);
            drepo.save(loginQuest);
        }

        // Create "Quiz" quest
        if (!hasQuizQuest) {
            DailyQuestEntity quizQuest = new DailyQuestEntity();
            quizQuest.setTitle("Quiz");
            quizQuest.setDescription("Answer at least one quiz today");
            quizQuest.setStatus("incomplete"); // Default status is incomplete
            quizQuest.setCoinsEarned(50);
            quizQuest.setUser(user);
            drepo.save(quizQuest);
        }
    }

    // Update Quest Status Based on To Do Completion and Update Coins
    public void updateQuestStatusBasedOnToDoCompletion(int userID) {
        UserEntity user = urepo.findById(userID)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + userID + " does not exist."));

        // Find the "Complete 5 to do's today" quest
        DailyQuestEntity todosQuest = drepo.findByUser(user).stream()
                .filter(quest -> "To Do's".equals(quest.getTitle()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Complete 5 to do's today quest not found"));

        // Count completed tasks for the user
        long completedTasks = trepo.countByUserAndStatus(user, true); // Count tasks with status as `true` (completed)

        // If 5 tasks are completed, mark the quest as complete and update user's coins
        if (completedTasks >= 5 && "incomplete".equals(todosQuest.getStatus())) {
            todosQuest.setStatus("complete");
            drepo.save(todosQuest);

            // Update the user's coins
            user.setCoinBalance(user.getCoinBalance() + todosQuest.getCoinsEarned());
            urepo.save(user);
        }
    }

    // Update Quest Status for Login and Update Coins
    public void updateQuestStatusForLogin(int userID) {
        UserEntity user = urepo.findById(userID)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + userID + " does not exist."));

        // Find the "Login Today" quest
        DailyQuestEntity loginQuest = drepo.findByUser(user).stream()
                .filter(quest -> "Login".equals(quest.getTitle()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Login Today quest not found"));

        // If the quest is incomplete, mark it as complete and update user's coins
        if ("incomplete".equals(loginQuest.getStatus())) {
            loginQuest.setStatus("complete");
            drepo.save(loginQuest);

            // Update the user's coins
            user.setCoinBalance(user.getCoinBalance() + loginQuest.getCoinsEarned());
            urepo.save(user);
        }
    }

    // Check if a user has answered a quiz today
    public boolean hasUserAnsweredQuizToday(int userID) {
        UserEntity user = urepo.findById(userID)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + userID + " does not exist."));

        // Fetch quizzes where userScore > 0
        List<QuizEntity> answeredQuizzes = qrepo.findByFlashcardset_User(user);
        return answeredQuizzes.stream().anyMatch(quiz -> quiz.getUserScore() > 0);
    }

    // Update Quest Status for Quiz Completion
    public void updateQuizQuestStatus(int userID) {
        UserEntity user = urepo.findById(userID)
                .orElseThrow(() -> new NoSuchElementException("User with ID " + userID + " does not exist."));

        // Find the "Quiz" quest
        DailyQuestEntity quizQuest = drepo.findByUser(user).stream()
                .filter(quest -> "Quiz".equals(quest.getTitle()))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Quiz quest not found"));

        // Check if the user has answered any quiz today
        boolean hasAnsweredQuizToday = hasUserAnsweredQuizToday(userID);

        // If the quest is incomplete and user answered a quiz, mark it complete
        if (hasAnsweredQuizToday && "incomplete".equals(quizQuest.getStatus())) {
            quizQuest.setStatus("complete");
            drepo.save(quizQuest);

            // Update the user's coins
            user.setCoinBalance(user.getCoinBalance() + quizQuest.getCoinsEarned());
            urepo.save(user);
        }
    }
}
