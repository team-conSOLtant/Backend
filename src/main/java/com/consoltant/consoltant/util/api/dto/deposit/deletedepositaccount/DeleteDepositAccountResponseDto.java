package com.consoltant.consoltant.util.api.dto.deposit.deletedepositaccount;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteDepositAccountResponseDto {
    private String status;
    private String bankCode;
    private String bankName;
    private String accountNo;
    private String accountName;
    private Long depositBalance;
    private Long earlyTerminationInterest;
    private Long earlyTerminationBalance;
    private String earlyTerminationDate;
}
