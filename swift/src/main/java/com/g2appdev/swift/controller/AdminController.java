package com.g2appdev.swift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.g2appdev.swift.entity.AdminEntity;
import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.service.AdminService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public AdminEntity registerAdmin(@RequestBody AdminEntity admin) {
        return adminService.registerAdmin(admin);
    }

    @PostMapping("/login")
    public AdminEntity loginAdmin(@RequestParam String username, @RequestParam String password) {
        return adminService.loginAdmin(username, password);
    }

    @GetMapping("/users")
    public List<UserEntity> getAllUsers(@RequestHeader("Authorization") String authorizationHeader) {
        // Extract the token from the 'Authorization' header
        String token = authorizationHeader.startsWith("Bearer ") 
                    ? authorizationHeader.substring(7) 
                    : authorizationHeader;

        // Log the token for debugging (you can remove this in production)
        System.out.println("Received token: " + token);
        
        // Call the service to fetch all users
        return adminService.getAllRegisteredUsers();
    }
}
