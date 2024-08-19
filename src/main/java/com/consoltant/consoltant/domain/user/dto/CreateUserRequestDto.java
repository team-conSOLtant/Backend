package com.consoltant.consoltant.domain.user.dto;

import com.consoltant.consoltant.domain.university.entity.University;

public class CreateUserRequestDto {
    private University university;

    private String email;

    private String password;

    private String name;

    private Integer age;

    private String phoneNumber;

    private String birthDate;

    private String major;
}
