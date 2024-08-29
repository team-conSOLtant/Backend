package com.consoltant.consoltant.domain.product.dto;

import com.consoltant.consoltant.util.constant.ProductType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductSaveRequestDto {
    private String accountTypeUniqueNo;
    private String accountNo;
    private ProductType productType;
    private LocalDate startDate;
    private LocalDate endDate;
}
