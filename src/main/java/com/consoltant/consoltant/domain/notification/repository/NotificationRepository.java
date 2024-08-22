package com.consoltant.consoltant.domain.notification.repository;

import com.consoltant.consoltant.domain.notification.entity.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByUserId(Long id);
    List<Notification> findAllByUserIdAndIsReadFalse(Long id);
}
