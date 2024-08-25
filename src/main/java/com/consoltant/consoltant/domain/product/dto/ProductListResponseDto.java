package com.consoltant.consoltant.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductListResponseDto {
    List<ProductInfo> demandDeposit;
    List<ProductInfo> deposit;
    List<ProductInfo> saving;
    List<ProductInfo> loan;

    public ProductListResponseDto(){
        demandDeposit = new ArrayList<>();
        deposit = new ArrayList<>();
        saving = new ArrayList<>();
        loan = new ArrayList<>();
    }
}
