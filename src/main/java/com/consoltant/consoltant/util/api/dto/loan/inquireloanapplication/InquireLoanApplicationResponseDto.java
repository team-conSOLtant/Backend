package com.consoltant.consoltant.util.api.dto.loan.inquireloanapplication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquireLoanApplicationResponseDto {
    private String accountTypeUniqueNo;
    private String status;
    private String bankCode;
    private String bankName;
    private String ratingUniqueNo;
}
