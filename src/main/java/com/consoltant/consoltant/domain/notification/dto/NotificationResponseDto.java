package com.consoltant.consoltant.domain.notification.dto;

import com.consoltant.consoltant.util.constant.NotificationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationResponseDto {

    private Long id;
    private Long userId;
    private NotificationType notificationType;
    private String content;
    private Boolean isRead;
    private Boolean isDeleted;
}
