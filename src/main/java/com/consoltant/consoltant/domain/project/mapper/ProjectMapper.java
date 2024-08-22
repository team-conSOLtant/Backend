package com.consoltant.consoltant.domain.project.mapper;

import com.consoltant.consoltant.domain.project.dto.ProjectRequestDto;
import com.consoltant.consoltant.domain.project.dto.ProjectResponseDto;
import com.consoltant.consoltant.domain.project.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {

    @Mapping(target = "contents", expression = "java(projectRequestDto.getContents().stream().collect(java.util.stream.Collectors.joining(\"\\n\")))")
    Project toProject(ProjectRequestDto projectRequestDto);

    @Mapping(target = "contents", expression = "java(java.util.Arrays.asList(project.getContents().split(\"\\n\")))")
    ProjectResponseDto toProjectResponseDto(Project project);

}