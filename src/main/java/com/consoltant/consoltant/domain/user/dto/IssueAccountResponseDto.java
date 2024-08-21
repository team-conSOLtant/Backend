package com.consoltant.consoltant.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class IssueAccountRequestDto {
    private String accountNo;
}
