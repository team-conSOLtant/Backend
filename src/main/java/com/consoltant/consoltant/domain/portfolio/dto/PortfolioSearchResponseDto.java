package com.consoltant.consoltant.domain.portfolio.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PortfolioSearchResponseDto {
    private String userName;

    private String universityName;
    private String major;
    private Double totalGpa;
    private Double majorGpa;

    private String job;
    private String myKeyword;

    private Integer awardCount;
    private Integer certificationCount;
    private Integer projectCount;
}
