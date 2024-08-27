package com.consoltant.consoltant.domain.project.mapper;

import com.consoltant.consoltant.domain.project.dto.ProjectRequestDto;
import com.consoltant.consoltant.domain.project.dto.ProjectResponseDto;
import com.consoltant.consoltant.domain.project.entity.Project;
import com.consoltant.consoltant.domain.projectuser.dto.ProjectUserRequestDto;
import com.consoltant.consoltant.domain.projectuser.dto.ProjectUserResponseDto;
import com.consoltant.consoltant.domain.projectuser.entity.ProjectUser;
import com.consoltant.consoltant.domain.projectuser.mapper.ProjectUserMapper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ProjectMapperImpl implements ProjectMapper {

    @Autowired
    private ProjectUserMapper projectUserMapper;

    @Override
    public Project toProject(ProjectRequestDto projectRequestDto) {
        if ( projectRequestDto == null ) {
            return null;
        }

        Project.ProjectBuilder project = Project.builder();

        project.projectUsers( projectUserRequestDtoListToProjectUserList( projectRequestDto.getProjectUsers() ) );
        project.title( projectRequestDto.getTitle() );
        project.startDate( projectRequestDto.getStartDate() );
        project.endDate( projectRequestDto.getEndDate() );
        project.language( projectRequestDto.getLanguage() );
        project.description( projectRequestDto.getDescription() );
        project.contents( projectRequestDto.getContents() );
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
        projectResponseDto.setProjectUsers( projectUserListToProjectUserResponseDtoList( project.getProjectUsers() ) );
        projectResponseDto.setStartDate( project.getStartDate() );
        projectResponseDto.setEndDate( project.getEndDate() );
        projectResponseDto.setIsDeleted( project.getIsDeleted() );

        projectResponseDto.setContents( project.getContents() != null ? java.util.Arrays.asList(project.getContents().split("\\n")) : new java.util.ArrayList<>() );

        return projectResponseDto;
    }

    protected List<ProjectUser> projectUserRequestDtoListToProjectUserList(List<ProjectUserRequestDto> list) {
        if ( list == null ) {
            return null;
        }

        List<ProjectUser> list1 = new ArrayList<ProjectUser>( list.size() );
        for ( ProjectUserRequestDto projectUserRequestDto : list ) {
            list1.add( projectUserMapper.toProjectUser( projectUserRequestDto ) );
        }

        return list1;
    }

    protected List<ProjectUserResponseDto> projectUserListToProjectUserResponseDtoList(List<ProjectUser> list) {
        if ( list == null ) {
            return null;
        }

        List<ProjectUserResponseDto> list1 = new ArrayList<ProjectUserResponseDto>( list.size() );
        for ( ProjectUser projectUser : list ) {
            list1.add( projectUserMapper.toProjectUserResponseDto( projectUser ) );
        }

        return list1;
    }
}
