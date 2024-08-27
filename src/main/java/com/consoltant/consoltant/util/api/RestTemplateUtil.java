package com.consoltant.consoltant.util.api;


import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.api.dto.auth.checkauthcode.CheckAuthCodeResponseDto;
import com.consoltant.consoltant.util.api.dto.demanddeposit.createdemanddeposit.CreateDemandDepositResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.createdeposit.CreateDepositResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.createdepositaccount.CreateDepositAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.createloanaccount.CreateLoanAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.createloanapplication.CreateLoanApplicationResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.createloanproduct.CreateLoanProductResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.createsaving.CreateSavingResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.createsavingaccount.CreateSavingAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.deletedepositaccount.DeleteDepositAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.deletesavingaccount.DeleteSavingAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquireassetbasedcreditrating.InquireAssetBasedCreditRatingResponseDto;
import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiredemanddeposit.InquireDemandDepositResponseDto;
import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiredemanddepositaccount.InquireDemandDepositAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiredemanddepositaccountbalance.InquireDemandDepositAccountBalanceResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.inquiredepositinfo.InquireDepositInfoResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquireloanaccount.InquireLoanAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquireloanapplication.InquireLoanApplicationResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquireloanproduct.InquireLoanProductResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquiremycreditrating.InquireMyCreditRatingResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.inquiresavinginfo.InquireSavingInfoResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.inquiresavingproducts.InquireSavingProductsResponseDto;
import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiretransactionhisotrylist.InquireTransactionHistoryListResponseDto;
import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiretransactionhistory.InquireTransactionHistoryResponseDto;
import com.consoltant.consoltant.util.api.dto.auth.openaccountauth.OpenAccountAuthResponseDto;
import com.consoltant.consoltant.util.api.global.response.CountListResponse;
import com.consoltant.consoltant.util.api.global.response.RECListResponse;
import com.consoltant.consoltant.util.api.global.response.RECResponse;
import com.consoltant.consoltant.util.api.dto.demanddeposit.createdemanddepositaccount.CreateDemandDepositAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.member.createmember.CreateMemberResponseDto;
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
import java.util.List;
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

    //정수형 UUID 생성
    private static String generateNumericUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("[^0-9]", "");
    }

    //API 호출용 Header 생성
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



    // 사용자 로그인 API
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

    // 수시입출금 API
    // 수시입출금 상품 등록
    public CreateDemandDepositResponseDto createDemandDeposit(String bankCode, String accountName, String accountDescription){
        final String name = "createDemandDepositA";
        log.info("금융 API 수시입출금 상품 등록");

        String uri = "edu/demandDeposit/createDemandDeposit";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name, null);

        requestBody.put("Header",headers);
        requestBody.put("bankCode",bankCode);
        requestBody.put("accountName",accountName);
        requestBody.put("accountDescription",accountDescription);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<CreateDemandDepositResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 수시입출금 상품 조회
    public List<InquireDemandDepositResponseDto> inquireDemandDepositList(){

        final String name = "inquireDemandDepositList";
        log.info("금융 API 수시입출금 상품 조회");

        String uri = "edu/demandDeposit/inquireDemandDepositList";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,null);

        requestBody.put("Header",headers);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECListResponse<InquireDemandDepositResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 수시입출금 계좌 목록 조회
    public List<InquireDemandDepositAccountResponseDto> inquireDemandDepositAccountList(String userKey){

        final String name = "inquireTransactionHistory";
        log.info("금융 API 수시입출금 계좌 목록 조회");

        String uri = "edu/demandDeposit/inquireDemandDepositAccountList";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name, userKey);

        requestBody.put("Header",headers);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECListResponse<InquireDemandDepositAccountResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 수시입출금 계좌 생성
    public CreateDemandDepositAccountResponseDto createDemandDepositAccount(String userKey, String accountTypeUniqueNo) {
        final String name = "createDemandDepositAccount";
        log.info("금융 API 계좌 생성 userKey -> {}",userKey);
        log.info("금융 API 계좌 생성 accountType -> {}",accountTypeUniqueNo);

        String uri = "edu/demandDeposit/createDemandDepositAccount";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name, userKey);

        requestBody.put("Header",headers);
        requestBody.put("accountTypeUniqueNo",accountTypeUniqueNo);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<CreateDemandDepositAccountResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 수시입출금 계좌 잔액 조회
    public InquireDemandDepositAccountBalanceResponseDto inquireDemandDepositAccountBalance(String userKey, String accountNo){
        final String name = "inquireDemandDepositAccountBalance";
        log.info("금융 API 수시입출금 계좌 잔액 조회");

        String uri = "edu/demandDeposit/inquireDemandDepositAccountBalance";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name, userKey);

        requestBody.put("Header",headers);
        requestBody.put("accountNo",accountNo);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<InquireDemandDepositAccountBalanceResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 수시입출금 계좌 거래 내역 조회
    public List<InquireTransactionHistoryListResponseDto> inquireTransactionHistoryList(String userKey, String accountNo){

        final String name = "inquireTransactionHistory";
        log.info("금융 API 계좌 거래 내역 조회");

        String uri = "edu/demandDeposit/inquireTransactionHistory";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name, userKey);

        requestBody.put("Header",headers);
        requestBody.put("accountNo",accountNo);
        requestBody.put("startDate",accountNo);
        requestBody.put("endDate",accountNo);
        requestBody.put("transactionType",accountNo);
        requestBody.put("orderByType",accountNo);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<CountListResponse<InquireTransactionHistoryListResponseDto>>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC().getList();
    }

    // 수시입출금 계좌 거래 내역 단건 조회
    public InquireTransactionHistoryResponseDto inquireTransactionHistory(String userKey, String accountNo, Long transactionUniqueNo){

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

    // 수시입출금 계좌 단건 조회
    public InquireDemandDepositAccountResponseDto inquireDemandDepositAccount(String userKey, String accountNo){

        final String name = "inquireDemandDepositAccount";
        log.info("금융 API 수시입출금 계좌 단건 조회");

        String uri = "edu/demandDeposit/inquireDemandDepositAccount";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name, userKey);

        requestBody.put("Header",headers);
        requestBody.put("accountNo",accountNo);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<InquireDemandDepositAccountResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 예금 API
    // 예금 상품 등록
    public CreateDepositResponseDto createDeposit(String bankCode, String accountName, String accountDescription, String subscriptionPeriod, Long minSubscriptionBalance, Long maxSubscriptionBalance, Double interestRate, String rateDescription){
        final String name = "createDeposit";
        log.info("금융 API 예금 상품 등록");

        String uri = "edu/deposit/createDeposit";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,null);

        requestBody.put("Header",headers);
        requestBody.put("bankCode",bankCode);
        requestBody.put("accountName",accountName);
        requestBody.put("accountDescription",accountDescription);
        requestBody.put("subscriptionPeriod",subscriptionPeriod);
        requestBody.put("minSubscriptionBalance",minSubscriptionBalance);
        requestBody.put("maxSubscriptionBalance",maxSubscriptionBalance);
        requestBody.put("interestRate",interestRate);
        requestBody.put("rateDescription",rateDescription);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<CreateDepositResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 예금 상품 조회
    public List<InquireDepositInfoResponseDto> inquireDepositProducts(){
        final String name = "inquireDepositProducts";
        log.info("금융 API 예금 상품 조회");

        String uri = "edu/deposit/inquireDepositProducts";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,null);

        requestBody.put("Header",headers);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECListResponse<InquireDepositInfoResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 예금 계좌 생성
    public CreateDepositAccountResponseDto createDepositAccount(String userKey, String withdrawalAccountNo, String accountTypeUniqueNo, Long depositBalance){
        final String name = "createDepositAccount";
        log.info("금융 API 예금 계좌 생성");

        String uri = "edu/deposit/createDepositAccount";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,userKey);

        requestBody.put("Header",headers);
        requestBody.put("withdrawalAccountNo",withdrawalAccountNo);
        requestBody.put("accountTypeUniqueNo",accountTypeUniqueNo);
        requestBody.put("depositBalance",depositBalance);


        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<CreateDepositAccountResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 예금 계좌 목록 조회
    public List<InquireDepositInfoResponseDto> inquireDepositInfoList(String userKey){
        final String name = "inquireDepositInfoList";
        log.info("금융 API 예금 계좌 목록 조회");

        String uri = "edu/deposit/inquireDepositInfoList";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,userKey);

        requestBody.put("Header",headers);


        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<CountListResponse<InquireDepositInfoResponseDto>>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC().getList();
    }

    // 예금 계좌 해지 -> 만기 시 or 중도 해지 시
    public DeleteDepositAccountResponseDto deleteDepositAccount(String userKey, String accountNo){
        final String name = "deleteAccount";
        log.info("금융 API 예금 계좌 해지 ");

        String uri = "edu/deposit/deleteAccount";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,userKey);

        requestBody.put("Header",headers);
        requestBody.put("accountNo",accountNo);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<DeleteDepositAccountResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 적금 API
    // 적금 상품 등록
    public CreateSavingResponseDto createSaving(String bankCode, String accountName, String accountDescription, String subscriptionPeriod, Long minSubscriptionBalance, Long maxSubscriptionBalance, Double interestRate, String rateDescription){
        final String name = "createProduct";
        log.info("금융 API 적금 상품 등록");

        String uri = "edu/savings/createProduct";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,null);

        requestBody.put("Header",headers);
        requestBody.put("bankCode",bankCode);
        requestBody.put("accountName",accountName);
        requestBody.put("accountDescription",accountDescription);
        requestBody.put("subscriptionPeriod",subscriptionPeriod);
        requestBody.put("minSubscriptionBalance",minSubscriptionBalance);
        requestBody.put("maxSubscriptionBalance",maxSubscriptionBalance);
        requestBody.put("interestRate",interestRate);
        requestBody.put("rateDescription",rateDescription);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<CreateSavingResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 적금 상품 조회
    public List<InquireSavingProductsResponseDto> inquireSavingProducts(){
        final String name = "inquireSavingsProducts";
        log.info("금융 API 적금 상품 조회");

        String uri = "edu/savings/inquireSavingsProducts";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,null);

        requestBody.put("Header",headers);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECListResponse<InquireSavingProductsResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 적금 계좌 생성
    public CreateSavingAccountResponseDto createSavingAccount(String userKey, String withdrawalAccountNo, String accountTypeUniqueNo, Long depositBalance){
        final String name = "createSavingAccount";
        log.info("금융 API 적금 계좌 생성");

        String uri = "edu/savings/createSavingAccount";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,userKey);

        requestBody.put("Header",headers);
        requestBody.put("withdrawalAccountNo",withdrawalAccountNo);
        requestBody.put("accountTypeUniqueNo",accountTypeUniqueNo);
        requestBody.put("depositBalance",depositBalance);


        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<CreateSavingAccountResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // TODO 적금 계좌 목록 조회
    public List<InquireSavingInfoResponseDto> inquireSavingInfoList(String userKey){
        final String name = "inquireAccountList";
        log.info("금융 API 적금 계좌 목록 조회");

        String uri = "edu/savings/inquireAccountList";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,userKey);

        requestBody.put("Header",headers);


        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<CountListResponse<InquireSavingInfoResponseDto>>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC().getList();
    }

    // TODO 적금 계좌 해지 -> 만기 시 or 중도 해지 시
    public DeleteSavingAccountResponseDto deleteSavingAccount(String userKey, String accountNo){
        final String name = "deleteAccount";
        log.info("금융 API 예금 계좌 해지 ");

        String uri = "edu/savings/deleteAccount";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,userKey);

        requestBody.put("Header",headers);
        requestBody.put("accountNo",accountNo);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<DeleteSavingAccountResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 대출 API
    // 신용등급 기준 조회
    public List<InquireAssetBasedCreditRatingResponseDto> inquireAssetBasedCreditRating(){
        final String name = "deleteAccount";
        log.info("금융 API 신용등급 기준 조회 ");

        String uri = "edu/loan/inquireAssetBasedCreditRating";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,null);

        requestBody.put("Header",headers);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECListResponse<InquireAssetBasedCreditRatingResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 대출 상품 등록
    public CreateLoanProductResponseDto createLoanProduct(
            String bankCode,
            String accountName,
            String accountDescription,
            String ratingUniqueNo,
            Integer loanPeriod,
            Long minLoanBalance,
            Long maxLoanBalance,
            Double interestRate
    ){
        final String name = "createLoanProduct";
        log.info("금융 API 대출 상품 등록 ");

        String uri = "edu/loan/createLoanProduct";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,null);

        requestBody.put("Header",headers);
        requestBody.put("bankCode",bankCode);
        requestBody.put("accountName",accountName);
        requestBody.put("accountDescription",accountDescription);
        requestBody.put("ratingUniqueNo",ratingUniqueNo);
        requestBody.put("loanPeriod",loanPeriod);
        requestBody.put("minLoanBalance",minLoanBalance);
        requestBody.put("maxLoanBalance",maxLoanBalance);
        requestBody.put("interestRate",interestRate);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<CreateLoanProductResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 대출 상품 조회
    public List<InquireLoanProductResponseDto> inquireLoanProductList(){
        final String name = "inquireLoanProductList";
        log.info("금융 API 대출 상품 조회");

        String uri = "edu/loan/inquireLoanProductList";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,null);

        requestBody.put("Header",headers);


        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECListResponse<InquireLoanProductResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 내 신용등급 조회
    public InquireMyCreditRatingResponseDto inquireMyCreditRating(String userKey){
        final String name = "inquireMyCreditRating";
        log.info("금융 API 내 신용등급 조회");

        String uri = "edu/loan/inquireMyCreditRating";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,userKey);

        requestBody.put("Header",headers);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<InquireMyCreditRatingResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 대출심사 신청
    public CreateLoanApplicationResponseDto createLoanApplication(String userKey, String accountTypeUniqueNo){
        final String name = "createLoanApplication";
        log.info("금융 API 대출심사 신청");

        String uri = "edu/loan/createLoanApplication";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,userKey);

        requestBody.put("Header",headers);
        requestBody.put("accountTypeUniqueNo",accountTypeUniqueNo);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<CreateLoanApplicationResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 대출 심사 목록 조회
    public List<InquireLoanApplicationResponseDto> inquireLoanApplicationList(String userKey){
        final String name = "inquireLoanApplicationList";
        log.info("금융 API 대출 심사 목록 조회");

        String uri = "edu/loan/inquireLoanApplicationList";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,userKey);

        requestBody.put("Header",headers);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECListResponse<InquireLoanApplicationResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    //대출 상품 가입
    public CreateLoanAccountResponseDto createLoanAccount(String userKey, String accountTypeUniqueNo, Long loanBalance, String withdrawalAccountNo){
        final String name = "createLoanAccount";
        log.info("금융 API 대출 상품 가입");

        String uri = "edu/loan/createLoanAccount";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,userKey);

        requestBody.put("Header",headers);
        requestBody.put("accountTypeUniqueNo",accountTypeUniqueNo);
        requestBody.put("loanBalance",loanBalance);
        requestBody.put("withdrawalAccountNo",withdrawalAccountNo);


        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECResponse<CreateLoanAccountResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    // 대출 상품 가입 목록 조회
    public List<InquireLoanAccountResponseDto> inquireLoanAccountList(String userKey){
        final String name = "inquireLoanAccountList";
        log.info("금융 API  대출 상품 가입 목록 조회");

        String uri = "edu/loan/inquireLoanAccountList";

        Map<String,Object>requestBody = new HashMap<>();

        RequestHeader headers = requestHeader(name,userKey);

        requestBody.put("Header",headers);

        HttpEntity<Object> entity = new HttpEntity<>(requestBody);

        ResponseEntity<RECListResponse<InquireLoanAccountResponseDto>> response =
                restTemplate.exchange(
                        url + uri,HttpMethod.POST ,entity,
                        new ParameterizedTypeReference<>(){}
                );

        if(response.getBody() == null){
            throw new BadRequestException("API 요청 중 오류가 발생했습니다.");
        }

        return response.getBody().getREC();
    }

    //대출 상품

    // TODO 대출 상환 내역 조회

    // TODO 대출 일시납 상환

    //1원 인증 API
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

}