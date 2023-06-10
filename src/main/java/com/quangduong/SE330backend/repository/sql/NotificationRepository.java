package com.quangduong.SE330backend.repository.sql;

import com.quangduong.SE330backend.constant.NotificationType;
import com.quangduong.SE330backend.entity.sql.NotificationEntity;
import com.quangduong.SE330backend.entity.sql.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    NotificationEntity findOneByTypeAndBoardIdAndUserAndIsAcceptAndIsReject(NotificationType type, long boardId, UserEntity user, boolean isAccept, boolean isReject);
}
