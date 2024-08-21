package com.consoltant.consoltant.domain.portfoliocomment.mapper;

import com.consoltant.consoltant.domain.portfoliocomment.dto.PortfolioCommentRequestDto;
import com.consoltant.consoltant.domain.portfoliocomment.dto.PortfolioCommentResponseDto;
import com.consoltant.consoltant.domain.portfoliocomment.entity.PortfolioComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PortfolioCommentMapper {

    PortfolioComment toPortfolioComment(PortfolioCommentRequestDto portfolioCommentRequestDto);

    @Mapping(source = "portfolio.id", target = "portfolioId")
    @Mapping(source = "user.id", target = "userId")
    PortfolioCommentResponseDto toPortfolioCommentResponseDto(PortfolioComment portfolioComment);

}
