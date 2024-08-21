package com.consoltant.consoltant.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckTransactionMessageRequestDto {
    private String accountNo;
    private String transactionUniqueNo;
}
