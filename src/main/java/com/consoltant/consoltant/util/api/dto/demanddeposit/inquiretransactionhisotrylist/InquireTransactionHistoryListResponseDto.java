package com.consoltant.consoltant.util.api.dto.demanddeposit.inquiretransactionhisotrylist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquireTransactionHistoryListResponseDto {
    private Long transactionUniqueNo;
    private String transactionDate;
    private String transactionTime;
    private String transactionType;
    private String transactionTypeName;
    private String transactionAccountNo;
    private Long transactionBalance;
    private Long transactionAfterBalance;
    private String transactionSummary;
    private String transactionMemo;
}
