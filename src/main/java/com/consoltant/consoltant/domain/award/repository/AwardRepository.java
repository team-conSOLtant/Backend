package com.consoltant.consoltant.domain.award.repository;

import com.consoltant.consoltant.domain.award.entity.Award;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AwardRepository extends JpaRepository<Award, Long> {
    List<Award> findAllByPortfolioId(Long id);
    void deleteAllByPortfolioId(Long portfolioId);
}
