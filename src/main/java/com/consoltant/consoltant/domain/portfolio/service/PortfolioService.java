package com.consoltant.consoltant.domain.portfolio.service;

import com.consoltant.consoltant.domain.portfolio.dto.PortfolioRequestDto;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioResponseDto;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.mapper.PortfolioMapper;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioModuleService portfolioModuleService;
    private final PortfolioMapper portfolioMapper;
    private final UserRepository userRepository;

    public PortfolioResponseDto findById(Long id) {
        return portfolioMapper.toPortfolioResponseDto(portfolioModuleService.findById(id));
    }

    public PortfolioResponseDto save(PortfolioRequestDto portfolioRequestDto) {
        Portfolio portfolio
                = portfolioMapper.toPortfolio(portfolioRequestDto);
        User user = userRepository.findById(portfolioRequestDto.getUserId()).orElseThrow();
        portfolio.setUser(user);
        return portfolioMapper.toPortfolioResponseDto(portfolioModuleService.save(portfolio));
    }

    public PortfolioResponseDto update(Long id, PortfolioRequestDto portfolioRequestDto){
        Portfolio portfolio = portfolioModuleService.findById(id);
        portfolio.update(portfolioRequestDto);
        portfolioModuleService.save(portfolio);
        return portfolioMapper.toPortfolioResponseDto(portfolio);
    }

    public void delete(Long id){
        portfolioModuleService.delete(id);
    }
}