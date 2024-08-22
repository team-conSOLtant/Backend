package com.consoltant.consoltant.util.api.dto.createloanproduct;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLoanProductResponseDto {
    private String accountTypeUniqueNo;
    private String bankCode;
    private String bankName;
    private String ratingUniqueNo;
    private String accountName;
    private Integer loanPeriod;
    private Long minLoanBalance;
    private Long maxLoanBalance;
    private Double interestRate;
    private String accountDescription;
    private String accountTypeCode;
    private String accountTypeName;
    private String loanTypeCode;
    private String loanTypeName;
    private String repaymentMethodTypeCode;
    private String repaymentMethodTypeName;
}
