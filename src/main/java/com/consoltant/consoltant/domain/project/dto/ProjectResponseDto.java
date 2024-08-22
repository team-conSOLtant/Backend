package com.consoltant.consoltant.domain.project.dto;

import java.time.LocalDate;
import java.util.List;

import com.consoltant.consoltant.domain.projectuser.entity.ProjectUser;
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
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isDeleted;

}
