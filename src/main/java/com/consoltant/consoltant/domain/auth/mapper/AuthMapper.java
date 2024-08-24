package com.consoltant.consoltant.domain.auth.mapper;

import com.consoltant.consoltant.domain.auth.dto.RegisterResponseDto;
import com.consoltant.consoltant.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthMapper {
    RegisterResponseDto toRegisterResponseDto(User user);
}
