package com.consoltant.consoltant.domain.notification.service;

import com.consoltant.consoltant.domain.notification.entity.Notification;
import com.consoltant.consoltant.domain.notification.repository.NotificationRepository;
import java.util.List;
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
    public List<Notification> findAllByUserIdAndIsReadFalse(Long userId) {
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
}
