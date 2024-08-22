package com.consoltant.consoltant.util.api.dto.inquiredemanddepositaccountbalance;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquireDemandDepositAccountBalanceResponseDto {
    private String bankCode;
    private String accountNo;
    private Long accountBalance;
    private String accountCreatedDate;
    private String accountExpireDate;
    private String lastTransactionDate;
    private String currency;
}
