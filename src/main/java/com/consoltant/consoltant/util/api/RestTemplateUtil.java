package com.consoltant.consoltant.util.api;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class RestTemplateUtil {
    private RestTemplate restTemplate;

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
        headers.put("apiName","openAccountAuth");
        headers.put("transmissionDate","20240723");
        headers.put("transmissionTime","152345");
        headers.put("institutionCode","00100");
        headers.put("fintechAppNo","001");
        headers.put("apiServiceCode","openAccountAuth");
        headers.put("insititutionTransactionUniqueNo","20240723152345666098");
        headers.put("apiKey","fda96747b542462caf0826cedcebd984");
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
        String url = "https://finopenapi.ssafy.io/ssafy/api/v1/member";

        Map<String,Object>requestBody = new HashMap<>();
        
        requestBody.put("apiKey","fda96747b542462caf0826cedcebd984");
        requestBody.put("userId",userId);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
        return response.getBody();
    }

}