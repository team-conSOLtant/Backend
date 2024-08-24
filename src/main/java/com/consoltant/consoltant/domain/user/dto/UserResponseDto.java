package com.consoltant.consoltant.domain.user.dto;

import com.consoltant.consoltant.domain.university.dto.UniversityResponseDto;
import com.consoltant.consoltant.domain.university.entity.University;
import com.consoltant.consoltant.util.constant.JourneyType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponseDto {
    private Long id;

    private UniversityResponseDto university;

    private String email;

    private String name;

    private Integer age;

    private String phoneNumber;

    private String birthDate;

    private String major;

    private Double totalGpa;

    private Double majorGpa;

    private Integer totalSumGpa;

    private Boolean isEmployed;

    private String corporateName;

    private String accountNo;

    private Integer salary;

    private JourneyType currentJourneyType; // Custom enum for 여정중 현재인 것 조회용
}
