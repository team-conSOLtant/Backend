package com.consoltant.consoltant.domain.user.dto;

import com.consoltant.consoltant.util.constant.JourneyType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserAccountRequestDto {
    private Long id;

    private Boolean isEmployed;

    private String accountNo;
}
