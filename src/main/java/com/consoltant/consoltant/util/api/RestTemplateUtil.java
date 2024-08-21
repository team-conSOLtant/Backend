package com.consoltant.consoltant.util.api;


import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.api.dto.checkauthcode.CheckAuthCodeResponseDto;
import com.consoltant.consoltant.util.api.dto.inquiretransactionhistory.InquireTransactionHistoryResponseDto;
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
import java.util.UUID;

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
//    @Value("${institution.transaction.unique.no}")
//    private String institutionTransactionUniqueNo;

    public static String generateNumericUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("[^0-9]", "");
    }

    private RequestHeader requestHeader(String name, String userKey){
        LocalDateTime today = LocalDateTime.now();

        String date = today.toString().split("T")[0].replace("-","");
        String time = today.toString().split("T")[1].substring(0,8).replace(":","");

        //시퀀스 or UUID or 사용자 아아디 추가
        String numericUUID = generateNumericUUID();

        String institutionTransactionUniqueNo = date + time + numericUUID.substring(0,(Math.min(numericUUID.length(), 6)));

        log.info("기관거래고유번호 -> {}",institutionTransactionUniqueNo);

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

    //1원 송금
    public OpenAccountAuthResponseDto openAccountAuth(String userKey, String accountNo) {
        log.info("1원 송금 API");

        String uri = "edu/accountAuth/openAccountAuth";

        String name = "openAccountAuth";

        Map<String,Object> requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,userKey);

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

    // 1원 송금 인증
    public CheckAuthCodeResponseDto checkAuthCode(String userKey, String accountNo, String authText, String authCode) {
        log.info("1원 송금 인증 API");

        String uri = "edu/accountAuth/checkAuthCode";

        String name = "checkAuthCode";

        Map<String,Object> requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,userKey);

        requestBody.put("Header",headers);
        requestBody.put("accountNo",accountNo);
        requestBody.put("authText",authText);
        requestBody.put("authCode",authCode);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<CheckAuthCodeResponseDto>> response
                = restTemplate.exchange(
                url + uri, HttpMethod.POST, entity,
                new ParameterizedTypeReference<>(){}
        );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }
        log.info("Status -> {}", response.getBody().getREC().getStatus());

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

        RequestHeader headers = requestHeader(name, userKey);

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
    
    //계좌 거래 내역 단건 조회
    public InquireTransactionHistoryResponseDto inquireTransactionHistoryResponseDto(String userKey, String accountNo, String transactionUniqueNo){

        final String name = "inquireTransactionHistory";
        log.info("금융 API 계좌 거래 내역 단건 조회");

        String uri = "edu/demandDeposit/inquireTransactionHistory";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name, userKey);

        requestBody.put("Header",headers);
        requestBody.put("accountNo",accountNo);
        requestBody.put("transactionUniqueNo",transactionUniqueNo);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<InquireTransactionHistoryResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

}