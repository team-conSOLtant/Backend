package com.consoltant.consoltant.domain.bank.dto.loan;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLoanRequestDto {
    private String bankCode;
    private String accountName;
    private String accountDescription;
}
