package com.consoltant.consoltant.domain.notification.service;

import com.consoltant.consoltant.domain.notification.dto.NotificationRequestDto;
import com.consoltant.consoltant.domain.notification.dto.NotificationResponseDto;
import com.consoltant.consoltant.domain.notification.entity.Notification;
import com.consoltant.consoltant.domain.notification.mapper.NotificationMapper;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationModuleService notificationModuleService;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;

    // 단일 조회
    public NotificationResponseDto findById(Long id) {
        return notificationMapper.toNotificationResponseDto(notificationModuleService.findById(id));
    }

    // 유저 ID로 알림 리스트 조회
    public List<NotificationResponseDto> findAllByUserId(Long userId){
        return notificationModuleService.findAllByUserId(userId).stream()
            .map(notificationMapper::toNotificationResponseDto)
            .toList();
    }

    //유저 ID로 읽지않은 알림만 조회
    public List<NotificationResponseDto> findAllByUserIdAndIsReadFalse(Long userId){
        return notificationModuleService.findAllByUserIdAndIsReadFalse(userId).stream()
            .map(notificationMapper::toNotificationResponseDto)
            .toList();
    }

    // 등록
    public NotificationResponseDto save(NotificationRequestDto notificationRequestDto) {
        User user = userRepository.findById(notificationRequestDto.getUserId()).orElseThrow();
        Notification notification = notificationMapper.toNotification(notificationRequestDto);
        notification.setUser(user);
        return notificationMapper.toNotificationResponseDto(notificationModuleService.save(notification));
    }

    // 알림 읽기
    public NotificationResponseDto read(Long id){
        Notification notification = notificationModuleService.findById(id);
        notification.read();
        return notificationMapper.toNotificationResponseDto(notificationModuleService.save(notification));
    }

    // 삭제
    public void delete(Long id){
        notificationModuleService.delete(id);
    }

    //선후배 매칭 하나
    public NotificationResponseDto findTopByNotificationTypeAndUserIdOrderByIdDesc(Long userId) {
        return notificationMapper.toNotificationResponseDto(notificationModuleService.findTopByNotificationTypeAndUserIdOrderByIdDesc(userId).orElseThrow());
    }

    public List<NotificationResponseDto> findAllByNotificationTypeAndUserId(Long userId){
        return notificationModuleService.findAllByNotificationTypeAndUserId(userId).stream()
            .map(notificationMapper::toNotificationResponseDto)
            .toList();
    }

    public void softDeleteByNotificationTypeAndUserId(Long userId){
        notificationModuleService.softDeleteByNotificationTypeAndUserId(userId);
    }

}
