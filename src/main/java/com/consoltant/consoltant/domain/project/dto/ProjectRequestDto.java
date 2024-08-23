package com.consoltant.consoltant.domain.project.dto;


import com.consoltant.consoltant.domain.projectuser.dto.ProjectUserRequestDto;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequestDto {

    private Long portfolioId;
    private String title;
    private String language;
    private String projectUrl;
    private String description;
    private String contents;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ProjectUserRequestDto> projectUsers;


}
