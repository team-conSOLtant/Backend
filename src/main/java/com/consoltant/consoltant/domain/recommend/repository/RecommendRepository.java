package com.consoltant.consoltant.domain.recommend.repository;

import com.consoltant.consoltant.domain.recommend.entity.Recommend;
import com.consoltant.consoltant.util.constant.JourneyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend,Long> {
    List<Recommend> findAllByUserIdAndJourneyType(Long UserId, JourneyType journeyType);
    List<Recommend> findAllByUserId(Long UserId);
}
