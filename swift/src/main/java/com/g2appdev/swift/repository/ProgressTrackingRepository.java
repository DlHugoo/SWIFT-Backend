package com.g2appdev.swift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.g2appdev.swift.entity.ProgressTrackingEntity;

@Repository
public interface ProgressTrackingRepository extends JpaRepository<ProgressTrackingEntity, Integer> {
	ProgressTrackingEntity findByCompletionRate(float completionRate);
}
