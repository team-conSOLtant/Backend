package com.consoltant.consoltant.domain.activity.repository;

import com.consoltant.consoltant.domain.activity.entity.Activity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByPortfolioId(Long portfolioId);
    void deleteAllByPortfolioId(Long portfolioId);
}
