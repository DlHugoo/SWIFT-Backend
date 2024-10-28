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

import com.g2appdev.swift.entity.ProgressTrackingEntity;
import com.g2appdev.swift.service.ProgressTrackingService;

@RestController
@RequestMapping(method = RequestMethod.GET,path="/api/progresstracking")
public class ProgressTrackingController {

	@Autowired
	ProgressTrackingService pserv;
	
	@GetMapping("/print")
		public String print() {
			return "Hello, This is for Progress Tracking!";
	}
	
	//Create of CRUD
	@PostMapping("/postProgressTrackingRecord")
		public ProgressTrackingEntity postProgressTrackingRecord(@RequestBody ProgressTrackingEntity progressTracking) {
			return pserv.postProgressTrackingRecord(progressTracking);
	}
	
	//Read of CRUD
		@GetMapping("/getAllProgressTracking")
		public List<ProgressTrackingEntity> getAllProgressTracking(){
			return pserv.getAllProgressTracking();
	}
		
	//Read of CRUD V2
	@GetMapping("/getAllProgressTrackingV2")
		  public String getAllProgressTrackingV2() {
		      List<ProgressTrackingEntity> progressTracking = pserv.getAllProgressTracking();
		      StringBuilder response = new StringBuilder();

		      for(int i = 0; i < progressTracking.size(); i++) {
		    	  ProgressTrackingEntity progresstracking = progressTracking.get(i);
		          response.append("Id: "+progresstracking.getProgressId()+"<br>")
		            .append("Completion Rate: " + progresstracking.getCompletionRate() + "<br>")
		            .append("Learning Progress: " + progresstracking.getLearningProgress() + "<br>")
		             .append("Quiz Scores: " + progresstracking.getQuizScores() + "<br>")
		             .append("Time Spent: " + progresstracking.getTimeSpent() + "<br><br>");   
		   }
		       return response.toString();
		}
		
	//UPDATE
	@PutMapping("/putProgressTrackingDetails")
	public ProgressTrackingEntity putProgressTrackingDetails(@RequestParam int id, @RequestBody ProgressTrackingEntity newProgressTrackingDetails) {
		return pserv.putProgressTrackingDetails(id,newProgressTrackingDetails);
	}
				
	//DELETE
	@DeleteMapping("/deleteProgressTrackingDetails/{id}")
	public String deleteProgressTracking(@PathVariable int id) {
		return pserv.deleteProgressTracking(id);
	}
		
}
