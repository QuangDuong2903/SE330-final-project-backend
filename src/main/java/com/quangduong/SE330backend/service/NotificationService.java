package com.quangduong.SE330backend.service;

import com.quangduong.SE330backend.dto.notification.NotificationDTO;
import org.aspectj.weaver.ast.Not;

import java.util.List;

public interface NotificationService {

    List<NotificationDTO> getNotifications();

    List<NotificationDTO> setRead(List<Long> ids);
    NotificationDTO acceptInvitation(long id);

}
