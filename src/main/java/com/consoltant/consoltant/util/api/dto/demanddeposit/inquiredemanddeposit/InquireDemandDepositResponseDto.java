package com.consoltant.consoltant.util.api.dto.demanddeposit.inquiredemanddeposit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private Integer age;

    // 복사 생성자
    public InquireDemandDepositResponseDto(InquireDemandDepositResponseDto source) {
        this.accountTypeUniqueNo = source.accountTypeUniqueNo;
        this.bankCode = source.bankCode;
        this.bankName = source.bankName;
        this.accountTypeCode = source.accountTypeCode;
        this.accountTypeName = source.accountTypeName;
        this.accountName = source.accountName;
        this.accountDescription = source.accountDescription;
        this.accountType = source.accountType;
        this.startDate = source.startDate;
        this.endDate = source.endDate;
        this.balance = source.balance;
        this.age = source.age;
    }
}