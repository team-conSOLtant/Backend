package com.consoltant.consoltant.domain.follow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowRequestDto {
    private Long userId;
    private Long portfolioId;
}
