package com.consoltant.consoltant.util.api.dto.createloanapplication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLoanApplicationResponseDto {
    private String status;
    private String bankCode;
    private String bankName;
    private String ratingUniqueNo;
    private String ratingName;
    private String accountName;
    private Integer loanPeriod;
    private Long minLoanBalance;
    private Long maxLoanBalance;
    private Double interestRate;
    private String accountDescription;
    private String applicationDate;
    private String applicationTime;
    private String decisionDate;
    private String decisionTime;


}
