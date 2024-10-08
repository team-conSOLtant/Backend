package com.consoltant.consoltant.domain.activity.dto;

import com.consoltant.consoltant.util.constant.ActivityType;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityResponseDto {

    private Long id;
    private String title;
    private String content;
    private String contentTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private ActivityType activityType;
    private boolean isDeleted;

}
