package com.consoltant.consoltant.domain.bank.mapper;

import com.consoltant.consoltant.domain.bank.dto.demanddeposit.CreateDemandDepositResponseBankDto;
import com.consoltant.consoltant.domain.bank.dto.deposit.CreateDepositResponseBankDto;
import com.consoltant.consoltant.domain.bank.dto.loan.CreateLoanResponseBankDto;
import com.consoltant.consoltant.domain.bank.dto.saving.CreateSavingResponseBankDto;
import com.consoltant.consoltant.util.api.dto.demanddeposit.createdemanddeposit.CreateDemandDepositResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.createdeposit.CreateDepositResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.createloanproduct.CreateLoanProductResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.createsaving.CreateSavingResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface BankMapper {
    CreateDemandDepositResponseBankDto toCreateDemandDepositResponseBankDto(CreateDemandDepositResponseDto createDemandDepositResponseDto);
    CreateDepositResponseBankDto toCreateDepositResponseBankDto(CreateDepositResponseDto createDepositResponseDto);
    CreateLoanResponseBankDto toCreateLoanResponseBankDto(CreateLoanProductResponseDto createLoanProductResponseDto);
    CreateSavingResponseBankDto toCreateSavingResponseBankDto(CreateSavingResponseDto createSavingResponseDto);
}
