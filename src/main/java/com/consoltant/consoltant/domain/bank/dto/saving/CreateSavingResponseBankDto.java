package com.consoltant.consoltant.domain.bank.dto.saving;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSavingResponseBankDto {
    private String bankCode;
    private String accountName;
    private String accountDescription;
}
