package com.consoltant.consoltant.domain.bank.dto.loan;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLoanRequestBankDto {
    private String bankCode;
    private String accountName;
    private String accountDescription;
    private String ratingUniqueNo;
    private Integer loanPeriod;
    private Long minLoanBalance;
    private Long maxLoanBalance;
    private Double interestRate;
}
