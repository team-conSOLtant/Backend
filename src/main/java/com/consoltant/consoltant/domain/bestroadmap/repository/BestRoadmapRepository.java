package com.consoltant.consoltant.domain.bestroadmap.repository;

import com.consoltant.consoltant.domain.bestroadmap.entity.BestRoadmap;
import com.consoltant.consoltant.util.constant.FinanceKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BestRoadmapRepository extends JpaRepository<BestRoadmap,Long> {
    Optional<BestRoadmap> findByUserId(Long userId);
    Optional<List<BestRoadmap>> findAllByFinanceKeyword(FinanceKeyword financeKeyword);
}
