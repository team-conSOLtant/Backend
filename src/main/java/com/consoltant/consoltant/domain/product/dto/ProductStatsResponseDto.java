package com.consoltant.consoltant.domain.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class ProductStatsResponseDto {
    private String accountNo;
    private String accountName;
    private String accountType;
    private Long totalAssetValue;

    //수시입출금 비율
    private Double demandDeposit;
    //예금 비율
    private Double deposit;
    //적금 비율
    private Double savings;
    //대출 비율
    private Double loan;
}
