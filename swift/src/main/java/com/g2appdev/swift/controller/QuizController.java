package com.g2appdev.swift.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.g2appdev.swift.dto.UpdateUserScoreRequest;
import com.g2appdev.swift.entity.QuizEntity;
import com.g2appdev.swift.service.QuizService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/quiz")
public class QuizController {
	@Autowired
	QuizService qserv;
	
    @GetMapping("/print")
    public String print() {
        return "Hello, Quiz";
    }
    
    @PostMapping("/postquizrecord")
    public QuizEntity postQuizRecord(@RequestBody QuizEntity quiz) {
        return qserv.postQuizRecord(quiz);
    }

    @GetMapping("/getAllQuizzes")
    public List<QuizEntity> getAllQuizzes() {
        return qserv.getAllQuizzes();
    }

    
    @PutMapping("/putQuizDetails/{quiz_id}")
    public QuizEntity putQuizDetails(@PathVariable int quiz_id, @RequestBody QuizEntity newQuizDetails) {
    	return qserv.putQuizDetails(quiz_id, newQuizDetails);
    }
    
    @DeleteMapping("/deleteQuizDetails/{quiz_id}")
    public String deleteQuiz(@PathVariable int quiz_id) {
    	return qserv.deleteQuiz(quiz_id);
    }

    @GetMapping("/getQuizByUserId/{userId}")
    public List<QuizEntity> getFlashcardsByUserId(@PathVariable int userId) {
        return qserv.getFlashcardsByUserId(userId);
    }
    
        // Endpoint to update user score
    @PostMapping("/update-user-score")
    public ResponseEntity<String> updateUserScore(@RequestBody UpdateUserScoreRequest request) {
        try {
            String response = qserv.updateUserScore(request.getQuizId(), request.getUserScore());
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user score: " + ex.getMessage());
        }
    }

    // Endpoint to get quizzes associated with a specific user
    @GetMapping("/getQuizzesByUserId/{userId}")
    public List<QuizEntity> getQuizzesByUserId(@PathVariable int userId) {
        return qserv.getQuizzesByUserId(userId);
    }

    @GetMapping("/getAverageScoreByUserId/{userId}")
    public ResponseEntity<Double> getAverageScoreByUserId(@PathVariable int userId) {
        try {
            double averageScore = qserv.getAverageScoreByUserId(userId);
            return ResponseEntity.ok(averageScore);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0.0);
        }
    }
}