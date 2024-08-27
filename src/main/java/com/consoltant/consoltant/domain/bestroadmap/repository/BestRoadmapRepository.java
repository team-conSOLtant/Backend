package com.consoltant.consoltant.domain.bestroadmap.repository;

import com.consoltant.consoltant.domain.bestroadmap.entity.BestRoadmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BestRoadmapRepository extends JpaRepository<BestRoadmap,Long> {
}
