package com.consoltant.consoltant.domain.portfolio.service;

import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioModuleService {
    private final PortfolioRepository portfolioRepository;

    public Portfolio findById(Long id) {
        return portfolioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Portfolio ID"));
    }

    public Portfolio save(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    public void delete(Long id) {
        portfolioRepository.deleteById(id);
    }
}