package com.consoltant.consoltant.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

@Builder
@Getter
public class LoginRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
