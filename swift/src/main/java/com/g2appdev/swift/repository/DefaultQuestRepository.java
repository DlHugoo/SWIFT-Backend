package com.g2appdev.swift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.g2appdev.swift.entity.DefaultQuestEntity;

@Repository
public interface DefaultQuestRepository extends JpaRepository<DefaultQuestEntity, Integer> {
}
