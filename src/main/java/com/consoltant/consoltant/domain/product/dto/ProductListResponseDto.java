package com.consoltant.consoltant.domain.product.dto;

import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiredemanddeposit.InquireDemandDepositResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.inquiredepositproducts.InquireDepositProductsResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquireloanproduct.InquireLoanProductResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.inquiresavingproducts.InquireSavingProductsResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductListResponseDto {
    List<InquireDemandDepositResponseDto> demandDeposit;
    List<InquireDepositProductsResponseDto> deposit;
    List<InquireSavingProductsResponseDto> saving;
    List<InquireLoanProductResponseDto> loan;

    public ProductListResponseDto(){
        demandDeposit = new ArrayList<>();
        deposit = new ArrayList<>();
        saving = new ArrayList<>();
        loan = new ArrayList<>();
    }
}
