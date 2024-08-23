package com.consoltant.consoltant.domain.matching.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MatchingRequestDto {

    private Long userId;
    private Long portfolioId;
}
