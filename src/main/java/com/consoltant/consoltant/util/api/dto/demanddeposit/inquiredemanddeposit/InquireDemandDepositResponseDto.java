package com.consoltant.consoltant.util.api.dto.demanddeposit.inquiredemanddeposit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquireDemandDepositResponseDto {
    private String accountTypeUniqueNo;
    private String bankCode;
    private String bankName;
    private String accountTypeCode;
    private String accountTypeName;
    private String accountName;
    private String accountDescription;
    private String accountType;
    private String startDate;
    private String endDate;
    private Long balance;
}
