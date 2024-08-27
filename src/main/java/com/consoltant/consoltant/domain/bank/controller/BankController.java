package com.consoltant.consoltant.domain.bank.controller;

import com.consoltant.consoltant.domain.bank.dto.demanddeposit.CreateDemandDepositRequestBankDto;
import com.consoltant.consoltant.domain.bank.dto.demanddeposit.CreateDemandDepositResponseBankDto;
import com.consoltant.consoltant.domain.bank.dto.deposit.CreateDepositRequestBankDto;
import com.consoltant.consoltant.domain.bank.dto.deposit.CreateDepositResponseBankDto;
import com.consoltant.consoltant.domain.bank.dto.loan.CreateLoanRequestBankDto;
import com.consoltant.consoltant.domain.bank.dto.loan.CreateLoanResponseBankDto;
import com.consoltant.consoltant.domain.bank.dto.saving.CreateSavingRequestBankDto;
import com.consoltant.consoltant.domain.bank.dto.saving.CreateSavingResponseBankDto;
import com.consoltant.consoltant.domain.bank.service.BankService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banks")
@RequiredArgsConstructor
@Slf4j
public class BankController {

    private final BankService bankService;

    @PostMapping("/create/demanddeposit")
    public BaseSuccessResponse<CreateDemandDepositResponseBankDto> createDemandDeposit(@RequestBody CreateDemandDepositRequestBankDto requestBankDto){
        log.info("수시입출금 상품 등록 API");
        return new BaseSuccessResponse<>(bankService.createDemandDeposit(requestBankDto));
    }

    @GetMapping("/demanddeposit")
    public BaseSuccessResponse<List<CreateDemandDepositResponseBankDto>> viewDemandDeposit(){
        log.info("수시입출금 상품 목록 API");
        return new BaseSuccessResponse<>(bankService.findAllDemandDeposit());
    }

    @PostMapping("/create/deposit")
    public BaseSuccessResponse<CreateDepositResponseBankDto> createDeposit(@RequestBody CreateDepositRequestBankDto requestBankDto){
        log.info("예금 상품 등록 API");
        return new BaseSuccessResponse<>(bankService.createDeposit(requestBankDto));
    }

    @GetMapping("/deposit")
    public BaseSuccessResponse<List<CreateDepositResponseBankDto>> viewDeposit(){
        log.info("예금 상품 목록 API");
        return new BaseSuccessResponse<>(bankService.findAllDeposit());
    }

    @PostMapping("/create/saving")
    public BaseSuccessResponse<CreateSavingResponseBankDto> createSaving(@RequestBody CreateSavingRequestBankDto requestBankDto){
        log.info("적금 상품 등록 API");
        return new BaseSuccessResponse<>(bankService.createSaving(requestBankDto));
    }

    @GetMapping("/saving")
    public BaseSuccessResponse<List<CreateSavingResponseBankDto>> viewSaving(){
        log.info("적금 상품 목록 API");
        return new BaseSuccessResponse<>(bankService.findAllSaving());
    }

    @PostMapping("/create/loan")
    public BaseSuccessResponse<CreateLoanResponseBankDto> createLoan(@RequestBody CreateLoanRequestBankDto requestBankDto){
        log.info("대출 상품 등록 API");
        return new BaseSuccessResponse<>(bankService.createLoan(requestBankDto));
    }

    @GetMapping("/loan")
    public BaseSuccessResponse<List<CreateLoanResponseBankDto>> viewLoan(){
        log.info("대출 상품 목록 API");
        return new BaseSuccessResponse<>(bankService.findAllLoan());
    }
}
