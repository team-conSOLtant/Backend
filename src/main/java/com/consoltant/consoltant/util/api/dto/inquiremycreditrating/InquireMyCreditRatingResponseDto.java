package com.consoltant.consoltant.util.api.dto.inquiremycreditrating;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquireMyCreditRatingResponseDto {
    private String ratingName;
    private Long demandDepositAssetValue;
    private Long depositSavingsAssetValue;
    private Long totalAssetValue;
}
