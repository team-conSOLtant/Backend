package com.consoltant.consoltant.domain.roadmap.dto;

import com.consoltant.consoltant.domain.product.dto.ProductInfo;
import com.consoltant.consoltant.domain.recommend.dto.RecommendResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExpectRoadmapRequestDto {
    private List<RecommendResponseDto>productList;
}
