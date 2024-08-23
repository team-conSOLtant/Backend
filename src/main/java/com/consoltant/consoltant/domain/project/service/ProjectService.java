package com.consoltant.consoltant.domain.project.service;

import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import com.consoltant.consoltant.domain.project.dto.ProjectRequestDto;
import com.consoltant.consoltant.domain.project.dto.ProjectResponseDto;
import com.consoltant.consoltant.domain.project.entity.Project;
import com.consoltant.consoltant.domain.project.mapper.ProjectMapper;
import com.consoltant.consoltant.domain.projectuser.dto.ProjectUserRequestDto;
import com.consoltant.consoltant.domain.projectuser.service.ProjectUserService;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectModuleService projectModuleService;
    private final PortfolioModuleService portfolioModuleService;
    private final ProjectUserService projectUserService;
    private final ProjectMapper projectMapper;

    // 단일 조회
    public ProjectResponseDto findById(Long id) {
        return projectMapper.toProjectResponseDto(projectModuleService.findById(id));
    }

    // 포트폴리오 ID로 프로젝트 리스트 조회
    public List<ProjectResponseDto> findAllByPortfolioId(Long portfolioId){
        return projectModuleService.findAllByPortfolioId(portfolioId).stream()
            .sorted(Comparator.comparing(Project::getStartDate))
            .map(projectMapper::toProjectResponseDto)
            .toList();
    }

    // 등록
    @Transactional
    public ProjectResponseDto save(ProjectRequestDto projectRequestDto) {
        Portfolio portfolio = portfolioModuleService.findById(projectRequestDto.getPortfolioId());
        Project project = projectMapper.toProject(projectRequestDto);
        project.setPortfolio(portfolio);
        Project savedProject = projectModuleService.save(project);
        Long generatedKey = savedProject.getId();
        for(ProjectUserRequestDto projectUserRequestDto : projectRequestDto.getProjectUsers()){
            projectUserRequestDto.setProjectId(generatedKey);
            projectUserService.save(projectUserRequestDto);
        }
        return projectMapper.toProjectResponseDto(savedProject);
    }

    // 수정
    @Transactional
    public ProjectResponseDto update(Long id, ProjectRequestDto projectRequestDto){
        Project project = projectModuleService.findById(id);
        project.update(projectRequestDto);
        projectModuleService.save(project);
        return projectMapper.toProjectResponseDto(project);
    }

    // 삭제
    public void delete(Long id){
        projectModuleService.delete(id);
    }
}