package com.consoltant.consoltant.domain.bank.dto.saving;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSavingResponseBankDto {
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
}
