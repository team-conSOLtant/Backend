package com.consoltant.consoltant.domain.bank.dto.demanddeposit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDemandDepositRequestDto {
    private String bankCode;
    private String accountName;
    private String accountDescription;
}
