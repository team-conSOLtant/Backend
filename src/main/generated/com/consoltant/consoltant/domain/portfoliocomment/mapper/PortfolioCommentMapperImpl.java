package com.consoltant.consoltant.domain.portfoliocomment.mapper;

import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfoliocomment.dto.PortfolioCommentRequestDto;
import com.consoltant.consoltant.domain.portfoliocomment.dto.PortfolioCommentResponseDto;
import com.consoltant.consoltant.domain.portfoliocomment.entity.PortfolioComment;
import com.consoltant.consoltant.domain.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class PortfolioCommentMapperImpl implements PortfolioCommentMapper {

    @Override
    public PortfolioComment toPortfolioComment(PortfolioCommentRequestDto portfolioCommentRequestDto) {
        if ( portfolioCommentRequestDto == null ) {
            return null;
        }

        PortfolioComment.PortfolioCommentBuilder portfolioComment = PortfolioComment.builder();

        portfolioComment.comment( portfolioCommentRequestDto.getComment() );

        return portfolioComment.build();
    }

    @Override
    public PortfolioCommentResponseDto toPortfolioCommentResponseDto(PortfolioComment portfolioComment) {
        if ( portfolioComment == null ) {
            return null;
        }

        PortfolioCommentResponseDto portfolioCommentResponseDto = new PortfolioCommentResponseDto();

        portfolioCommentResponseDto.setPortfolioId( portfolioCommentPortfolioId( portfolioComment ) );
        portfolioCommentResponseDto.setUserId( portfolioCommentUserId( portfolioComment ) );
        portfolioCommentResponseDto.setId( portfolioComment.getId() );
        portfolioCommentResponseDto.setComment( portfolioComment.getComment() );
        portfolioCommentResponseDto.setIsDeleted( portfolioComment.getIsDeleted() );

        return portfolioCommentResponseDto;
    }

    private Long portfolioCommentPortfolioId(PortfolioComment portfolioComment) {
        if ( portfolioComment == null ) {
            return null;
        }
        Portfolio portfolio = portfolioComment.getPortfolio();
        if ( portfolio == null ) {
            return null;
        }
        Long id = portfolio.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long portfolioCommentUserId(PortfolioComment portfolioComment) {
        if ( portfolioComment == null ) {
            return null;
        }
        User user = portfolioComment.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
