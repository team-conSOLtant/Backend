package com.consoltant.consoltant.util.api;


import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.api.dto.openaccountauth.OpenAccountAuthResponseDto;
import com.consoltant.consoltant.util.api.global.response.RECResponse;
import com.consoltant.consoltant.util.api.dto.createdemanddepositaccount.CreateDemandDepositAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.createmember.CreateMemberResponseDto;
import com.consoltant.consoltant.util.api.global.header.RequestHeader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class RestTemplateUtil {
    @Autowired
    private RestTemplate restTemplate;
    private final String url = "https://finopenapi.ssafy.io/ssafy/api/v1/";

    @Value("${institution.code}")
    private String institutionCode;
    @Value("${fintech.app.no}")
    private String fintechAppNo;
    @Value("${api.key}")
    private String apiKey;

    private RequestHeader requestHeader(String name, String userKey, String institutionTransactionUniqueNo){
        LocalDateTime today = LocalDateTime.now();

        String date = today.toString().split("T")[0].replace("-","");
        String time = today.toString().split("T")[1].substring(0,8).replace(":","");

        return RequestHeader.builder()
                .apiName(name)
                .apiKey(apiKey)
                .apiServiceCode(name)
                .transmissionDate(date)
                .transmissionTime(time)
                .fintechAppNo(fintechAppNo)
                .institutionCode(institutionCode)
                .institutionTransactionUniqueNo(institutionTransactionUniqueNo)
                .userKey(userKey)
                .build();
    }

    //선택
    @Value("${institution.transaction.unique.no}")
    private String institutionTransactionUniqueNo;

    // POST 요청
    public OpenAccountAuthResponseDto openAccountAuth(String accountNo, String userKey) {
        log.info("1원 송금 API");

        String uri = "edu/accountAuth/openAccountAuth";

        String name = "openAccountAuth";

        Map<String,Object> requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,userKey,institutionTransactionUniqueNo);

        requestBody.put("Header",headers);
        requestBody.put("accountNo",accountNo);
        requestBody.put("authText","SSAFY");

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<OpenAccountAuthResponseDto>> response
                = restTemplate.exchange(
                        url + uri, HttpMethod.POST, entity,
                new ParameterizedTypeReference<>(){}
        );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }
        log.info("AccountNo -> {}", response.getBody().getREC().getAccountNo());

        return response.getBody().getREC();
    }

    // 사용자 생성
    public String createMember(String userId) {
        log.info("금융 API 사용자 생성 -> {}",userId);
        String uri = "member";

        Map<String,Object>requestBody = new HashMap<>();

        requestBody.put("apiKey","fda96747b542462caf0826cedcebd984");
        requestBody.put("userId",userId);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<CreateMemberResponseDto> response = restTemplate.postForEntity(url + uri, entity, CreateMemberResponseDto.class);
        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }
        log.info("UserKey -> {}", response.getBody().getUserKey());

        return response.getBody().getUserKey();
    }

    // 계좌 생성
    public CreateDemandDepositAccountResponseDto createDemandDepositAccount(String userKey, String accountTypeUniqueNo) {
        final String name = "createDemandDepositAccount";
        log.info("금융 API 계좌 생성 userKey -> {}",userKey);
        log.info("금융 API 계좌 생성 accountType -> {}",accountTypeUniqueNo);

        String uri = "edu/demandDeposit/createDemandDepositAccount";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name, userKey, institutionTransactionUniqueNo);

        log.info("요청 헤더 -> {}",headers);

        requestBody.put("Header",headers);
        requestBody.put("accountTypeUniqueNo",accountTypeUniqueNo);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<CreateDemandDepositAccountResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        log.info("response -> {}", response.getBody());
        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }
        log.info("Account No -> {}", response.getBody().getHeader().toString());
        log.info("Account No -> {}", response.getBody().getREC().toString());

        return response.getBody().getREC();
    }

}