package com.consoltant.consoltant.domain.activity.controller;

import com.consoltant.consoltant.domain.activity.dto.ActivityRequestDto;
import com.consoltant.consoltant.domain.activity.dto.ActivityResponseDto;
import com.consoltant.consoltant.domain.activity.service.ActivityService;
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
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;

    // 단일 조회
    @GetMapping("/{id}")
    public BaseSuccessResponse<ActivityResponseDto> findById(@PathVariable Long id) {
        return new BaseSuccessResponse<>(activityService.findById(id));
    }

    // 포트폴리오 아이디로 활동 리스트 조회
    @GetMapping
    public BaseSuccessResponse<List<ActivityResponseDto>> findAllByPortfolioId(@RequestParam Long portfolioId){
        return new BaseSuccessResponse<>(activityService.findAllByPortfolioId(portfolioId));
    }

    // 활동 등록
    @PostMapping
    public BaseSuccessResponse<ActivityResponseDto> save(@RequestBody ActivityRequestDto activityRequestDto) {
        return new BaseSuccessResponse<>(activityService.save(activityRequestDto));
    }

    // 활동 수정
    @PutMapping("/{id}")
    public BaseSuccessResponse<ActivityResponseDto> update(@PathVariable Long id, @RequestBody ActivityRequestDto activityRequestDto){
        return new BaseSuccessResponse<>(activityService.update(id, activityRequestDto));
    }

    // 활동 삭제
    @DeleteMapping("/{id}")
    public BaseSuccessResponse<Void> delete(@PathVariable Long id){
        activityService.delete(id);
        return new BaseSuccessResponse<>(null);
    }
}
