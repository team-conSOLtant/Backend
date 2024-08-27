package com.consoltant.consoltant.domain.bank.mapper;

import com.consoltant.consoltant.domain.bank.dto.demanddeposit.CreateDemandDepositResponseBankDto;
import com.consoltant.consoltant.domain.bank.dto.deposit.CreateDepositResponseBankDto;
import com.consoltant.consoltant.domain.bank.dto.loan.CreateLoanResponseBankDto;
import com.consoltant.consoltant.domain.bank.dto.saving.CreateSavingResponseBankDto;
import com.consoltant.consoltant.util.api.dto.demanddeposit.createdemanddeposit.CreateDemandDepositResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.createdeposit.CreateDepositResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.createloanproduct.CreateLoanProductResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.createsaving.CreateSavingResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class BankMapperImpl implements BankMapper {

    @Override
    public CreateDemandDepositResponseBankDto toCreateDemandDepositResponseBankDto(CreateDemandDepositResponseDto createDemandDepositResponseDto) {
        if ( createDemandDepositResponseDto == null ) {
            return null;
        }

        CreateDemandDepositResponseBankDto createDemandDepositResponseBankDto = new CreateDemandDepositResponseBankDto();

        createDemandDepositResponseBankDto.setAccountTypeUniqueNo( createDemandDepositResponseDto.getAccountTypeUniqueNo() );
        createDemandDepositResponseBankDto.setBankCode( createDemandDepositResponseDto.getBankCode() );
        createDemandDepositResponseBankDto.setBankName( createDemandDepositResponseDto.getBankName() );
        createDemandDepositResponseBankDto.setAccountTypeCode( createDemandDepositResponseDto.getAccountTypeCode() );
        createDemandDepositResponseBankDto.setAccountTypeName( createDemandDepositResponseDto.getAccountTypeName() );
        createDemandDepositResponseBankDto.setAccountName( createDemandDepositResponseDto.getAccountName() );
        createDemandDepositResponseBankDto.setAccountDescription( createDemandDepositResponseDto.getAccountDescription() );
        createDemandDepositResponseBankDto.setAccountType( createDemandDepositResponseDto.getAccountType() );

        return createDemandDepositResponseBankDto;
    }

    @Override
    public CreateDepositResponseBankDto toCreateDepositResponseBankDto(CreateDepositResponseDto createDepositResponseDto) {
        if ( createDepositResponseDto == null ) {
            return null;
        }

        CreateDepositResponseBankDto createDepositResponseBankDto = new CreateDepositResponseBankDto();

        createDepositResponseBankDto.setAccountTypeUniqueNo( createDepositResponseDto.getAccountTypeUniqueNo() );
        createDepositResponseBankDto.setBankCode( createDepositResponseDto.getBankCode() );
        createDepositResponseBankDto.setBankName( createDepositResponseDto.getBankName() );
        createDepositResponseBankDto.setAccountTypeCode( createDepositResponseDto.getAccountTypeCode() );
        createDepositResponseBankDto.setAccountTypeName( createDepositResponseDto.getAccountTypeName() );
        createDepositResponseBankDto.setAccountName( createDepositResponseDto.getAccountName() );
        createDepositResponseBankDto.setAccountDescription( createDepositResponseDto.getAccountDescription() );
        createDepositResponseBankDto.setSubscriptionPeriod( createDepositResponseDto.getSubscriptionPeriod() );
        createDepositResponseBankDto.setMinSubscriptionBalance( createDepositResponseDto.getMinSubscriptionBalance() );
        createDepositResponseBankDto.setMaxSubscriptionBalance( createDepositResponseDto.getMaxSubscriptionBalance() );
        createDepositResponseBankDto.setInterestRate( createDepositResponseDto.getInterestRate() );
        createDepositResponseBankDto.setRateDescription( createDepositResponseDto.getRateDescription() );

        return createDepositResponseBankDto;
    }

    @Override
    public CreateLoanResponseBankDto toCreateLoanResponseBankDto(CreateLoanProductResponseDto createLoanProductResponseDto) {
        if ( createLoanProductResponseDto == null ) {
            return null;
        }

        CreateLoanResponseBankDto createLoanResponseBankDto = new CreateLoanResponseBankDto();

        createLoanResponseBankDto.setAccountTypeUniqueNo( createLoanProductResponseDto.getAccountTypeUniqueNo() );
        createLoanResponseBankDto.setBankCode( createLoanProductResponseDto.getBankCode() );
        createLoanResponseBankDto.setBankName( createLoanProductResponseDto.getBankName() );
        createLoanResponseBankDto.setRatingUniqueNo( createLoanProductResponseDto.getRatingUniqueNo() );
        createLoanResponseBankDto.setAccountName( createLoanProductResponseDto.getAccountName() );
        createLoanResponseBankDto.setLoanPeriod( createLoanProductResponseDto.getLoanPeriod() );
        createLoanResponseBankDto.setMinLoanBalance( createLoanProductResponseDto.getMinLoanBalance() );
        createLoanResponseBankDto.setMaxLoanBalance( createLoanProductResponseDto.getMaxLoanBalance() );
        createLoanResponseBankDto.setInterestRate( createLoanProductResponseDto.getInterestRate() );
        createLoanResponseBankDto.setAccountDescription( createLoanProductResponseDto.getAccountDescription() );
        createLoanResponseBankDto.setAccountTypeCode( createLoanProductResponseDto.getAccountTypeCode() );
        createLoanResponseBankDto.setAccountTypeName( createLoanProductResponseDto.getAccountTypeName() );
        createLoanResponseBankDto.setLoanTypeCode( createLoanProductResponseDto.getLoanTypeCode() );
        createLoanResponseBankDto.setLoanTypeName( createLoanProductResponseDto.getLoanTypeName() );
        createLoanResponseBankDto.setRepaymentMethodTypeCode( createLoanProductResponseDto.getRepaymentMethodTypeCode() );
        createLoanResponseBankDto.setRepaymentMethodTypeName( createLoanProductResponseDto.getRepaymentMethodTypeName() );

        return createLoanResponseBankDto;
    }

    @Override
    public CreateSavingResponseBankDto toCreateSavingResponseBankDto(CreateSavingResponseDto createSavingResponseDto) {
        if ( createSavingResponseDto == null ) {
            return null;
        }

        CreateSavingResponseBankDto createSavingResponseBankDto = new CreateSavingResponseBankDto();

        createSavingResponseBankDto.setAccountTypeUniqueNo( createSavingResponseDto.getAccountTypeUniqueNo() );
        createSavingResponseBankDto.setBankCode( createSavingResponseDto.getBankCode() );
        createSavingResponseBankDto.setBankName( createSavingResponseDto.getBankName() );
        createSavingResponseBankDto.setAccountTypeCode( createSavingResponseDto.getAccountTypeCode() );
        createSavingResponseBankDto.setAccountTypeName( createSavingResponseDto.getAccountTypeName() );
        createSavingResponseBankDto.setAccountName( createSavingResponseDto.getAccountName() );
        createSavingResponseBankDto.setAccountDescription( createSavingResponseDto.getAccountDescription() );
        createSavingResponseBankDto.setSubscriptionPeriod( createSavingResponseDto.getSubscriptionPeriod() );
        createSavingResponseBankDto.setMinSubscriptionBalance( createSavingResponseDto.getMinSubscriptionBalance() );
        createSavingResponseBankDto.setMaxSubscriptionBalance( createSavingResponseDto.getMaxSubscriptionBalance() );
        createSavingResponseBankDto.setInterestRate( createSavingResponseDto.getInterestRate() );
        createSavingResponseBankDto.setRateDescription( createSavingResponseDto.getRateDescription() );

        return createSavingResponseBankDto;
    }
}
