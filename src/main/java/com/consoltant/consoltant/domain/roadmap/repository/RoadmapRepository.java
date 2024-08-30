package com.consoltant.consoltant.domain.roadmap.repository;

import com.consoltant.consoltant.domain.roadmap.entity.Roadmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoadmapRepository extends JpaRepository<Roadmap, Long> {
    Optional<List<Roadmap>> findAllByUserId(Long userId);
    Optional<Roadmap> findByUserId(Long roadmapId);
}
