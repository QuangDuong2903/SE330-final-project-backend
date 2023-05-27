package com.quangduong.SE330backend.repository.sql;

import com.quangduong.SE330backend.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
