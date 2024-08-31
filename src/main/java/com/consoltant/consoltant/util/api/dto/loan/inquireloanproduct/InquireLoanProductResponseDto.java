package com.consoltant.consoltant.util.api.dto.loan.inquireloanproduct;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InquireLoanProductResponseDto {
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
    private String startDate;
    private String endDate;
    private Long balance;
    private Integer age;

    // 복사 생성자
    public InquireLoanProductResponseDto(InquireLoanProductResponseDto source) {
        this.accountTypeUniqueNo = source.accountTypeUniqueNo;
        this.bankCode = source.bankCode;
        this.bankName = source.bankName;
        this.ratingUniqueNo = source.ratingUniqueNo;
        this.accountName = source.accountName;
        this.loanPeriod = source.loanPeriod;
        this.minLoanBalance = source.minLoanBalance;
        this.maxLoanBalance = source.maxLoanBalance;
        this.interestRate = source.interestRate;
        this.accountDescription = source.accountDescription;
        this.accountTypeCode = source.accountTypeCode;
        this.accountTypeName = source.accountTypeName;
        this.loanTypeCode = source.loanTypeCode;
        this.loanTypeName = source.loanTypeName;
        this.repaymentMethodTypeCode = source.repaymentMethodTypeCode;
        this.repaymentMethodTypeName = source.repaymentMethodTypeName;
        this.startDate = source.startDate;
        this.endDate = source.endDate;
        this.balance = source.balance;
        this.age = source.age;
    }
}