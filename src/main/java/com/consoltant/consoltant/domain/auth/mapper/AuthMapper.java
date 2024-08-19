package com.consoltant.consoltant.domain.auth.mapper;

import com.consoltant.consoltant.domain.auth.dto.LoginRequestDto;
import com.consoltant.consoltant.domain.user.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    User toUser(LoginRequestDto loginRequestDto);
}
