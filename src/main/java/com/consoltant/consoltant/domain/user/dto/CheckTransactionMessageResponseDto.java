package com.consoltant.consoltant.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class
CheckTransactionMessageResponseDto {
    private String transactionUniqueNo;
    private String transactionDate;
    private String transactionTime;
    private String transactionType;
    private String transactionTypeName;
    private String transactionBalance;
    private String transactionAfterBalance;
    private String transactionSummary;
    private String transactionMemo;
}
