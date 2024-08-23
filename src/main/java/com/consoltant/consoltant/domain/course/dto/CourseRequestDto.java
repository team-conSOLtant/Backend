package com.consoltant.consoltant.domain.course.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequestDto {
    private Long userId;
    private Long subjectId;
    private Boolean isSelected;

}
