package com.consoltant.consoltant.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
