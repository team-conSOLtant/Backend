package com.consoltant.consoltant.domain.journey.dto;

import com.consoltant.consoltant.util.constant.JourneyType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class JourneyStatsResponseDto {

    private String accountName;
    private String accountType;
    private Long totalAssetValue;

    private String journeyTypeName;
    private JourneyType journeyType;

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
