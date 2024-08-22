package com.consoltant.consoltant.util.api.dto.createdemanddeposit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDemandDepositResponseDto {
    private String bankCode;
    private String accountName;
    private String accountDescription;
}
