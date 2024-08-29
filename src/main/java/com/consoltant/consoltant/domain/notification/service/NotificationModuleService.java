package com.consoltant.consoltant.domain.notification.service;

import com.consoltant.consoltant.domain.notification.entity.Notification;
import com.consoltant.consoltant.domain.notification.repository.NotificationRepository;
import com.consoltant.consoltant.util.constant.NotificationType;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationModuleService {
    private final NotificationRepository notificationRepository;

    // Notification ID로 단일 조회
    public Notification findById(Long id) {
        return notificationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Notification ID"));
    }

    // 유저 ID로 모든 Notification 조회
    public List<Notification> findAllByUserId(Long userId) {
        return notificationRepository.findAllByUserId(userId);
    }

    // 유저 ID로 읽지 않은 Notification 조회
    public Optional<List<Notification>> findAllByUserIdAndIsReadFalse(Long userId) {
        return notificationRepository.findAllByUserIdAndIsReadFalse(userId);
    }

    // Notification 저장
    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    // Notification 삭제
    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

    //매칭 하나만 조회
    public Optional<Notification> findTopByNotificationTypeAndUserIdOrderByIdDesc(Long userId) {
        return notificationRepository.findTopByNotificationTypeAndUserIdOrderByIdDesc(NotificationType.PORTFOLIO_MATCHING, userId);
    }

    //댓글만 조회
    public Optional<List<Notification>> findAllByNotificationTypeAndUserIdAndIsReadFalse(Long userId){
        return notificationRepository.findAllByNotificationTypeAndUserIdAndIsReadFalse(NotificationType.PORTFOLIO_COMMENT, userId);
    }

    //댓글 알림 전부 삭제
    public void softDeleteByNotificationTypeAndUserId(Long userId){
        notificationRepository.softDeleteByNotificationTypeAndUserId(NotificationType.PORTFOLIO_COMMENT, userId);
    }
}
