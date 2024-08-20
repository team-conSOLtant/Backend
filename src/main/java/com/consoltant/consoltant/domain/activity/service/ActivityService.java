package com.consoltant.consoltant.domain.activity.service;

import com.consoltant.consoltant.domain.activity.dto.ActivityRequestDto;
import com.consoltant.consoltant.domain.activity.dto.ActivityResponseDto;
import com.consoltant.consoltant.domain.activity.entity.Activity;
import com.consoltant.consoltant.domain.activity.mapper.ActivityMapper;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityModuleService activityModuleService;
    private final PortfolioModuleService portfolioModuleService;
    private final ActivityMapper activityMapper;

    // 단일 조회
    public ActivityResponseDto findById(Long id) {
        return activityMapper.toActivityResponseDto(activityModuleService.findById(id));
    }

    // 포트폴리오 ID로 활동 리스트 조회
    public List<ActivityResponseDto> findAllByPortfolioId(Long portfolioId){
        return activityModuleService.findAllByPortfolioId(portfolioId).stream()
            .sorted(Comparator.comparing(Activity::getStartDate))
            .map(activityMapper::toActivityResponseDto)
            .toList();
    }

    // 등록
    public ActivityResponseDto save(ActivityRequestDto activityRequestDto) {
        Portfolio portfolio = portfolioModuleService.findById(activityRequestDto.getPortfolioId());
        Activity activity = activityMapper.toActivity(activityRequestDto);
        activity.setPortfolio(portfolio);
        return activityMapper.toActivityResponseDto(activityModuleService.save(activity));
    }

    // 수정
    public ActivityResponseDto update(Long id, ActivityRequestDto activityRequestDto){
        Activity activity = activityModuleService.findById(id);
        activity.update(activityRequestDto);
        activityModuleService.save(activity);
        return activityMapper.toActivityResponseDto(activity);
    }

    // 삭제
    public void delete(Long id){
        activityModuleService.delete(id);
    }
}
