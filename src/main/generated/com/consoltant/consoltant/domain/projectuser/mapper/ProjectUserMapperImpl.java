package com.consoltant.consoltant.domain.projectuser.mapper;

import com.consoltant.consoltant.domain.project.entity.Project;
import com.consoltant.consoltant.domain.projectuser.dto.ProjectUserRequestDto;
import com.consoltant.consoltant.domain.projectuser.dto.ProjectUserResponseDto;
import com.consoltant.consoltant.domain.projectuser.entity.ProjectUser;
import com.consoltant.consoltant.domain.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ProjectUserMapperImpl implements ProjectUserMapper {

    @Override
    public ProjectUser toProjectUser(ProjectUserRequestDto projectUserRequestDto) {
        if ( projectUserRequestDto == null ) {
            return null;
        }

        ProjectUser.ProjectUserBuilder projectUser = ProjectUser.builder();

        projectUser.name( projectUserRequestDto.getName() );

        return projectUser.build();
    }

    @Override
    public ProjectUserResponseDto toProjectUserResponseDto(ProjectUser projectUser) {
        if ( projectUser == null ) {
            return null;
        }

        ProjectUserResponseDto projectUserResponseDto = new ProjectUserResponseDto();

        projectUserResponseDto.setProjectId( projectUserProjectId( projectUser ) );
        projectUserResponseDto.setUserId( projectUserUserId( projectUser ) );
        projectUserResponseDto.setId( projectUser.getId() );
        projectUserResponseDto.setName( projectUser.getName() );

        return projectUserResponseDto;
    }

    private Long projectUserProjectId(ProjectUser projectUser) {
        if ( projectUser == null ) {
            return null;
        }
        Project project = projectUser.getProject();
        if ( project == null ) {
            return null;
        }
        Long id = project.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long projectUserUserId(ProjectUser projectUser) {
        if ( projectUser == null ) {
            return null;
        }
        User user = projectUser.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
