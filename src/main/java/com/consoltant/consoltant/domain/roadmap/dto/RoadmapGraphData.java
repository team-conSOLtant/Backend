package com.consoltant.consoltant.domain.roadmap.dto;

import com.consoltant.consoltant.util.constant.JourneyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoadmapGraphData {
    private Integer age;
    private JourneyType journeyType;
    private Long totalAssetValue;
    private Long depositAssetValue;
    private Long savingAssetValue;
    private Long loanAssetValue;
}
