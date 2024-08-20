package com.consoltant.consoltant.util.api;


import com.consoltant.consoltant.util.api.dto.CreateMemberResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class RestTemplateUtil {
    @Autowired
    private RestTemplate restTemplate;
    private final String url = "https://finopenapi.ssafy.io/ssafy/api/v1/";

    // GET 요청 메소드
    public String sendGetRequestWithHeaders(String url, HttpHeaders headers) {
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);

        return response.getBody();
    }

    // POST 요청
    public String openAccountAuth(String accountNo, String userKey) {
        String url = "https://finopenapi.ssafy.io/ssafy/api/v1/edu/accountAuth/openAccountAuth";
        Map<String,Object> requestBody = new HashMap<>();
        Map<String,String> headers = new HashMap<>();

        //공통
        headers.put("institutionCode","00100");
        headers.put("fintechAppNo","001");
        headers.put("apiKey","fda96747b542462caf0826cedcebd984");

        //세부
        headers.put("apiName","openAccountAuth");
        headers.put("apiServiceCode","openAccountAuth");
        headers.put("transmissionDate","20240723");
        headers.put("transmissionTime","152345");
        headers.put("insititutionTransactionUniqueNo","20240723152345666098");

        headers.put("userKey",userKey);

        requestBody.put("Header",headers);
        requestBody.put("accountNo",accountNo);
        requestBody.put("authText","SSAFY");

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return response.getBody();
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
        log.info("Response -> {}",response.getBody());
        log.info("UserKey -> {}", response.getBody().getUserKey());

        return response.getBody().getUserKey();
    }

}