package com.consoltant.consoltant.domain.user.dto;

import com.consoltant.consoltant.domain.university.entity.University;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserAcademyRequestDto {
    private Long id;

    private University university;

    private String major;

    private Double totalGpa;

    private Double majorGpa;

    private Integer totalSumGpa;

}
