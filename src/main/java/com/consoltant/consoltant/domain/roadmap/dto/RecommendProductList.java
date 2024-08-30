package com.consoltant.consoltant.domain.roadmap.dto;

import com.consoltant.consoltant.domain.recommend.dto.RecommendResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RecommendProductList {
    private List<RecommendResponseDto> deposit;
    private List<RecommendResponseDto> saving;
    private List<RecommendResponseDto> loan;

    public RecommendProductList(){
        deposit = new ArrayList<>();
        saving = new ArrayList<>();
        loan = new ArrayList<>();
    }
}
