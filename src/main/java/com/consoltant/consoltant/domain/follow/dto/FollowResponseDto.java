package com.consoltant.consoltant.domain.follow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowResponseDto {
    private Long id;
    private Long userId;
    private Long portfolioId;
    private Boolean isDeleted;
}
