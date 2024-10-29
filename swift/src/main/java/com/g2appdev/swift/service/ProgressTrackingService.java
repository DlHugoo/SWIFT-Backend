package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.ProgressTrackingEntity;
import com.g2appdev.swift.repository.ProgressTrackingRepository;

@Service
public class ProgressTrackingService {
	@Autowired
	ProgressTrackingRepository prepo;
	
	public ProgressTrackingService() {
		super();
	}
	
	//Create of CRUD
	public ProgressTrackingEntity postProgressTrackingRecord(ProgressTrackingEntity progressTracking) {
		//TeacherEntity teacher = trepo.findById(student.getTeacher().getTeacherID())
			//	.orElseThrow(()-> new NoSuchElementException("Teacher with ID "+student.getTeacher().getTeacherID()+ "does not exist."));
		//student.setTeacher(teacher);
		return prepo.save(progressTracking);
	}
		
	//Read of CRUD 
	public List<ProgressTrackingEntity> getAllProgressTracking(){
		return prepo.findAll();
	}
	
	//Update of CRUD
	@SuppressWarnings("finally")
	public ProgressTrackingEntity putProgressTrackingDetails(int id, ProgressTrackingEntity newProgressTrackingDetails) {
		ProgressTrackingEntity progressTracking = new ProgressTrackingEntity();
				
		try {
			progressTracking = prepo.findById(id).get();
					
			progressTracking.setCompletionRate(newProgressTrackingDetails.getCompletionRate());
			progressTracking.setLearningProgress(newProgressTrackingDetails.getLearningProgress());
			progressTracking.setQuizScores(newProgressTrackingDetails.getQuizScores());
			progressTracking.setTimeSpent(newProgressTrackingDetails.getTimeSpent());
		}catch(NoSuchElementException nex) {
			throw new NameNotFoundException("Progress Tracking Record "+ id + "not found");
		}finally {
			return prepo.save(progressTracking);
		}
	}
	
	//Delete of CRUD
			@SuppressWarnings("unused")
			public String deleteProgressTracking(int id) {
				String msg = "";
				
				if(prepo.findById(id)!=null) {
					prepo.deleteById(id);
					msg = "Progress Tracking Record successfully deleted";
				}else
					msg = id + "NOT found!";
				return msg;
			}
}