package com.consoltant.consoltant.domain.journey.dto;

import com.consoltant.consoltant.util.constant.JourneyType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GraphData {
    Integer age;
    JourneyType journeyType;
    String journeyTypeName;
    Long totalAssetValue;

    public GraphData(Integer age, Long totalAssetValue, JourneyType journeyType, String journeyTypeName ){
        this.age = age;
        this.totalAssetValue = totalAssetValue;
        this.journeyType = journeyType;
        this.journeyTypeName = journeyTypeName;
    }
}
