package com.consoltant.consoltant.domain.roadmap.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExpectRoadmapRequestDto {
    private List<PreRecommendDto>productList;
}
