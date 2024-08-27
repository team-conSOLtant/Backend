package com.consoltant.consoltant.domain.portfolio.dto;

import lombok.Getter;

@Getter
public class PortfolioSearchRequestDto {
    private String keyword;
    private Boolean isEmployed;
    private Double minGpa;
    private Double maxGpa;
}
