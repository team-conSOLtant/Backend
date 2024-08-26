package com.consoltant.consoltant.domain.journey.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class JourneyGraphResponseDto {

    private Integer age;
    private List<GraphData> data;

    public JourneyGraphResponseDto (){
        this.data = new ArrayList<>();
    }
}
