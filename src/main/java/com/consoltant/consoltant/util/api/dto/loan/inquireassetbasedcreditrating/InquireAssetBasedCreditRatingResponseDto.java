package com.consoltant.consoltant.util.api.dto.loan.inquireassetbasedcreditrating;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquireAssetBasedCreditRatingResponseDto {
    private String ratingUniqueNo;
    private String ratingName;
    private Long minAssetValue;
    private Long maxAssetValue;
}
