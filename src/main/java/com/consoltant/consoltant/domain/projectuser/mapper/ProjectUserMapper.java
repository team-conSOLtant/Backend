package com.consoltant.consoltant.domain.projectuser.mapper;

import com.consoltant.consoltant.domain.projectuser.dto.ProjectUserRequestDto;
import com.consoltant.consoltant.domain.projectuser.dto.ProjectUserResponseDto;
import com.consoltant.consoltant.domain.projectuser.entity.ProjectUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectUserMapper {

    ProjectUser toProjectUser(ProjectUserRequestDto projectUserRequestDto);

    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "user.id", target = "userId")
    ProjectUserResponseDto toProjectUserResponseDto(ProjectUser projectUser);

}
