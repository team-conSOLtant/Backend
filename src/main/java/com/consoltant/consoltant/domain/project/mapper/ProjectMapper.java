package com.consoltant.consoltant.domain.project.mapper;

import com.consoltant.consoltant.domain.project.dto.ProjectRequestDto;
import com.consoltant.consoltant.domain.project.dto.ProjectResponseDto;
import com.consoltant.consoltant.domain.project.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {

    Project toProject(ProjectRequestDto projectRequestDto);

    ProjectResponseDto toProjectResponseDto(Project project);

}