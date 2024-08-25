package com.consoltant.consoltant.domain.recommend.dto;

import com.consoltant.consoltant.util.constant.JourneyType;
import com.consoltant.consoltant.util.constant.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RecommendResponseDto {

    private Long id;

    private ProductType productType;

    private String accountUniqueTypeNo;

    private JourneyType journeyType;

    private LocalDate startDate;

    private LocalDate endDate;

}
