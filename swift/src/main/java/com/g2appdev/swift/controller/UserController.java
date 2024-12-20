package com.g2appdev.swift.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.g2appdev.swift.dto.LoginRequest;
import com.g2appdev.swift.dto.UserDTO;
import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.service.UserService;
import com.g2appdev.swift.service.DailyQuestService; // Import DailyQuestService
import com.g2appdev.swift.utils.JwtUtils;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userv;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private DailyQuestService dailyQuestService; // Add dependency to DailyQuestService

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/print")
    public String print() {
        return "Hello, User";
    }

    // Create of CRUD
    @PostMapping("/post")
    public UserEntity postUserRecord(@RequestBody UserEntity user) {
        return userv.postUserRecord(user);
    }

    // Read of CRUD
    @GetMapping("/get")
    public List<UserEntity> getAllUsers() {
        return userv.getAllUsers();
    }

    // Update of CRUD
    @PutMapping("/put")
    public ResponseEntity<?> putUserDetails(@RequestParam int userID, @RequestBody UserEntity newUserDetails) {
        try {
            // Validate password format if a new password is provided
            if (newUserDetails.getPassword() != null && !newUserDetails.getPassword().isEmpty()) {
                if (!isValidPassword(newUserDetails.getPassword())) {
                    Map<String, String> error = new HashMap<>();
                    error.put("error",
                            "Password must be at least 8 characters long and contain at least one special character.");
                    return ResponseEntity.badRequest().body(error);
                }
            }

            // Update user details
            UserEntity updatedUser = userv.putUserDetails(userID, newUserDetails);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Delete of CRUD
    @DeleteMapping("/delete/{userID}")
    public String deleteUser(@PathVariable int userID) {
        return userv.deleteUser(userID);
    }

    // Password Validation utility
    private boolean isValidPassword(String password) {
        // At least 8 characters and contains at least one special character
        String passwordPattern = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";
        return password != null && password.matches(passwordPattern);
    }

    // Registration Endpoint using UserDTO
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        logger.info("Attempting to register user with username: {}", userDTO.getUsername());
        try {
            // Validate password format
            if (!isValidPassword(userDTO.getPassword())) {
                Map<String, String> error = new HashMap<>();
                error.put("error",
                        "Password must be at least 8 characters long and contain at least one special character.");
                return ResponseEntity.badRequest().body(error);
            }

            // Register new user using UserDTO and convert to UserEntity in service layer
            UserEntity registeredUser = userv.registerUser(userDTO);

            // Generate JWT token
            String token = jwtUtils.generateToken(registeredUser.getUsername());

            // Prepare response with token and user ID
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Registration successful");
            response.put("userId", registeredUser.getUserID());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Login Endpoint using LoginRequest
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate the user based on LoginRequest (username and password)
            UserEntity user = userv.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());

            // Generate the JWT token
            String token = jwtUtils.generateToken(user.getUsername());

            // Update the daily quest for logging in
            dailyQuestService.updateQuestStatusForLogin(user.getUserID()); // This ensures the login quest is marked
                                                                           // complete

            // Prepare response with token and user details
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login successful");
            response.put("token", token);
            response.put("userId", user.getUserID());
            response.put("username", user.getUsername());
            response.put("email", user.getEmail());
            response.put("coinBalance", user.getCoinBalance());

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Check if Username Exists Endpoint
    @GetMapping("/exists")
    public ResponseEntity<Map<String, Boolean>> checkUsernameExists(@RequestParam String username) {
        boolean exists = userv.existsByUsername(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    // Check if Email Exists Endpoint
    @GetMapping("/exists/email")
    public ResponseEntity<Map<String, Boolean>> checkEmailExists(@RequestParam String email) {
        boolean exists = userv.existsByEmail(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return ResponseEntity.ok(response);
    }

    // getting coinbalance
    @GetMapping("/{userID}/coin-balance")
    public ResponseEntity<Integer> getCoinBalance(@PathVariable int userID) {
        try {
            int coinBalance = userv.getCoinBalanceByUserId(userID);
            return ResponseEntity.ok(coinBalance);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0); // Return 0 or an appropriate error response
        }
    }
}
