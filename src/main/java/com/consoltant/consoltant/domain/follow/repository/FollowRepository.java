package com.consoltant.consoltant.domain.follow.repository;

import com.consoltant.consoltant.domain.follow.entity.Follow;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByPortfolioId(Long id);
    List<Follow> findAllByUserId(Long id);

    Boolean existsByUserAndPortfolio(User user, Portfolio portfolio);
}
