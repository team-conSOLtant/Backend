package com.consoltant.consoltant.domain.career.service;

import com.consoltant.consoltant.domain.career.dto.CareerRequestDto;
import com.consoltant.consoltant.domain.career.dto.CareerResponseDto;
import com.consoltant.consoltant.domain.career.entity.Career;
import com.consoltant.consoltant.domain.career.mapper.CareerMapper;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareerService {

    private final CareerModuleService careerModuleService;
    private final PortfolioModuleService portfolioModuleService;
    private final CareerMapper careerMapper;

    //단일 조회
    public CareerResponseDto findById(Long id) {
        return careerMapper.toCareerResponseDto(careerModuleService.findById(id));
    }

    //포폴 아이디로 경력 리스트 조회
    public List<CareerResponseDto> findAllByPortfolioId(Long portfolioId){
        return careerModuleService.findAllByPortfolioId(portfolioId).stream()
            .sorted(Comparator.comparing(Career::getStartDate))
            .map(career -> careerMapper.toCareerResponseDto(career))
            .toList();
    }

    //등록
    public CareerResponseDto save(CareerRequestDto careerRequestDto) {
        Portfolio portfolio = portfolioModuleService.findById(careerRequestDto.getPortfolioId());
        Career career = careerMapper.toCareer(careerRequestDto);
        career.setPortfolio(portfolio);
        careerModuleService.save(career);
        return careerMapper.toCareerResponseDto(career);
    }

    //수정
    public CareerResponseDto update(Long id, CareerRequestDto careerRequestDto){
        Career career = careerModuleService.findById(id);
        career.update(careerRequestDto);
        return careerMapper.toCareerResponseDto(career);
    }

    //삭제
    public void delete(Long id){
        careerModuleService.delete(id);
    }
}
