package com.consoltant.consoltant.domain.notification.repository;

import com.consoltant.consoltant.domain.notification.entity.Notification;
import com.consoltant.consoltant.util.constant.NotificationType;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByUserId(Long id);
    List<Notification> findAllByUserIdAndIsReadFalse(Long id);
    Optional<Notification> findTopByNotificationTypeAndUserIdOrderByIdDesc(NotificationType notificationType, Long userId);

    List<Notification> findAllByNotificationTypeAndUserIdAndIsReadFalse(NotificationType notificationType, Long userId);

    @Transactional
    @Modifying
    @Query("UPDATE Notification n SET n.isRead = true WHERE n.notificationType = :notificationType AND n.user.id = :userId")
    void softDeleteByNotificationTypeAndUserId(NotificationType notificationType, Long userId);
}
