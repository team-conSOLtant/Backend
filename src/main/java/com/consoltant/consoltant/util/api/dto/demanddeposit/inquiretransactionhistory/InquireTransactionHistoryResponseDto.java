package com.consoltant.consoltant.util.api.dto.demanddeposit.inquiretransactionhistory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquireTransactionHistoryResponseDto {
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
