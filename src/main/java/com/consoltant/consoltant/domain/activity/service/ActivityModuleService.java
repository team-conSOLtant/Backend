package com.consoltant.consoltant.domain.activity.service;

import com.consoltant.consoltant.domain.activity.entity.Activity;
import com.consoltant.consoltant.domain.activity.repository.ActivityRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityModuleService {
    private final ActivityRepository activityRepository;

    public Activity findById(Long id) {
        return activityRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Activity ID"));
    }

    public List<Activity> findAllByPortfolioId(Long portfolioId) {
        return activityRepository.findAllByPortfolioId(portfolioId);
    }

    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }

    public void delete(Long id) {
        activityRepository.deleteById(id);
    }

    public void deleteAllByPortfolioId(Long portfolioId){
        activityRepository.deleteAllByPortfolioId(portfolioId);
    }
}
