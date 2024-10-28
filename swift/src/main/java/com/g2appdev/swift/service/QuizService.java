package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.QuizEntity;
import com.g2appdev.swift.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository qrepo;
	
	public QuizService() {
		super();
	}
	
	public QuizEntity postQuizRecord(QuizEntity quiz) {
		return qrepo.save(quiz);
	}
	
	public List<QuizEntity>getAllQuizzes(){
		return qrepo.findAll();
	}
	
	@SuppressWarnings("finally")
	public QuizEntity putQuizDetails(int quiz_id, QuizEntity newQuizDetails) {
		QuizEntity quiz = new QuizEntity();
		try {
			//search the id number
			quiz = qrepo.findById(quiz_id).get();
			
			quiz.setTitle(newQuizDetails.getTitle());
			quiz.setQuestions(newQuizDetails.getQuestions());
			quiz.setScore(newQuizDetails.getScore());
		}catch(NoSuchElementException nex) {
			throw new NameNotFoundException ("Quiz " + quiz_id + "not found");
		}finally {
			return qrepo.save(quiz);
		}
	}
	
	@SuppressWarnings("unused")
	public String deleteQuiz(int quiz_id) {
		String msg = "";
		if(qrepo.findById(quiz_id) != null) {
			qrepo.deleteById(quiz_id);	
			msg = "Quiz Record successfully deleted";
		}else
			msg = quiz_id + "NOT found!";
		return msg;
	}
}