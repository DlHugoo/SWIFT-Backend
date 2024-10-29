package com.g2appdev.swift.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.repository.UserRepository;



@Service
public class UserService {
	
	@Autowired
	UserRepository urepo;
	
	public UserService() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Create of CRUD
	public UserEntity postUserRecord(UserEntity user) {
		return urepo.save(user);
	}
	
	//Read of CRUD 
	public List<UserEntity>getAllUsers(){
		return urepo.findAll();
	}
	
	//Update of CRUD
	@SuppressWarnings("finally")
	public UserEntity putUserDetails(int userID, UserEntity newUserDetails) {
		UserEntity user = new UserEntity();
		try{
			user = urepo.findById(userID).get();
			
			user.setUsername(newUserDetails.getUsername());
			user.setEmail(newUserDetails.getEmail());
			user.setPassword(newUserDetails.getPassword());
			user.setAvatarID(newUserDetails.getAvatarID());
			user.setProgressData(newUserDetails.getProgressData());
		}finally {
			return urepo.save(user);
		}
		
	}
	
	//Delete of CRUD
	@SuppressWarnings("unused")
	public String deleteUser(int userID) {
		String msg = "";
		if (urepo.findById(userID) != null ) {
			urepo.deleteById(userID);
			msg = "User Successfully deleted!";
		} else {
			msg = userID + "NOT Found!";
		}
		return msg;
	}
	
	 public UserEntity registerUser(UserEntity user) {
	        // Check if user already exists
	        if (urepo.findByUsername(user.getUsername()) != null) {
	            throw new RuntimeException("Username already exists");
	        }
	        
	        // Set default values for new users
	        user.setAvatarID(1); // Default avatar
	        user.setProgressData(0); // Initial progress
	        
	        return urepo.save(user);
	    }
	    
	    public UserEntity authenticateUser(String username, String password) {
	        UserEntity user = urepo.findByUsername(username);
	        if (user == null) {
	            throw new RuntimeException("User not found");
	        }
	        
	        if (!user.getPassword().equals(password)) { // Note: In production, use proper password hashing
	            throw new RuntimeException("Invalid password");
	        }
	        
	        return user;
	    }
	
}
