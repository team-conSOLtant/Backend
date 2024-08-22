package com.consoltant.consoltant.domain.user.mapper;

import com.consoltant.consoltant.domain.user.dto.CreateUserAcademyRequestDto;
import com.consoltant.consoltant.domain.user.dto.CreateUserAccountRequestDto;
import com.consoltant.consoltant.domain.user.dto.CreateUserRequestDto;
import com.consoltant.consoltant.domain.user.dto.UserResponseDto;
import com.consoltant.consoltant.domain.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-21T17:13:12+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(CreateUserRequestDto createUserRequestDto) {
        if ( createUserRequestDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( createUserRequestDto.getEmail() );
        user.password( createUserRequestDto.getPassword() );
        user.name( createUserRequestDto.getName() );
        user.age( createUserRequestDto.getAge() );
        user.phoneNumber( createUserRequestDto.getPhoneNumber() );
        user.birthDate( createUserRequestDto.getBirthDate() );

        return user.build();
    }

    @Override
    public User toUser(CreateUserAccountRequestDto createUserAccountRequestDto) {
        if ( createUserAccountRequestDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( createUserAccountRequestDto.getId() );
        user.isEmployed( createUserAccountRequestDto.getIsEmployed() );
        user.accountNo( createUserAccountRequestDto.getAccountNo() );
        user.salary( createUserAccountRequestDto.getSalary() );
        user.currentJourneyType( createUserAccountRequestDto.getCurrentJourneyType() );

        return user.build();
    }

    @Override
    public User toUser(CreateUserAcademyRequestDto createUserAcademyRequestDto) {
        if ( createUserAcademyRequestDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( createUserAcademyRequestDto.getId() );
        user.university( createUserAcademyRequestDto.getUniversity() );
        user.major( createUserAcademyRequestDto.getMajor() );
        user.totalGpa( createUserAcademyRequestDto.getTotalGpa() );
        user.majorGpa( createUserAcademyRequestDto.getMajorGpa() );
        user.totalSumGpa( createUserAcademyRequestDto.getTotalSumGpa() );

        return user.build();
    }

    @Override
    public UserResponseDto toUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto.UserResponseDtoBuilder userResponseDto = UserResponseDto.builder();

        userResponseDto.id( user.getId() );
        userResponseDto.university( user.getUniversity() );
        userResponseDto.email( user.getEmail() );
        userResponseDto.password( user.getPassword() );
        userResponseDto.name( user.getName() );
        userResponseDto.age( user.getAge() );
        userResponseDto.phoneNumber( user.getPhoneNumber() );
        userResponseDto.birthDate( user.getBirthDate() );
        userResponseDto.major( user.getMajor() );
        userResponseDto.totalGpa( user.getTotalGpa() );
        userResponseDto.majorGpa( user.getMajorGpa() );
        userResponseDto.totalSumGpa( user.getTotalSumGpa() );
        userResponseDto.isEmployed( user.getIsEmployed() );
        userResponseDto.accountNo( user.getAccountNo() );
        userResponseDto.salary( user.getSalary() );
        userResponseDto.currentJourneyType( user.getCurrentJourneyType() );
        userResponseDto.isDeleted( user.getIsDeleted() );

        return userResponseDto.build();
    }
}
