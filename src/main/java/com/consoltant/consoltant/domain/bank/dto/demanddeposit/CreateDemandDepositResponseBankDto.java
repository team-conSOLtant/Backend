package com.consoltant.consoltant.domain.bank.dto.demanddeposit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDemandDepositResponseBankDto {
    private String accountTypeUniqueNo;
    private String bankCode;
    private String bankName;
    private String accountTypeCode;
    private String accountTypeName;
    private String accountName;
    private String accountDescription;
    private String accountType;
}
