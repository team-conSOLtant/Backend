package com.consoltant.consoltant.domain.career.repository;

import com.consoltant.consoltant.domain.career.entity.Career;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Career, Long> {
    List<Career> findByPortfolioId(Long portfolioId);
}
