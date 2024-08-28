package com.consoltant.consoltant.domain.notification.controller;

import com.consoltant.consoltant.domain.notification.dto.NotificationResponseDto;
import com.consoltant.consoltant.domain.notification.service.NotificationService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    // 단일 조회
    @GetMapping("/{id}")
    public BaseSuccessResponse<NotificationResponseDto> findById(@PathVariable Long id) {
        return new BaseSuccessResponse<>(notificationService.findById(id));
    }

    // 유저 ID로 알림 리스트 조회
    @GetMapping
    public BaseSuccessResponse<List<NotificationResponseDto>> findAllByUserId(@RequestParam Long userId){
        return new BaseSuccessResponse<>(notificationService.findAllByUserId(userId));
    }

    // 유저 ID로 읽지 않은 알림 리스트 조회
    @GetMapping("/not-read")
    public BaseSuccessResponse<List<NotificationResponseDto>> findAllByUserIdAndIsReadFalse(@RequestParam Long userId){
        return new BaseSuccessResponse<>(notificationService.findAllByUserIdAndIsReadFalse(userId));
    }

    // 알림 읽기
    @PatchMapping("/read/{id}")
    public BaseSuccessResponse<NotificationResponseDto> read(@PathVariable Long id){
        return new BaseSuccessResponse<>(notificationService.read(id));
    }

    // 알림 삭제
    @DeleteMapping("/{id}")
    public BaseSuccessResponse<Void> delete(@PathVariable Long id){
        notificationService.delete(id);
        return new BaseSuccessResponse<>(null);
    }

    @GetMapping("/portfolio-matching")
    public BaseSuccessResponse<NotificationResponseDto> findByPortfolioMatching(@RequestParam Long userId){
        return new BaseSuccessResponse<>(notificationService.findTopByNotificationTypeAndUserIdOrderByIdDesc(userId));
    }

    @GetMapping("/portfolio-comment")
    public BaseSuccessResponse<List<NotificationResponseDto>> findByPortfolioComment(@RequestParam Long userId){
        return new BaseSuccessResponse<>(notificationService.findAllByNotificationTypeAndUserId(userId));
    }

    @PatchMapping("/readall/portfolio-comment")
    public BaseSuccessResponse<Void> softDeleteByNotificationTypeAndUserId(@RequestParam Long userId){
        notificationService.softDeleteByNotificationTypeAndUserId(userId);
        return new BaseSuccessResponse<>(null);
    }
}
