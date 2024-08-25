package com.consoltant.consoltant.domain.product.mapper;

import com.consoltant.consoltant.domain.product.dto.*;
import com.consoltant.consoltant.domain.product.entity.Product;
import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiredemanddepositaccount.InquireDemandDepositAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.inquiredepositinfo.InquireDepositInfoResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquireloanaccount.InquireLoanAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.inquiresavinginfo.InquireSavingInfoResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    Product toProduct(ProductRequestDto productRequestDto);
    Product toProduct(ProductSaveRequestDto productSaveRequestDto);

    @Mapping(source = "user.id", target = "userId")
    ProductResponseDto toProductResponseDto(Product product);

    ProductInfo toProductInfo(InquireDemandDepositAccountResponseDto inquireDemandDepositAccountResponseDto);
    ProductInfo toProductInfo(InquireSavingInfoResponseDto inquireSavingInfoResponseDto);
    ProductInfo toProductInfo(InquireDepositInfoResponseDto inquireDepositInfoResponseDto);
    ProductInfo toProductInfo(InquireLoanAccountResponseDto inquireLoanAccountResponseDto);
}
