package com.consoltant.consoltant.domain.roadmap.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreRecommendDto {
    private String accountTypeUniqueNo;
    private String bankCode;
    private String bankName;
    private String accountTypeCode;
    private String accountTypeName;
    private String accountName;
    private String accountDescription;
    private String accountType;
    private String startDate;
    private String endDate;
    private Long balance;
    private Integer age;
    private Integer interestRate;
}
