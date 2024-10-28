package com.g2appdev.swift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.g2appdev.swift.entity.DailyQuestEntity;
import com.g2appdev.swift.service.DailyQuestService;

@RestController
@RequestMapping(method = RequestMethod.GET,path="/api/dailyquest")
public class DailyQuestController {
	@Autowired
	DailyQuestService dserv;
	
	@GetMapping("/print")
	public String print() {
	return "Hello, This is for Daily Quest!";
	}
	
	//Create of CRUD
	@PostMapping("/postDailyQuestRecord")
		public DailyQuestEntity postDailyQuestRecord(@RequestBody DailyQuestEntity dailyQuest) {
		return dserv.postDailyQuestRecord(dailyQuest);
	}
	
	//Read of CRUD
	@GetMapping("/getAllDailyQuest")
	public List<DailyQuestEntity> getAllDailyQuest(){
		return dserv.getAllDailyQuest();
	}
	
	
	//Read of CRUD V2
	@GetMapping("/getAllDailyQuestV2")
	   public String getAllDailyQuestV2() {
	       List<DailyQuestEntity> dailyQuest = dserv.getAllDailyQuest();
	       StringBuilder response = new StringBuilder();

	       for(int i = 0; i < dailyQuest.size(); i++) {
	           DailyQuestEntity dailyquest = dailyQuest.get(i);
	           response.append("Id: "+dailyquest.getDailyQuestId()+"<br>")
	            	.append("Title: " + dailyquest.getTitle() + "<br>")
	            	.append("Description: " + dailyquest.getDescription() + "<br>")
	                .append("Status: " + dailyquest.getStatus() + "<br>")
	                .append("Coins Earned: " + dailyquest.getCoinsEarned() + "<br><br>");   
	   }
	       return response.toString();
	}
	
	
	//UPDATE
	@PutMapping("/putDailyQuestDetails")
	public DailyQuestEntity putDailyQuestDetails(@RequestParam int id, @RequestBody DailyQuestEntity newDailyQuestDetails) {
		return dserv.putDailyQuestDetails(id,newDailyQuestDetails);
	}
			
	//DELETE
	@DeleteMapping("/deleteDailyQuestDetails/{id}")
	public String deleteDailyQuest(@PathVariable int id) {
		return dserv.deleteDailyQuest(id);
	}
}
