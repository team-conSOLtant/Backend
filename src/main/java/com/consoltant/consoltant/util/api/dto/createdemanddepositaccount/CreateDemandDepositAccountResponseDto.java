package com.consoltant.consoltant.util.api.dto.createdemanddepositaccount;

import lombok.*;

@Data
public class CreateDemandDepositAccountResponseDto {
    private String bankCode;
    private String accountNo;
    private Currency currency;
}
