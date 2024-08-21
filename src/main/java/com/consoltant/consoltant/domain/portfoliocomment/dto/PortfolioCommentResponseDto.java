package com.consoltant.consoltant.domain.portfoliocomment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioCommentResponseDto {

    private Long id;
    private Long portfolioId;
    private Long userId;
    private String comment;
    private Boolean isDeleted;

}
