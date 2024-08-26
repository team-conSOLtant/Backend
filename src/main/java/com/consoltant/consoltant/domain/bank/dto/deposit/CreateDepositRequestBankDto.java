package com.consoltant.consoltant.domain.bank.dto.deposit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDepositRequestBankDto {
    private String bankCode;
    private String accountName;
    private String accountDescription;
    private String subscriptionPeriod;
    private Long minSubscriptionBalance;
    private Long maxSubscriptionBalance;
    private Double interestRate;
    private String rateDescription;
}
