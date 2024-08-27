package com.consoltant.consoltant.domain.auth.mapper;

import com.consoltant.consoltant.domain.auth.dto.RegisterResponseDto;
import com.consoltant.consoltant.domain.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class AuthMapperImpl implements AuthMapper {

    @Override
    public RegisterResponseDto toRegisterResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        RegisterResponseDto.RegisterResponseDtoBuilder registerResponseDto = RegisterResponseDto.builder();

        registerResponseDto.email( user.getEmail() );
        registerResponseDto.age( user.getAge() );
        registerResponseDto.name( user.getName() );
        registerResponseDto.phoneNumber( user.getPhoneNumber() );
        registerResponseDto.birthDate( user.getBirthDate() );

        return registerResponseDto.build();
    }
}
