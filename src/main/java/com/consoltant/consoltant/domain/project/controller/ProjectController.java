package com.consoltant.consoltant.domain.project.controller;

import com.consoltant.consoltant.domain.project.dto.ProjectRequestDto;
import com.consoltant.consoltant.domain.project.dto.ProjectResponseDto;
import com.consoltant.consoltant.domain.project.service.ProjectService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/{id}")
    public BaseSuccessResponse<ProjectResponseDto> findById(@PathVariable Long id) {
        return new BaseSuccessResponse<>(projectService.findById(id));
    }

    @GetMapping
    public BaseSuccessResponse<List<ProjectResponseDto>> findAllByPortfolioId(@RequestParam Long portfolioId){
        return new BaseSuccessResponse<>(projectService.findAllByPortfolioId(portfolioId));
    }

    @PostMapping
    public BaseSuccessResponse<ProjectResponseDto> save(@RequestBody ProjectRequestDto projectRequestDto) {
        return new BaseSuccessResponse<>(projectService.save(projectRequestDto));
    }

    @PutMapping("/{id}")
    public BaseSuccessResponse<ProjectResponseDto> update(@PathVariable Long id, @RequestBody ProjectRequestDto projectRequestDto){
        return new BaseSuccessResponse<>(projectService.update(id, projectRequestDto));
    }

    @DeleteMapping("/{id}")
    public BaseSuccessResponse<Void> delete(@PathVariable Long id){
        projectService.delete(id);
        return new BaseSuccessResponse<>(null);
    }
}