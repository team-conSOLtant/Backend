package com.consoltant.consoltant.domain.user.mapper;

import com.consoltant.consoltant.domain.university.dto.UniversityResponseDto;
import com.consoltant.consoltant.domain.university.entity.University;
import com.consoltant.consoltant.domain.user.dto.CheckAccountResponseDto;
import com.consoltant.consoltant.domain.user.dto.CheckTransactionMessageResponseDto;
import com.consoltant.consoltant.domain.user.dto.CreateAccountResponseDto;
import com.consoltant.consoltant.domain.user.dto.IssueAccountResponseDto;
import com.consoltant.consoltant.domain.user.dto.UserResponseDto;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.util.api.dto.auth.checkauthcode.CheckAuthCodeResponseDto;
import com.consoltant.consoltant.util.api.dto.auth.openaccountauth.OpenAccountAuthResponseDto;
import com.consoltant.consoltant.util.api.dto.demanddeposit.createdemanddepositaccount.CreateDemandDepositAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiretransactionhistory.InquireTransactionHistoryResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDto toUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto.UserResponseDtoBuilder userResponseDto = UserResponseDto.builder();

        userResponseDto.id( user.getId() );
        userResponseDto.university( toUniversityResponseDto( user.getUniversity() ) );
        userResponseDto.email( user.getEmail() );
        userResponseDto.name( user.getName() );
        userResponseDto.age( user.getAge() );
        userResponseDto.phoneNumber( user.getPhoneNumber() );
        userResponseDto.birthDate( user.getBirthDate() );
        userResponseDto.major( user.getMajor() );
        userResponseDto.totalGpa( user.getTotalGpa() );
        userResponseDto.majorGpa( user.getMajorGpa() );
        userResponseDto.maxGpa( user.getMaxGpa() );
        userResponseDto.credit( user.getCredit() );
        userResponseDto.maxCredit( user.getMaxCredit() );
        userResponseDto.degree( user.getDegree() );
        userResponseDto.startDate( user.getStartDate() );
        userResponseDto.endDate( user.getEndDate() );
        userResponseDto.isEmployed( user.getIsEmployed() );
        userResponseDto.corporateName( user.getCorporateName() );
        userResponseDto.accountNo( user.getAccountNo() );
        userResponseDto.salary( user.getSalary() );
        userResponseDto.currentJourneyType( user.getCurrentJourneyType() );

        return userResponseDto.build();
    }

    @Override
    public CreateAccountResponseDto toCreateAccountResponseDto(CreateDemandDepositAccountResponseDto createDemandDepositAccountResponseDto) {
        if ( createDemandDepositAccountResponseDto == null ) {
            return null;
        }

        CreateAccountResponseDto.CreateAccountResponseDtoBuilder createAccountResponseDto = CreateAccountResponseDto.builder();

        createAccountResponseDto.bankCode( createDemandDepositAccountResponseDto.getBankCode() );
        createAccountResponseDto.accountNo( createDemandDepositAccountResponseDto.getAccountNo() );
        createAccountResponseDto.currency( createDemandDepositAccountResponseDto.getCurrency() );

        return createAccountResponseDto.build();
    }

    @Override
    public IssueAccountResponseDto toIssueResponseDto(OpenAccountAuthResponseDto openAccountAuthResponseDto) {
        if ( openAccountAuthResponseDto == null ) {
            return null;
        }

        IssueAccountResponseDto.IssueAccountResponseDtoBuilder issueAccountResponseDto = IssueAccountResponseDto.builder();

        issueAccountResponseDto.transactionUniqueNo( openAccountAuthResponseDto.getTransactionUniqueNo() );
        issueAccountResponseDto.accountNo( openAccountAuthResponseDto.getAccountNo() );

        return issueAccountResponseDto.build();
    }

    @Override
    public CheckAccountResponseDto toCheckAccountResponseDto(CheckAuthCodeResponseDto checkAuthCodeResponseDto) {
        if ( checkAuthCodeResponseDto == null ) {
            return null;
        }

        CheckAccountResponseDto.CheckAccountResponseDtoBuilder checkAccountResponseDto = CheckAccountResponseDto.builder();

        checkAccountResponseDto.status( checkAuthCodeResponseDto.getStatus() );
        checkAccountResponseDto.transactionUniqueNo( checkAuthCodeResponseDto.getTransactionUniqueNo() );
        checkAccountResponseDto.accountNo( checkAuthCodeResponseDto.getAccountNo() );

        return checkAccountResponseDto.build();
    }

    @Override
    public CheckTransactionMessageResponseDto toCheckTransactionResponseDto(InquireTransactionHistoryResponseDto inquireTransactionHistoryResponseDto) {
        if ( inquireTransactionHistoryResponseDto == null ) {
            return null;
        }

        CheckTransactionMessageResponseDto.CheckTransactionMessageResponseDtoBuilder checkTransactionMessageResponseDto = CheckTransactionMessageResponseDto.builder();

        checkTransactionMessageResponseDto.transactionUniqueNo( inquireTransactionHistoryResponseDto.getTransactionUniqueNo() );
        checkTransactionMessageResponseDto.transactionDate( inquireTransactionHistoryResponseDto.getTransactionDate() );
        checkTransactionMessageResponseDto.transactionTime( inquireTransactionHistoryResponseDto.getTransactionTime() );
        checkTransactionMessageResponseDto.transactionType( inquireTransactionHistoryResponseDto.getTransactionType() );
        checkTransactionMessageResponseDto.transactionTypeName( inquireTransactionHistoryResponseDto.getTransactionTypeName() );
        checkTransactionMessageResponseDto.transactionBalance( inquireTransactionHistoryResponseDto.getTransactionBalance() );
        checkTransactionMessageResponseDto.transactionAfterBalance( inquireTransactionHistoryResponseDto.getTransactionAfterBalance() );
        checkTransactionMessageResponseDto.transactionSummary( inquireTransactionHistoryResponseDto.getTransactionSummary() );
        checkTransactionMessageResponseDto.transactionMemo( inquireTransactionHistoryResponseDto.getTransactionMemo() );

        return checkTransactionMessageResponseDto.build();
    }

    @Override
    public UniversityResponseDto toUniversityResponseDto(University university) {
        if ( university == null ) {
            return null;
        }

        UniversityResponseDto universityResponseDto = new UniversityResponseDto();

        universityResponseDto.setId( university.getId() );
        universityResponseDto.setName( university.getName() );
        universityResponseDto.setUniversityCode( university.getUniversityCode() );
        universityResponseDto.setIsDeleted( university.getIsDeleted() );

        return universityResponseDto;
    }
}
