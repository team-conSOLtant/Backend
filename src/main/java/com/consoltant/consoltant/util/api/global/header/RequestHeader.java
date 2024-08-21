package com.consoltant.consoltant.util.api.global.header;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Builder
@Getter
@ToString
public class RequestHeader {
    //필수
    private String apiName;
    private String transmissionDate;
    private String transmissionTime;
    @Value("${institution.code}")
    private String institutionCode;
    @Value("${fintech.app.no}")
    private String fintechAppNo;
    private String apiServiceCode;
    @Value("${api.key}")
    private String apiKey;

    //선택
    @Value("${institution.transaction.unique.no}")
    private String institutionTransactionUniqueNo;
    private String userKey;
}
