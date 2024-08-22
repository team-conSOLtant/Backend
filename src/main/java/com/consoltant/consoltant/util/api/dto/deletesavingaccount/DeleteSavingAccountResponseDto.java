package com.consoltant.consoltant.util.api.dto.deletesavingaccount;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteSavingAccountResponseDto {
    private String status;
    private String bankCode;
    private String bankName;
    private String accountNo;
    private String accountName;
    private Long totalBalance;
    private Long earlyTerminationInterest;
    private Long earlyTerminationBalance;
    private String earlyTerminationDate;
}
