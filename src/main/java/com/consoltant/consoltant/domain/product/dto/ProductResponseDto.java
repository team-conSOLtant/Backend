package com.consoltant.consoltant.domain.product.dto;

import com.consoltant.consoltant.util.constant.ProductType;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {

    private Long id;
    private Long userId;
    private String accountNo;
    private ProductType productType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isDeleted;

}
