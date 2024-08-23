package com.consoltant.consoltant.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class CheckAccountRequestDto {
    private String accountNo;
    private String authText;
    private String authCode;
}
