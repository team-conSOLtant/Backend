package com.consoltant.consoltant.domain.award.service;

import com.consoltant.consoltant.domain.award.dto.AwardRequestDto;
import com.consoltant.consoltant.domain.award.dto.AwardResponseDto;
import com.consoltant.consoltant.domain.award.entity.Award;
import com.consoltant.consoltant.domain.award.mapper.AwardMapper;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AwardService {

    private final AwardModuleService awardModuleService;
    private final AwardMapper awardMapper;
    private final PortfolioModuleService portfolioModuleService;

    public AwardResponseDto findById(Long id) {
        return awardMapper.toAwardResponseDto(awardModuleService.findById(id));
    }

    public List<AwardResponseDto> findAllByPortfolioId(Long portfolioId) {
        return awardModuleService.findAllByPortfolioId(portfolioId)
            .stream()
            .sorted(Comparator.comparing(Award::getAcquisitionDate))
            .map(awardMapper::toAwardResponseDto)
            .toList();
    }

    public AwardResponseDto save(AwardRequestDto awardRequestDto) {
        Portfolio portfolio = portfolioModuleService.findById(awardRequestDto.getPortfolioId());
        Award award = awardMapper.toAward(awardRequestDto);
        award.setPortfolio(portfolio);
        return awardMapper.toAwardResponseDto(awardModuleService.save(award));
    }

    public AwardResponseDto update(Long id, AwardRequestDto awardRequestDto){
        Award award = awardModuleService.findById(id);
        award.update(awardRequestDto);
        awardModuleService.save(award);
        return awardMapper.toAwardResponseDto(award);
    }

    //삭제
    public void delete(Long id){
        awardModuleService.delete(id);
    }

}
