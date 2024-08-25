package com.consoltant.consoltant.util.api.dto.loan.inquireloanaccount;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquireLoanAccountResponseDto {
    private String accountNo;
    private String accountName;
    private String status;
    private String accountTypeUniqueNo;
    private int loanPeriod;
    private String loanDate;
    private String maturityDate;
    private Long loanBalance;
    private Double interestRate;
    private String withdrawalAccountNo;
}
