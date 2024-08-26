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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banks")
@RequiredArgsConstructor
@Slf4j
public class BankController {

    private final BankService bankService;

    @PostMapping("/create/demanddeposit")
    public BaseSuccessResponse<CreateDemandDepositResponseBankDto> createDemandDeposit(@RequestBody CreateDemandDepositRequestBankDto requestBankDto){
        return new BaseSuccessResponse<>(bankService.createDemandDeposit(requestBankDto));
    }

    @PostMapping("/create/deposit")
    public BaseSuccessResponse<CreateDepositResponseBankDto> createDeposit(@RequestBody CreateDepositRequestBankDto requestBankDto){
        return new BaseSuccessResponse<>(bankService.createDeposit(requestBankDto));
    }

    @PostMapping("/create/saving")
    public BaseSuccessResponse<CreateSavingResponseBankDto> createSaving(@RequestBody CreateSavingRequestBankDto requestBankDto){
        return new BaseSuccessResponse<>(bankService.createSaving(requestBankDto));
    }

    @PostMapping("/create/loan")
    public BaseSuccessResponse<CreateLoanResponseBankDto> createDemandDeposit(@RequestBody CreateLoanRequestBankDto requestBankDto){
        return new BaseSuccessResponse<>(bankService.createLoan(requestBankDto));
    }
}
