package com.consoltant.consoltant.domain.auth.dto;

import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.entity.PortfolioDocument;
import com.consoltant.consoltant.domain.university.entity.University;

import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.util.constant.JourneyType;
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
    private Integer age;
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String birthDate;
    @NotNull
    private JourneyType journeyType;

    public User createUser(String password, String userKey, University university) {
        return User.builder()
                .email(email)
                .password(password)
                .role("USER")
                .name(name)
                .age(age)
                .university(university)
                .phoneNumber(phoneNumber)
                .birthDate(birthDate)
                .currentJourneyType(journeyType)
                .userKey(userKey)
                .build();
    }
}
