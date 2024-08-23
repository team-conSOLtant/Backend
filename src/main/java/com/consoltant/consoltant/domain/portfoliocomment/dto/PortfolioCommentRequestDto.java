package com.consoltant.consoltant.domain.portfoliocomment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioCommentRequestDto {

    private Long portfolioId;
    private Long userId;
    private String comment;

}
