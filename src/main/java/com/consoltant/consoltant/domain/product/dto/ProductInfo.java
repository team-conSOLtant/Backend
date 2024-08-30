package com.consoltant.consoltant.domain.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInfo {
    private String bankCode;
    private String bankName;
    private String userName;
    private String accountNo;
    private String accountName;
    private String accountTypeCode;
    private String accountTypeName;
    private String accountCreatedDate;
    private String accountExpiryDate;
    private Long dailyTransferLimit;
    private Long oneTimeTransferLimit;
    private Long accountBalance;
    private String lastTransactionDate;
    private String currency;
    private Integer interest;
}
