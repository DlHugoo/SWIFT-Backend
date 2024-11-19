package com.g2appdev.swift.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.dto.UserDTO;
import com.g2appdev.swift.entity.InventoryEntity;
import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

	@Autowired
	UserRepository urepo;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	// Regex pattern to check for at least 8 characters and at least one special
	// character
	private static final String PASSWORD_PATTERN = "^(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,}$";

	// Password validation method
	private void validatePassword(String password) {
		if (password == null || !Pattern.matches(PASSWORD_PATTERN, password)) {
			throw new RuntimeException(
					"Password must be at least 8 characters long and contain at least one special character.");
		}
	}

	public UserService() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Create of CRUD
	public UserEntity postUserRecord(UserEntity user) {
		logger.info("Creating user record for: {}", user.getUsername());
		return urepo.save(user);
	}

	// Read of CRUD
	public List<UserEntity> getAllUsers() {
		logger.info("Retrieving all users");
		return urepo.findAll();
	}

	// Update of CRUD
	@SuppressWarnings("finally")
	public UserEntity putUserDetails(int userID, UserEntity newUserDetails) {
		UserEntity user = new UserEntity();
		try {
			user = urepo.findById(userID).orElseThrow(() -> new RuntimeException("User not found"));
			logger.info("Updating details for user ID: {}", userID);

			if (!user.getEmail().equals(newUserDetails.getEmail()) && urepo.existsByEmail(newUserDetails.getEmail())) {
				throw new RuntimeException("Email already exists");
			}
			// Update fields only if they have new values
			user.setUsername(newUserDetails.getUsername());
			user.setEmail(newUserDetails.getEmail());

			// Preserve the existing password if a new one is not provided
			if (newUserDetails.getPassword() != null && !newUserDetails.getPassword().isEmpty()) {
				validatePassword(newUserDetails.getPassword());
				user.setPassword(newUserDetails.getPassword());
			}

			user.setProgressData(newUserDetails.getProgressData());

		} finally {
			return urepo.save(user);
		}
	}

	// Delete of CRUD
	@SuppressWarnings("unused")
	public String deleteUser(int userID) {
		String msg = "";
		if (urepo.findById(userID) != null) {
			urepo.deleteById(userID);
			msg = "User Successfully deleted!";
			logger.info("User ID {} successfully deleted", userID);
		} else {
			logger.warn("User ID {} not found for deletion", userID);
			msg = userID + "NOT Found!";
		}
		return msg;
	}

	// Register
	public UserEntity registerUser(UserDTO userDTO) {
		// Check if user already exists
		if (urepo.findByUsername(userDTO.getUsername()) != null) {
			logger.warn("Attempt to register with existing username: {}", userDTO.getUsername());
			throw new RuntimeException("Username already exists");
		}
		if (urepo.existsByEmail(userDTO.getEmail())) {
			throw new RuntimeException("Email already exists");
		}

		// Validate password
		validatePassword(userDTO.getPassword());

		// Create new UserEntity and InventoryEntity
		UserEntity newUser = new UserEntity();
		newUser.setUsername(userDTO.getUsername());
		newUser.setEmail(userDTO.getEmail());
		newUser.setPassword(userDTO.getPassword());
		newUser.setProgressData(0); // Initial progress

		InventoryEntity newInventory = new InventoryEntity();
		newUser.setInventory(newInventory);
		newInventory.setUser(newUser);

		logger.info("Registering new user: {}", newUser.getUsername());
		return urepo.save(newUser);
	}

	public UserEntity authenticateUser(String username, String password) {
		UserEntity user = urepo.findByUsername(username);
		if (user == null) {
			logger.warn("User not found with username: {}", username);
			throw new RuntimeException("User not found");
		}

		if (!user.getPassword().equals(password)) { // Note: In production, use proper password hashing
			logger.warn("Invalid password for username: {}", username);
			throw new RuntimeException("Invalid password");
		}
		logger.info("User authenticated: {}", username);
		return user;
	}

	// UsernameExistence
	public boolean existsByUsername(String username) {
		return urepo.existsByUsername(username);
	}

	// Method to check if email already exists
	public boolean existsByEmail(String email) {
		return urepo.existsByEmail(email);
	}

}
