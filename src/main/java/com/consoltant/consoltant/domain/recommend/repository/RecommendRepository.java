package com.consoltant.consoltant.domain.recommend.repository;

import com.consoltant.consoltant.domain.recommend.entity.Recommend;
import com.consoltant.consoltant.util.constant.JourneyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendRepository extends JpaRepository<Recommend,Long> {
    List<Recommend> findAllByUserIdAndJourneyType(Long UserId, JourneyType journeyType);
    List<Recommend> findAllByUserId(Long UserId);

    @Query("SELECT r FROM Recommend r WHERE r.user.id = :userId AND " +
            "YEAR(r.startDate) <= :year AND " +
            "YEAR(r.endDate) >= :year AND r.isDeleted = false")
    List<Recommend> findAllByUserIdAndYear(@Param("userId") Long UserId, @Param("year") Integer year);
}
