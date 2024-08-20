package com.consoltant.consoltant.domain.career.service;

import com.consoltant.consoltant.domain.career.entity.Career;
import com.consoltant.consoltant.domain.career.repository.CareerRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareerModuleService {
    private final CareerRepository careerRepository;

    //portfolioId로 단일 조회
    public Career findById(Long id) {
        return careerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Career ID"));
    }

    //portfolioId로 career 전부 조회
    public List<Career> findAllByPortfolioId(Long portfolioId) {
        return careerRepository.findAllByPortfolioId(portfolioId);
    }

    //Portfolio 저장
    public Career save(Career career) {
        return careerRepository.save(career);
    }

    //portfolio 삭제
    public void delete(Long id) {
        careerRepository.deleteById(id);
    }

}
