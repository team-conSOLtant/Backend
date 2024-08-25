package com.consoltant.consoltant.domain.product.dto;

import com.consoltant.consoltant.util.constant.ProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankProductInfoRequestDto {
    private ProductType productType;
}
