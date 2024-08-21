package com.consoltant.consoltant.util.api.global.header;

import lombok.*;

@Data
public class ResponseHeader {
    private String responseCode;
    private String responseMessage;
    private String apiName;
    private String transmissionDate;
    private String transmissionTime;
    private String institutionCode;
    private String apiKey;
    private String apiServiceCode;

    //선택
    private String institutionTransactionUniqueNo;
//    private String fintechAppNo;
}
