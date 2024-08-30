package com.consoltant.consoltant.domain.journey.repository;

import com.consoltant.consoltant.domain.journey.entity.Journey;
import com.consoltant.consoltant.util.constant.JourneyType;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JourneyRepository extends JpaRepository<Journey, Long> {
    List<Journey> findAllByUserId(Long id);
    Journey findByUserIdAndJourneyType(Long id, JourneyType journeyType);
}
