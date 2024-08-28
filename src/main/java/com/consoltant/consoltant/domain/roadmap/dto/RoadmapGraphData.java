package com.consoltant.consoltant.domain.roadmap.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoadmapGraphData {
    private Integer age;
    private Long totalAssetValue;
    private Long depositAssetValue;
    private Long savingAssetValue;
    private Long loanAssetValue;
}
