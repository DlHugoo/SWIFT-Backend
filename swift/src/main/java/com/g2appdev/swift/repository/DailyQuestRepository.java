package com.g2appdev.swift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.g2appdev.swift.entity.DailyQuestEntity;
import com.g2appdev.swift.entity.UserEntity;

import java.util.List;

@Repository
public interface DailyQuestRepository extends JpaRepository<DailyQuestEntity, Integer> {
    DailyQuestEntity findByTitle(String title);

    // Add this to find DailyQuests by UserEntity
    List<DailyQuestEntity> findByUser(UserEntity user);
}
