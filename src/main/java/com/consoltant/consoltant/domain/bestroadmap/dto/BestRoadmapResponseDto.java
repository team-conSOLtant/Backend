package com.consoltant.consoltant.domain.bestroadmap.dto;

import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.util.constant.FinanceKeyword;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BestRoadmapResponseDto {
    private Long id;

    private User user;

    private Integer salary;

    private Long startAsset;

    private FinanceKeyword financeKeyword;

    private Integer age;
}
