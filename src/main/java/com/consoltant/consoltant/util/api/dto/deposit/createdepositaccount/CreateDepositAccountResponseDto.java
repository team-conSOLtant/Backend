package com.consoltant.consoltant.util.api.dto.deposit.createdepositaccount;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDepositAccountResponseDto {
    private String bankCode;
    private String bankName;
    private String accountNo;
    private String accountName;
    private String withdrawalAccountNo;
    private String withdrawAccountNo;
    private String subscriptionPeriod;
    private Long depositBalance;
    private Double interestRate;
    private String accountCreateDate;
    private String accountExpiryDate;
}
