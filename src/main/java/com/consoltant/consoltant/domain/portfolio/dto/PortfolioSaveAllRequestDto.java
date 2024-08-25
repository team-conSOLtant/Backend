package com.consoltant.consoltant.domain.portfolio.dto;

import com.consoltant.consoltant.domain.activity.dto.ActivityRequestDto;
import com.consoltant.consoltant.domain.award.dto.AwardRequestDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioSaveAllRequestDto {


    // Portfolio 정보
    private Long portfolioId;

    private PortfolioRequestDto portfolioRequestDto;

    // Activity 리스트
    private List<ActivityRequestDto> activities;

    // Award 리스트
    private List<AwardRequestDto> awards;
//
//    // Project 리스트
//    private List<ProjectSaveAllRequestDto> projects;
//
//    // Certification 리스트
//    private List<CertificationSaveAllRequestDto> certifications;
//
//    // Career 리스트
//    private List<CareerSaveAllRequestDto> careers;


}
