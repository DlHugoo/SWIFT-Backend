package com.g2appdev.swift.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(method = RequestMethod.GET,path="/api/user")
public class UserController {
	
	@Autowired
	UserService userv;
	
	@GetMapping("/print")
	public String print() {
		return "Hello, User";
	}
	
	//Create of CRUD
	@PostMapping("/post")
	public UserEntity postUserRecord(@RequestBody UserEntity user) {
		return userv.postUserRecord(user);
	}
	
	//Read of CRUD
	@GetMapping("/get")
	public List<UserEntity> getAllUsers(){
		return userv.getAllUsers();
	}
	
	//Update of CRUD
	@PutMapping("/put")
	public UserEntity putUserDetails(@RequestParam int userID, @RequestBody UserEntity newUserDetails) {
		return userv.putUserDetails(userID, newUserDetails);
	}
	
	//Delete of CRUD
	@DeleteMapping("/delete/{userID}")
	public String deleteUser(@PathVariable int userID) {
		return userv.deleteUser(userID);
	}
	
	@PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserEntity user) {
        try {
            UserEntity registeredUser = userv.registerUser(user);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Registration successful");
            response.put("userId", registeredUser.getUserID());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        try {
            UserEntity user = userv.authenticateUser(
                credentials.get("username"),
                credentials.get("password")
            );
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("userId", user.getUserID());
            response.put("username", user.getUsername());
            response.put("email", user.getEmail());
            response.put("progressData", user.getProgressData());
            
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
	
}
