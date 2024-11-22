package com.g2appdev.swift.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2appdev.swift.entity.AdminEntity;
import com.g2appdev.swift.entity.UserEntity;
import com.g2appdev.swift.repository.AdminRepository;
import com.g2appdev.swift.repository.UserRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
	UserRepository urepo;

    public AdminEntity registerAdmin(AdminEntity admin) {
        if (adminRepository.existsByAdminUsername(admin.getAdminUsername())) {
            throw new RuntimeException("Admin username already exists!");
        }
        return adminRepository.save(admin);
    }

    public AdminEntity loginAdmin(String username, String password) {
        AdminEntity admin = adminRepository.findByAdminUsername(username);
        if (admin == null || !admin.getAdminPassword().equals(password)) {
            throw new RuntimeException("Invalid username or password!");
        }
        return admin;
    }
    

    public List<UserEntity> getAllRegisteredUsers() {
        // Logic to fetch all registered users (likely via the UserRepository)
        return urepo.findAll();
    }
}
