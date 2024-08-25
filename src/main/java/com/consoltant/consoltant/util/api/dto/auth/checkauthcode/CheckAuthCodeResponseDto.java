package com.consoltant.consoltant.util.api.dto.auth.checkauthcode;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CheckAuthCodeResponseDto {
    private String status;
    private String transactionUniqueNo;
    private String accountNo;
}
