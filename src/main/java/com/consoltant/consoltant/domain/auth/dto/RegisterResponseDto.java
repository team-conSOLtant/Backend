package com.consoltant.consoltant.domain.auth.dto;

import com.consoltant.consoltant.domain.university.entity.University;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.util.constant.JourneyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Builder
@Getter
public class RegisterResponseDto {
    private String email;
    private Integer age;
    private String name;
    private String phoneNumber;
    private String birthDate;
    private JourneyType journeyType;
}
