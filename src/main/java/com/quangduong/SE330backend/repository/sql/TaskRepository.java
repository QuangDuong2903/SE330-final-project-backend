package com.quangduong.SE330backend.repository.sql;

import com.quangduong.SE330backend.entity.sql.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
