package com.consoltant.consoltant.domain.career.service;

import com.consoltant.consoltant.domain.career.dto.CareerRequestDto;
import com.consoltant.consoltant.domain.career.dto.CareerResponseDto;
import com.consoltant.consoltant.domain.career.entity.Career;
import com.consoltant.consoltant.domain.career.mapper.CareerMapper;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareerService {

    private final CareerModuleService careerModuleService;
    private final PortfolioModuleService portfolioModuleService;

    public CareerResponseDto findById(Long id) {
        return CareerMapper.INSTANCE.toCareerResponseDto(careerModuleService.findById(id));
    }

    public void save(CareerRequestDto careerRequestDto) {
        Portfolio portfolio = portfolioModuleService.findById(careerRequestDto.getPortfolioId());
        Career career = CareerMapper.INSTANCE.toCareer(careerRequestDto);
        career.setPortfolio(portfolio);
        System.out.println(career.toString());
        careerModuleService.save(career);
    }
}
