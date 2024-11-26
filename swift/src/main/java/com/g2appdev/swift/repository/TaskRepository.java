package com.g2appdev.swift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.g2appdev.swift.entity.TaskEntity;
import com.g2appdev.swift.entity.UserEntity;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    TaskEntity findByTaskId(int taskId);

    // Count the tasks that are completed by a specific user
    long countByUserAndStatus(UserEntity user, boolean status);

    // Find all tasks by user
    List<TaskEntity> findByUser(UserEntity user);
}
