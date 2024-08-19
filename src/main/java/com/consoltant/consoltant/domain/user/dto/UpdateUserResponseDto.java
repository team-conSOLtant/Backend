package com.consoltant.consoltant.domain.user.dto;

import com.consoltant.consoltant.domain.university.entity.University;
import com.consoltant.consoltant.util.constant.JourneyType;

public class UpdateUserResponseDto {
    private Long id;

    private University university;

    private String email;

    private String password;

    private String userKey;

    private String name;

    private Integer age;

    private String phoneNumber;

    private String birthDate;

    private String major;

    private Double totalGpa;

    private Double majorGpa;

    private Integer totalSumGpa;

    private Boolean isEmployed;

    private String accountNo;

    private Integer salary;

    private JourneyType currentJourneyType; // Custom enum for 여정중 현재인 것 조회용

    private Boolean isDeleted;
}
