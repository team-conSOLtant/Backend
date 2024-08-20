package com.consoltant.consoltant.domain.activity.mapper;

import com.consoltant.consoltant.domain.activity.dto.ActivityRequestDto;
import com.consoltant.consoltant.domain.activity.dto.ActivityResponseDto;
import com.consoltant.consoltant.domain.activity.entity.Activity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ActivityMapper {

    Activity toActivity(ActivityRequestDto activityRequestDto);

    ActivityResponseDto toActivityResponseDto(Activity activity);

}
