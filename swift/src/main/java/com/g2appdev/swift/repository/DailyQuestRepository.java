package com.g2appdev.swift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.g2appdev.swift.entity.DailyQuestEntity;

@Repository
public interface DailyQuestRepository extends JpaRepository<DailyQuestEntity, Integer> {
	DailyQuestEntity findByTitle(String title);
}
