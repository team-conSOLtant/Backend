package com.consoltant.consoltant.domain.portfolio.service;

import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PortfolioModuleService {

    private final PortfolioRepository portfolioRepository;

    public Portfolio findById(Long id) {
        return portfolioRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Portfolio Id"));
    }

    public Portfolio save(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    public void delete(Portfolio portfolio) {
        //연관관계 있는 것들 전부 삭제해줘야함.
        portfolio.delete();
    }
}
