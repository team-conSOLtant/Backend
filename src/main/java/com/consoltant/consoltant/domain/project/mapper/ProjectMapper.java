package com.consoltant.consoltant.domain.project.mapper;

import com.consoltant.consoltant.domain.project.dto.ProjectRequestDto;
import com.consoltant.consoltant.domain.project.dto.ProjectResponseDto;
import com.consoltant.consoltant.domain.project.entity.Project;
import com.consoltant.consoltant.domain.projectuser.mapper.ProjectUserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ProjectUserMapper.class})
public interface ProjectMapper {

    @Mapping(target = "projectUsers", source = "projectUsers")
    Project toProject(ProjectRequestDto projectRequestDto);

    @Mapping(target = "contents", expression = "java(project.getContents() != null ? java.util.Arrays.asList(project.getContents().split(\"\\\\n\")) : new java.util.ArrayList<>())")
    ProjectResponseDto toProjectResponseDto(Project project);
}