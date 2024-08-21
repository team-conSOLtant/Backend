package com.consoltant.consoltant.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class IssueAccountResponseDto {
    private String transactionUniqueNo;
    private String accountNo;
}
