package com.consoltant.consoltant.domain.recommend.dto;

import com.consoltant.consoltant.util.constant.JourneyType;
import com.consoltant.consoltant.util.constant.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendRequestDto {
    private String accountTypeUniqueNo;
    private JourneyType journeyType;
    private ProductType productType;
}
