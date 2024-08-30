package com.consoltant.consoltant.domain.roadmap.dto;

import com.consoltant.consoltant.domain.product.dto.ProductListResponseDto;
import com.consoltant.consoltant.domain.recommend.dto.RecommendResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExpectRoadmapGraphResponseDto {
    private Integer age;
    private List<RoadmapGraphData> data;
    private ProductListResponseDto product;
    private UserInfo info;
    private List<RecommendResponseDto> recommend;
    private List<PreRecommendDto> preRecommend;

    public ExpectRoadmapGraphResponseDto() {

        data = new ArrayList<>();
        preRecommend = new ArrayList<>();
    }
}
