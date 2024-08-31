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
    private Long demandDeposit;
    //예금 비율
    private Long deposit;
    //적금 비율
    private Long savings;
    //대출 비율
    private Long loan;

    //수시입출금 비율
    private Long demandDepositValue;
    //예금 금액
    private Long depositValue;
    //적금 금액
    private Long savingsValue;
    //대출 금액
    private Long loanValue;

    //수시입출금 개수
    private Integer demandDepositCount;
    //예금 비율
    private Integer depositCount;
    //적금 비율
    private Integer savingsCount;
    //대출 비율
    private Integer loanCount;
}
