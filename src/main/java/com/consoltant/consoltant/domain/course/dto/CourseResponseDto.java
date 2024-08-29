package com.consoltant.consoltant.domain.course.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponseDto {

    private Long id;
    private Long userId;
    private Long subjectId;
    private String subjectName;
    private String grade;
    private Boolean isSelected;
    private Boolean isDeleted;

}
