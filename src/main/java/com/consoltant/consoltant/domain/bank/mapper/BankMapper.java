package com.consoltant.consoltant.domain.bank.mapper;

import com.consoltant.consoltant.domain.bank.dto.demanddeposit.CreateDemandDepositResponseBankDto;
import com.consoltant.consoltant.domain.bank.dto.deposit.CreateDepositResponseBankDto;
import com.consoltant.consoltant.domain.bank.dto.loan.CreateLoanResponseBankDto;
import com.consoltant.consoltant.domain.bank.dto.saving.CreateSavingResponseBankDto;
import com.consoltant.consoltant.util.api.dto.demanddeposit.createdemanddeposit.CreateDemandDepositResponseDto;
import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiredemanddeposit.InquireDemandDepositResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.createdeposit.CreateDepositResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.inquiredepositinfo.InquireDepositInfoResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.inquiredepositproducts.InquireDepositProductsResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.createloanproduct.CreateLoanProductResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquireloanproduct.InquireLoanProductResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.createsaving.CreateSavingResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.inquiresavinginfo.InquireSavingInfoResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.inquiresavingproducts.InquireSavingProductsResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface BankMapper {
    CreateDemandDepositResponseBankDto toCreateDemandDepositResponseBankDto(CreateDemandDepositResponseDto createDemandDepositResponseDto);
    CreateDepositResponseBankDto toCreateDepositResponseBankDto(CreateDepositResponseDto createDepositResponseDto);
    CreateLoanResponseBankDto toCreateLoanResponseBankDto(CreateLoanProductResponseDto createLoanProductResponseDto);
    CreateSavingResponseBankDto toCreateSavingResponseBankDto(CreateSavingResponseDto createSavingResponseDto);
    List<CreateDemandDepositResponseBankDto> toCreateDemandDepositResponseBankListDto(List<InquireDemandDepositResponseDto> inquireDemandDepositResponseDtoList);
    List<CreateDepositResponseBankDto> toCreateDepositResponseBankListDto(List<InquireDepositProductsResponseDto> inquireDemandDepositResponseDtoList);
    List<CreateSavingResponseBankDto> toCreateSavingResponseBankListDto(List<InquireSavingProductsResponseDto> inquireSavingProductsResponseDtoList);
    List<CreateLoanResponseBankDto> toCreateLoanResponseBankListDto(List<InquireLoanProductResponseDto> inquireLoanProductResponseDtoList);
}
