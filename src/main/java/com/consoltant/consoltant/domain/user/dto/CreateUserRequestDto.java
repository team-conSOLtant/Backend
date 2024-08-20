package com.consoltant.consoltant.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestDto {
    private String email;

    private String password;

    private String name;

    private Integer age;

    private String phoneNumber;

    private String birthDate;

}
