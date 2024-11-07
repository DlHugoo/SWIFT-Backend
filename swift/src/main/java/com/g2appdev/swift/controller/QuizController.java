package com.g2appdev.swift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}