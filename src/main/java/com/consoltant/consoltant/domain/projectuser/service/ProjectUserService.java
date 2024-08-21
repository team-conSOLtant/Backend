package com.consoltant.consoltant.domain.projectuser.service;

import com.consoltant.consoltant.domain.project.entity.Project;
import com.consoltant.consoltant.domain.project.service.ProjectModuleService;
import com.consoltant.consoltant.domain.projectuser.dto.ProjectUserRequestDto;
import com.consoltant.consoltant.domain.projectuser.dto.ProjectUserResponseDto;
import com.consoltant.consoltant.domain.projectuser.entity.ProjectUser;
import com.consoltant.consoltant.domain.projectuser.mapper.ProjectUserMapper;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectUserService {

    private final ProjectUserModuleService projectUserModuleService;
    private final ProjectModuleService projectModuleService;
    private final UserRepository userRepository;
    private final ProjectUserMapper projectUserMapper;

    // 프로젝트 ID로 유저 리스트 조회
    public List<ProjectUserResponseDto> findAllByProjectId(Long projectId){
        return projectUserModuleService.findAllByProjectId(projectId).stream()
            .map(projectUserMapper::toProjectUserResponseDto)
            .toList();
    }

    // 등록
    public ProjectUserResponseDto save(ProjectUserRequestDto projectUserRequestDto) {
        ProjectUser projectUser = projectUserMapper.toProjectUser(projectUserRequestDto);
        User user = userRepository.findById(projectUserRequestDto.getUserId()).orElseThrow();
        Project project = projectModuleService.findById(projectUserRequestDto.getProjectId());
        projectUser.setProject(project);
        projectUser.setUser(user);
        return projectUserMapper.toProjectUserResponseDto(projectUserModuleService.save(projectUser));
    }

    // 삭제
    public void delete(Long id){
        projectUserModuleService.delete(id);
    }
}
