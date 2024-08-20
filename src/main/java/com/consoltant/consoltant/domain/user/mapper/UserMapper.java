package com.consoltant.consoltant.domain.user.mapper;

import com.consoltant.consoltant.domain.user.dto.*;
import com.consoltant.consoltant.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User toUser(CreateUserRequestDto createUserRequestDto);
    User toUser(CreateUserAccountRequestDto createUserAccountRequestDto);
    User toUser(CreateUserAcademyRequestDto createUserAcademyRequestDto);
    UserResponseDto toUserResponseDto(User user);
}
