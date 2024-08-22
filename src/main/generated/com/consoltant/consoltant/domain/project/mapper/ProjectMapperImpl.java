package com.consoltant.consoltant.domain.project.mapper;

import com.consoltant.consoltant.domain.project.dto.ProjectRequestDto;
import com.consoltant.consoltant.domain.project.dto.ProjectResponseDto;
import com.consoltant.consoltant.domain.project.entity.Project;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-21T17:13:12+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Override
    public Project toProject(ProjectRequestDto projectRequestDto) {
        if ( projectRequestDto == null ) {
            return null;
        }

        Project.ProjectBuilder project = Project.builder();

        project.title( projectRequestDto.getTitle() );
        project.startDate( projectRequestDto.getStartDate() );
        project.endDate( projectRequestDto.getEndDate() );
        project.language( projectRequestDto.getLanguage() );
        project.description( projectRequestDto.getDescription() );
        project.projectUrl( projectRequestDto.getProjectUrl() );

        return project.build();
    }

    @Override
    public ProjectResponseDto toProjectResponseDto(Project project) {
        if ( project == null ) {
            return null;
        }

        ProjectResponseDto projectResponseDto = new ProjectResponseDto();

        projectResponseDto.setId( project.getId() );
        projectResponseDto.setTitle( project.getTitle() );
        projectResponseDto.setLanguage( project.getLanguage() );
        projectResponseDto.setProjectUrl( project.getProjectUrl() );
        projectResponseDto.setDescription( project.getDescription() );
        projectResponseDto.setStartDate( project.getStartDate() );
        projectResponseDto.setEndDate( project.getEndDate() );
        projectResponseDto.setIsDeleted( project.getIsDeleted() );

        return projectResponseDto;
    }
}
