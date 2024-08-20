package com.consoltant.consoltant.domain.certification.service;

import com.consoltant.consoltant.domain.certification.dto.CertificationRequestDto;
import com.consoltant.consoltant.domain.certification.dto.CertificationResponseDto;
import com.consoltant.consoltant.domain.certification.entity.Certification;
import com.consoltant.consoltant.domain.certification.mapper.CertificationMapper;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificationService {

    private final CertificationModuleService certificationModuleService;
    private final PortfolioModuleService portfolioModuleService;
    private final CertificationMapper certificationMapper;

    // 단일 조회
    public CertificationResponseDto findById(Long id) {
        return certificationMapper.toCertificationResponseDto(certificationModuleService.findById(id));
    }

    // 포트폴리오 ID로 인증 리스트 조회
    public List<CertificationResponseDto> findAllByPortfolioId(Long portfolioId){
        return certificationModuleService.findAllByPortfolioId(portfolioId).stream()
            .sorted(Comparator.comparing(Certification::getAcquisitionDate))
            .map(certificationMapper::toCertificationResponseDto)
            .toList();
    }

    // 등록
    public CertificationResponseDto save(CertificationRequestDto certificationRequestDto) {
        Portfolio portfolio = portfolioModuleService.findById(certificationRequestDto.getPortfolioId());
        Certification certification = certificationMapper.toCertification(certificationRequestDto);
        certification.setPortfolio(portfolio);
        return certificationMapper.toCertificationResponseDto(certificationModuleService.save(certification));
    }

    // 수정
    public CertificationResponseDto update(Long id, CertificationRequestDto certificationRequestDto){
        Certification certification = certificationModuleService.findById(id);
        certification.update(certificationRequestDto);
        certificationModuleService.save(certification);
        return certificationMapper.toCertificationResponseDto(certification);
    }

    // 삭제
    public void delete(Long id){
        certificationModuleService.delete(id);
    }
}