package com.consoltant.consoltant.domain.follow.repository;

import com.consoltant.consoltant.domain.follow.entity.Follow;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByPortfolioId(Long id);
    List<Follow> findAllByUserId(Long id);
}
