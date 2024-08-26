package com.consoltant.consoltant.domain.journey.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GraphData {
    Integer age;
    Long totalAssetValue;

    public GraphData(Integer age, Long totalAssetValue ){
        this.age = age;
        this.totalAssetValue = totalAssetValue;
    }
}
