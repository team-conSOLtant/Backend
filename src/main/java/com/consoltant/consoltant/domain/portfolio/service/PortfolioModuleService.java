package com.consoltant.consoltant.domain.portfolio.service;

import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.repository.PortfolioRepository;
import java.util.List;
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

    public Portfolio findByUserId(Long userId) {
        return portfolioRepository.findByUserId(userId);
    }

    public Portfolio save(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    public void delete(Long id) {
        portfolioRepository.deleteById(id);
    }

    //같은 대학의 취업한 선배들의 포트폴리오 조회
    public List<Portfolio> findAllByUniversityIdAndIsEmployedTrue(Long universityId){
        return portfolioRepository.findAllByUniversityIdAndIsEmployedTrue(universityId);
    }
}