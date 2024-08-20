package com.consoltant.consoltant.domain.portfolio.repository;

import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

}
