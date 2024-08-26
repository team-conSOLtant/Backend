package com.consoltant.consoltant.domain.journey.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class JourneyStatsResponseDto {

    private String accountName;
    private String accountType;
    private Long totalAssetValue;

    private String journeyType;

    //색
    private String HEX;
    private String RGBA;

    //수시입출금 비율
    private Double demandDeposit;
    //예금 비율
    private Double deposit;
    //적금 비율
    private Double savings;
    //대출 비율
    private Double loan;

    //수시입출금 비율
    private Long demandDepositValue;
    //예금 금액
    private Long depositValue;
    //적금 금액
    private Long savingsValue;
    //대출 금액
    private Long loanValue;
}
