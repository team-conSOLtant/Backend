package com.consoltant.consoltant.domain.projectuser.service;

import com.consoltant.consoltant.domain.projectuser.entity.ProjectUser;
import com.consoltant.consoltant.domain.projectuser.repository.ProjectUserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectUserModuleService {
    private final ProjectUserRepository projectUserRepository;

    // 프로젝트 ID로 모든 ProjectUser 조회
    public List<ProjectUser> findAllByProjectId(Long projectId) {
        return projectUserRepository.findAllByProjectId(projectId);
    }

    // ProjectUser 저장
    public ProjectUser save(ProjectUser projectUser) {
        return projectUserRepository.save(projectUser);
    }

    // ProjectUser 삭제
    public void delete(Long id) {
        projectUserRepository.deleteById(id);
    }
}
