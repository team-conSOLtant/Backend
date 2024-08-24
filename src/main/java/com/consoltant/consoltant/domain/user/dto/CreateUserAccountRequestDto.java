package com.consoltant.consoltant.domain.user.dto;

import com.consoltant.consoltant.util.constant.JourneyType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserAccountRequestDto {
    private Boolean isEmployed;

    private Integer salary;

    private String accountNo;

    private String corporateName;
}
