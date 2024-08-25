package com.consoltant.consoltant.domain.recommend.dto;

import com.consoltant.consoltant.util.constant.JourneyType;
import com.consoltant.consoltant.util.constant.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RecommendRequestDto {
    private String accountTypeUniqueNo;
    private JourneyType journeyType;
    private ProductType productType;
    private LocalDate startDate;
    private LocalDate endDate;
}
