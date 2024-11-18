package com.g2appdev.swift.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g2appdev.swift.entity.FlashcardSetEntity;
import com.g2appdev.swift.entity.UserEntity;

@Repository
public interface FlashcardSetRepository extends JpaRepository<FlashcardSetEntity, Integer>{
    List<FlashcardSetEntity> findByUser(UserEntity user);
}
