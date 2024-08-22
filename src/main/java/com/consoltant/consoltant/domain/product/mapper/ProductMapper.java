package com.consoltant.consoltant.domain.product.mapper;

import com.consoltant.consoltant.domain.product.dto.ProductRequestDto;
import com.consoltant.consoltant.domain.product.dto.ProductResponseDto;
import com.consoltant.consoltant.domain.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    Product toProduct(ProductRequestDto productRequestDto);

    @Mapping(source = "user.id", target = "userId")
    ProductResponseDto toProductResponseDto(Product product);

}
