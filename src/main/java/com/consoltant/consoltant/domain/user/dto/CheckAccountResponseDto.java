package com.consoltant.consoltant.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CheckAccountResponseDto {
    private String status;
    private String transactionUniqueNo;
    private String accountNo;
}
