package com.consoltant.consoltant.domain.project.dto;

import com.consoltant.consoltant.domain.projectuser.dto.ProjectUserResponseDto;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectResponseDto {

    private Long id;
    private String title;
    private String language;
    private String projectUrl;
    private String description;
    private List<String> contents;
    private List<ProjectUserResponseDto> projectUsers;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isDeleted;

}
