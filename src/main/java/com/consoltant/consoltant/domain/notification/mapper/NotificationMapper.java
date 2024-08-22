package com.consoltant.consoltant.domain.notification.mapper;

import com.consoltant.consoltant.domain.notification.dto.NotificationRequestDto;
import com.consoltant.consoltant.domain.notification.dto.NotificationResponseDto;
import com.consoltant.consoltant.domain.notification.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationMapper {

    Notification toNotification(NotificationRequestDto notificationRequestDto);

    @Mapping(source = "user.id", target = "userId")
    NotificationResponseDto toNotificationResponseDto(Notification notification);

}
