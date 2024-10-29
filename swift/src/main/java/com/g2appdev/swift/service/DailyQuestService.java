package com.g2appdev.swift.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.DailyQuestEntity;
import com.g2appdev.swift.repository.DailyQuestRepository;

@Service
public class DailyQuestService {
	@Autowired
	DailyQuestRepository drepo;
	
	public DailyQuestService() {
		super();
	}
	
	//Create of CRUD
	public DailyQuestEntity postDailyQuestRecord(DailyQuestEntity dailyQuest) {
		//TeacherEntity teacher = trepo.findById(student.getTeacher().getTeacherID())
			//	.orElseThrow(()-> new NoSuchElementException("Teacher with ID "+student.getTeacher().getTeacherID()+ "does not exist."));
		//student.setTeacher(teacher);
		return drepo.save(dailyQuest);
	}
		
	//Read of CRUD 
	public List<DailyQuestEntity> getAllDailyQuest(){
		return drepo.findAll();
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
			throw new NameNotFoundException("Daily Quest "+ id + "not found");
		}finally {
			return drepo.save(dailyQuest);
		}
	}
	
	//Delete of CRUD
		@SuppressWarnings("unused")
		public String deleteDailyQuest(int id) {
			String msg = "";
			
			if(drepo.findById(id)!=null) {
				drepo.deleteById(id);
				msg = "Daily Quest Record successfully deleted";
			}else
				msg = id + "NOT found!";
			return msg;
		}
}
