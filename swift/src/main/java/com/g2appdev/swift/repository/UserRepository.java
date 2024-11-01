package com.g2appdev.swift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g2appdev.swift.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	UserEntity findByUsername(String username);
	boolean existsByUsername(String username);
}
