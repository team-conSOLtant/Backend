package com.consoltant.consoltant.domain.portfolio.repository;

import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Optional<Portfolio> findByUserId(Long userId);

    @Query("SELECT p FROM Portfolio p " +
        "JOIN p.user u " +
        "WHERE u.university.id = :universityId " +
        "AND u.isEmployed = true")
    List<Portfolio> findAllByUniversityIdAndIsEmployedTrue(@Param("universityId") Long universityId);
}
