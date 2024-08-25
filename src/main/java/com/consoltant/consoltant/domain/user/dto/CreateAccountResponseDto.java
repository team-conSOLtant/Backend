package com.consoltant.consoltant.domain.user.dto;

import com.consoltant.consoltant.util.api.dto.demanddeposit.createdemanddepositaccount.Currency;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateAccountResponseDto {
    private String bankCode;
    private String accountNo;
    private Currency currency;
}
