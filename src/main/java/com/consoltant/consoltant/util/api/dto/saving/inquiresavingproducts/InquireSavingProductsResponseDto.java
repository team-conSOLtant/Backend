package com.consoltant.consoltant.util.api.dto.saving.inquiresavingproducts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InquireSavingProductsResponseDto {
    private String accountTypeUniqueNo;
    private String bankCode;
    private String bankName;
    private String accountTypeCode;
    private String accountTypeName;
    private String accountName;
    private String accountDescription;
    private String subscriptionPeriod;
    private Long minSubscriptionBalance;
    private Long maxSubscriptionBalance;
    private Double interestRate;
    private String rateDescription;
    private String startDate;
    private String endDate;
    private Long balance;
    private Integer age;

    // 복사 생성자
    public InquireSavingProductsResponseDto(InquireSavingProductsResponseDto source) {
        this.accountTypeUniqueNo = source.accountTypeUniqueNo;
        this.bankCode = source.bankCode;
        this.bankName = source.bankName;
        this.accountTypeCode = source.accountTypeCode;
        this.accountTypeName = source.accountTypeName;
        this.accountName = source.accountName;
        this.accountDescription = source.accountDescription;
        this.subscriptionPeriod = source.subscriptionPeriod;
        this.minSubscriptionBalance = source.minSubscriptionBalance;
        this.maxSubscriptionBalance = source.maxSubscriptionBalance;
        this.interestRate = source.interestRate;
        this.rateDescription = source.rateDescription;
        this.startDate = source.startDate;
        this.endDate = source.endDate;
        this.balance = source.balance;
        this.age = source.age;
    }
}