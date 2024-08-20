package com.consoltant.consoltant.domain.portfolio.dto;

import com.consoltant.consoltant.util.constant.FinanceKeyword;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioResponseDto {

    private Long id;
    private Double totalGpa;
    private Double majorGpa;
    private FinanceKeyword financeKeyword;
    private String myKeyword;
    private String job;
    private String imageUrl;
    private String description;
    private String backgroundColor;
    private Boolean isDeleted;

}
