package com.consoltant.consoltant.domain.portfolio.dto;

import com.consoltant.consoltant.domain.activity.dto.ActivityRequestDto;
import com.consoltant.consoltant.domain.award.dto.AwardRequestDto;
import com.consoltant.consoltant.domain.career.dto.CareerRequestDto;
import com.consoltant.consoltant.domain.certification.dto.CertificationRequestDto;
import com.consoltant.consoltant.domain.course.dto.CourseRequestDto;
import com.consoltant.consoltant.domain.project.dto.ProjectRequestDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioSaveAllRequestDto {


    // Portfolio 정보
    private Long portfolioId;

    private Long userId;

    private List<CourseRequestDto> courses;

    private PortfolioRequestDto portfolioRequestDto;

    // Activity 리스트
    private List<ActivityRequestDto> activities;

    // Award 리스트
    private List<AwardRequestDto> awards;

    // Project 리스트
    private List<ProjectRequestDto> projects;

    // Certification 리스트
    private List<CertificationRequestDto> certifications;

    // Career 리스트
    private List<CareerRequestDto> careers;

    // es 전체 컨텐츠
    private String allContent;
}
