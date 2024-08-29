package com.consoltant.consoltant.domain.activity.dto;

import com.consoltant.consoltant.util.constant.ActivityType;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ActivityRequestDto {

    private Long activityId;
    private Long portfolioId;
    private String title;
    private String contentTitle;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;
    private ActivityType activityType;

}
