package com.consoltant.consoltant.domain.projectuser.controller;

import com.consoltant.consoltant.domain.projectuser.dto.ProjectUserRequestDto;
import com.consoltant.consoltant.domain.projectuser.dto.ProjectUserResponseDto;
import com.consoltant.consoltant.domain.projectuser.service.ProjectUserService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/project-users")
public class ProjectUserController {

    private final ProjectUserService projectUserService;

    // 프로젝트 ID로 유저 리스트 조회
    @GetMapping
    public BaseSuccessResponse<List<ProjectUserResponseDto>> findAllByProjectId(@RequestParam Long projectId){
        return new BaseSuccessResponse<>(projectUserService.findAllByProjectId(projectId));
    }

    // 유저 등록
    @PostMapping
    public BaseSuccessResponse<ProjectUserResponseDto> save(@RequestBody ProjectUserRequestDto projectUserRequestDto) {
        return new BaseSuccessResponse<>(projectUserService.save(projectUserRequestDto));
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public BaseSuccessResponse<Void> delete(@PathVariable Long id){
        projectUserService.delete(id);
        return new BaseSuccessResponse<>(null);
    }
}
