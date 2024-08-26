package com.consoltant.consoltant.domain.user.dto;

import com.consoltant.consoltant.domain.university.dto.UniversityResponseDto;
import com.consoltant.consoltant.util.constant.JourneyType;
import java.time.LocalDate;
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

    private Double maxGpa;  //기준학점 4.3인지 4.5인지

    private Integer credit; // 내 총 이수학점

    private Integer maxCredit; // 필요한 총 이수학점

    private String degree;  //학사 석사 박사 나누는 기준

    private LocalDate startDate;    //학사 시작 기간

    private LocalDate endDate;  //학사 종료 기간

    private Boolean isEmployed;

    private String corporateName;

    private String accountNo;

    private Integer salary;

    private JourneyType currentJourneyType; // Custom enum for 여정중 현재인 것 조회용
}
