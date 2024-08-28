package com.consoltant.consoltant.domain.project.repository;

import com.consoltant.consoltant.domain.project.entity.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByPortfolioId(Long id);
    void deleteAllByPortfolioId(Long portfolioId);
}
