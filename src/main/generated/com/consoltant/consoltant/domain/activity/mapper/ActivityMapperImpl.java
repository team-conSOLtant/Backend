package com.consoltant.consoltant.domain.activity.mapper;

import com.consoltant.consoltant.domain.activity.dto.ActivityRequestDto;
import com.consoltant.consoltant.domain.activity.dto.ActivityResponseDto;
import com.consoltant.consoltant.domain.activity.entity.Activity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ActivityMapperImpl implements ActivityMapper {

    @Override
    public Activity toActivity(ActivityRequestDto activityRequestDto) {
        if ( activityRequestDto == null ) {
            return null;
        }

        Activity.ActivityBuilder activity = Activity.builder();

        activity.activityType( activityRequestDto.getActivityType() );
        activity.title( activityRequestDto.getTitle() );
        activity.content( activityRequestDto.getContent() );
        activity.startDate( activityRequestDto.getStartDate() );
        activity.endDate( activityRequestDto.getEndDate() );

        return activity.build();
    }

    @Override
    public ActivityResponseDto toActivityResponseDto(Activity activity) {
        if ( activity == null ) {
            return null;
        }

        ActivityResponseDto activityResponseDto = new ActivityResponseDto();

        activityResponseDto.setId( activity.getId() );
        activityResponseDto.setTitle( activity.getTitle() );
        activityResponseDto.setContent( activity.getContent() );
        activityResponseDto.setStartDate( activity.getStartDate() );
        activityResponseDto.setEndDate( activity.getEndDate() );
        activityResponseDto.setActivityType( activity.getActivityType() );

        return activityResponseDto;
    }
}
