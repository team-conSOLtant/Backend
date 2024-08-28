package com.consoltant.consoltant.domain.portfolio.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PortfolioSearchResponseDto {
    private String userName;

    private Long portfolioId;
    private String universityName;
    private String major;
    @JsonSerialize(using = ToStringSerializer.class)
    private Double totalGpa;
    @JsonSerialize(using = ToStringSerializer.class)
    private Double majorGpa;

    private String job;
    private String myKeyword;

    private String careerTitle;
    private Integer awardCount;
    private Integer certificationCount;
    private Integer projectCount;
}
