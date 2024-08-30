package com.consoltant.consoltant.domain.roadmap.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ExpectProductList {
    private List<PreRecommendDto> deposit;
    private List<PreRecommendDto> saving;
    private List<PreRecommendDto> loan;

    public ExpectProductList(){
        deposit = new ArrayList<>();
        saving = new ArrayList<>();
        loan = new ArrayList<>();
    }
}
