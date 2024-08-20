package com.consoltant.consoltant.domain.project.service;

import com.consoltant.consoltant.domain.project.entity.Project;
import com.consoltant.consoltant.domain.project.repository.ProjectRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectModuleService {
    private final ProjectRepository projectRepository;

    // Project ID로 단일 조회
    public Project findById(Long id) {
        return projectRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Project ID"));
    }

    // 포트폴리오 ID로 모든 Project 조회
    public List<Project> findAllByPortfolioId(Long portfolioId) {
        return projectRepository.findAllByPortfolioId(portfolioId);
    }

    // Project 저장
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    // Project 삭제
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }
}