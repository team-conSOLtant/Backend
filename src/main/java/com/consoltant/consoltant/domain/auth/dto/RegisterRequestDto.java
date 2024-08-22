package com.consoltant.consoltant.domain.auth.dto;

import com.consoltant.consoltant.domain.university.entity.University;

import com.consoltant.consoltant.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String birthDate;

    public User createUser(String password, String userKey, University university) {
        return User.builder()
                .email(email)
                .password(password)
                .name(name)
                .university(university)
                .phoneNumber(phoneNumber)
                .birthDate(birthDate)
                .userKey(userKey)
                .build();
    }

}
