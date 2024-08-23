package com.consoltant.consoltant.domain.matching.repository;

import com.consoltant.consoltant.domain.matching.entity.Matching;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchingRepository extends JpaRepository<Matching, Long> {

    @Query("SELECT DISTINCT m.portfolio.id FROM Matching m WHERE m.user.id = :userId")
    Set<Long> findPortfolioIdsByUserId(@Param("userId") Long userId);

}
