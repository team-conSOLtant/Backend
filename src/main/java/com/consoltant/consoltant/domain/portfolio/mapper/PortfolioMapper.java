package com.consoltant.consoltant.domain.portfolio.mapper;

import com.consoltant.consoltant.domain.portfolio.dto.PortfolioRequestDto;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioResponseDto;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioSaveAllRequestDto;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PortfolioMapper {

    Portfolio toPortfolio(PortfolioRequestDto portfolioRequestDto);

    Portfolio toPortfolio(PortfolioSaveAllRequestDto portfolioSaveAllRequestDto);

    PortfolioResponseDto toPortfolioResponseDto(Portfolio portfolio);

}