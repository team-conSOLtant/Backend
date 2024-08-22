package com.consoltant.consoltant.util.api.dto.inquiresavinginfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquireSavingInfoResponseDto {
    private String bankCode;
    private String bankName;
    private String userName;
    private String accountNo;
    private String accountName;
    private String accountDescription;
    private String withdrawalBankCode;
    private String withdrawalAccountNo;
    private String subscriptionPeriod;
    private Long depositBalance;
    private Double interestRate;
    private String accountCreateDate;
    private String accountExpiryDate;
}
