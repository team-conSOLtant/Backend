package com.consoltant.consoltant.domain.notification.mapper;

import com.consoltant.consoltant.domain.notification.dto.NotificationRequestDto;
import com.consoltant.consoltant.domain.notification.dto.NotificationResponseDto;
import com.consoltant.consoltant.domain.notification.entity.Notification;
import com.consoltant.consoltant.domain.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public Notification toNotification(NotificationRequestDto notificationRequestDto) {
        if ( notificationRequestDto == null ) {
            return null;
        }

        Notification.NotificationBuilder notification = Notification.builder();

        notification.content( notificationRequestDto.getContent() );
        notification.notificationType( notificationRequestDto.getNotificationType() );

        return notification.build();
    }

    @Override
    public NotificationResponseDto toNotificationResponseDto(Notification notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationResponseDto notificationResponseDto = new NotificationResponseDto();

        notificationResponseDto.setUserId( notificationUserId( notification ) );
        notificationResponseDto.setId( notification.getId() );
        notificationResponseDto.setNotificationType( notification.getNotificationType() );
        notificationResponseDto.setContent( notification.getContent() );
        notificationResponseDto.setIsRead( notification.getIsRead() );
        notificationResponseDto.setIsDeleted( notification.getIsDeleted() );

        return notificationResponseDto;
    }

    private Long notificationUserId(Notification notification) {
        if ( notification == null ) {
            return null;
        }
        User user = notification.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
