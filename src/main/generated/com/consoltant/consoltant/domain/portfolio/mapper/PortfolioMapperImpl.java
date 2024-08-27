package com.consoltant.consoltant.domain.portfolio.mapper;

import com.consoltant.consoltant.domain.portfolio.dto.PortfolioRequestDto;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioResponseDto;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioSaveAllRequestDto;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class PortfolioMapperImpl implements PortfolioMapper {

    @Override
    public Portfolio toPortfolio(PortfolioRequestDto portfolioRequestDto) {
        if ( portfolioRequestDto == null ) {
            return null;
        }

        Portfolio.PortfolioBuilder portfolio = Portfolio.builder();

        portfolio.totalGpa( portfolioRequestDto.getTotalGpa() );
        portfolio.majorGpa( portfolioRequestDto.getMajorGpa() );
        portfolio.financeKeyword( portfolioRequestDto.getFinanceKeyword() );
        portfolio.myKeyword( portfolioRequestDto.getMyKeyword() );
        portfolio.job( portfolioRequestDto.getJob() );
        portfolio.email( portfolioRequestDto.getEmail() );
        portfolio.imageUrl( portfolioRequestDto.getImageUrl() );
        portfolio.description( portfolioRequestDto.getDescription() );
        portfolio.backgroundColor( portfolioRequestDto.getBackgroundColor() );

        return portfolio.build();
    }

    @Override
    public Portfolio toPortfolio(PortfolioSaveAllRequestDto portfolioSaveAllRequestDto) {
        if ( portfolioSaveAllRequestDto == null ) {
            return null;
        }

        Portfolio.PortfolioBuilder portfolio = Portfolio.builder();

        return portfolio.build();
    }

    @Override
    public PortfolioResponseDto toPortfolioResponseDto(Portfolio portfolio) {
        if ( portfolio == null ) {
            return null;
        }

        PortfolioResponseDto portfolioResponseDto = new PortfolioResponseDto();

        portfolioResponseDto.setId( portfolio.getId() );
        portfolioResponseDto.setTotalGpa( portfolio.getTotalGpa() );
        portfolioResponseDto.setMajorGpa( portfolio.getMajorGpa() );
        portfolioResponseDto.setFinanceKeyword( portfolio.getFinanceKeyword() );
        portfolioResponseDto.setMyKeyword( portfolio.getMyKeyword() );
        portfolioResponseDto.setEmail( portfolio.getEmail() );
        portfolioResponseDto.setJob( portfolio.getJob() );
        portfolioResponseDto.setImageUrl( portfolio.getImageUrl() );
        portfolioResponseDto.setDescription( portfolio.getDescription() );
        portfolioResponseDto.setBackgroundColor( portfolio.getBackgroundColor() );
        portfolioResponseDto.setIsDeleted( portfolio.getIsDeleted() );

        return portfolioResponseDto;
    }
}
