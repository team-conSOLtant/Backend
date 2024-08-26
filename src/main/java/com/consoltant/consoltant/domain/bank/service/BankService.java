package com.consoltant.consoltant.domain.bank.service;

import com.consoltant.consoltant.domain.bank.dto.demanddeposit.CreateDemandDepositRequestBankDto;
import com.consoltant.consoltant.domain.bank.dto.demanddeposit.CreateDemandDepositResponseBankDto;
import com.consoltant.consoltant.domain.bank.dto.deposit.CreateDepositRequestBankDto;
import com.consoltant.consoltant.domain.bank.dto.deposit.CreateDepositResponseBankDto;
import com.consoltant.consoltant.domain.bank.dto.loan.CreateLoanRequestBankDto;
import com.consoltant.consoltant.domain.bank.dto.loan.CreateLoanResponseBankDto;
import com.consoltant.consoltant.domain.bank.dto.saving.CreateSavingRequestBankDto;
import com.consoltant.consoltant.domain.bank.dto.saving.CreateSavingResponseBankDto;
import com.consoltant.consoltant.domain.bank.mapper.BankMapper;
import com.consoltant.consoltant.util.api.RestTemplateUtil;
import com.consoltant.consoltant.util.api.dto.saving.createsaving.CreateSavingResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankService {
    private final RestTemplateUtil restTemplateUtil;
    private final BankMapper bankMapper;

    public CreateDemandDepositResponseBankDto createDemandDeposit(CreateDemandDepositRequestBankDto requestDto){
        return bankMapper.toCreateDemandDepositResponseBankDto(restTemplateUtil.createDemandDeposit(requestDto.getBankCode(), requestDto.getAccountName(), requestDto.getAccountDescription()));
    }

    public CreateDepositResponseBankDto createDeposit(CreateDepositRequestBankDto requestDto){
        return bankMapper.toCreateDepositResponseBankDto(
                restTemplateUtil.createDeposit(
                    requestDto.getBankCode(),
                    requestDto.getAccountName(),
                    requestDto.getAccountDescription(),
                    requestDto.getSubscriptionPeriod(),
                    requestDto.getMinSubscriptionBalance(),
                    requestDto.getMaxSubscriptionBalance(),
                    requestDto.getInterestRate(),
                    requestDto.getRateDescription()
            )
        );
    }

    public CreateLoanResponseBankDto createLoan(CreateLoanRequestBankDto requestDto){
        return bankMapper.toCreateLoanResponseBankDto(
                restTemplateUtil.createLoanProduct(
                        requestDto.getBankCode(),
                        requestDto.getAccountName(),
                        requestDto.getAccountDescription(),
                        requestDto.getRatingUniqueNo(),
                        requestDto.getLoanPeriod(),
                        requestDto.getMinLoanBalance(),
                        requestDto.getMaxLoanBalance(),
                        requestDto.getInterestRate()
                )
        );
    }

    public CreateSavingResponseBankDto createSaving(CreateSavingRequestBankDto requestDto){
        return bankMapper.toCreateSavingResponseBankDto(
                restTemplateUtil.createSaving(
                        requestDto.getBankCode(),
                        requestDto.getAccountName(),
                        requestDto.getAccountDescription(),
                        requestDto.getSubscriptionPeriod(),
                        requestDto.getMinSubscriptionBalance(),
                        requestDto.getMaxSubscriptionBalance(),
                        requestDto.getInterestRate(),
                        requestDto.getRateDescription()
                )
        );
    }
}
