package com.consoltant.consoltant.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequestDto {

    private String birthDate;
    private String phoneNumber;
    private String email;

}
