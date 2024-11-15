package com.g2appdev.swift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g2appdev.swift.entity.FlashcardSetEntity;
import java.util.List;

@Repository
public interface FlashcardSetRepository extends JpaRepository<FlashcardSetEntity, Integer>{
    public List<FlashcardSetEntity> findByUserUserID(int userID);
}
