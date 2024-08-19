package com.consoltant.consoltant.domain.user.mapper;

import com.consoltant.consoltant.domain.user.dto.CreateUserRequestDto;
import com.consoltant.consoltant.domain.user.dto.CreateUserResponseDto;
import com.consoltant.consoltant.domain.user.dto.UpdateUserRequestDto;
import com.consoltant.consoltant.domain.user.dto.UpdateUserResponseDto;
import com.consoltant.consoltant.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User toUser(CreateUserRequestDto createUserRequestDto);
    User toUser(UpdateUserRequestDto updateUserRequestDto);
    CreateUserResponseDto toCreateUserResponseDto(User user);
    UpdateUserResponseDto toUpdateUserResponseDto(User user);

}
