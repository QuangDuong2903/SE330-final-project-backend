package com.quangduong.SE330backend.repository;

import com.quangduong.SE330backend.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
