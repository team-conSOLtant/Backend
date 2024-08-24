package com.consoltant.consoltant.domain.user.mapper;

import com.consoltant.consoltant.domain.university.dto.UniversityResponseDto;
import com.consoltant.consoltant.domain.university.entity.University;
import com.consoltant.consoltant.domain.user.dto.*;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.util.api.dto.checkauthcode.CheckAuthCodeResponseDto;
import com.consoltant.consoltant.util.api.dto.createdemanddepositaccount.CreateDemandDepositAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.inquiretransactionhistory.InquireTransactionHistoryResponseDto;
import com.consoltant.consoltant.util.api.dto.openaccountauth.OpenAccountAuthResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserResponseDto toUserResponseDto(User user);
    CreateAccountResponseDto toCreateAccountResponseDto(CreateDemandDepositAccountResponseDto createDemandDepositAccountResponseDto);
    IssueAccountResponseDto toIssueResponseDto(OpenAccountAuthResponseDto openAccountAuthResponseDto);
    CheckAccountResponseDto toCheckAccountResponseDto(CheckAuthCodeResponseDto checkAuthCodeResponseDto);
    CheckTransactionMessageResponseDto toCheckTransactionResponseDto(InquireTransactionHistoryResponseDto inquireTransactionHistoryResponseDto);
    UniversityResponseDto toUniversityResponseDto(University university);

}
