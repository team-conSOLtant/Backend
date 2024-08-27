package com.consoltant.consoltant.domain.product.mapper;

import com.consoltant.consoltant.domain.product.dto.ProductInfo;
import com.consoltant.consoltant.domain.product.dto.ProductRequestDto;
import com.consoltant.consoltant.domain.product.dto.ProductResponseDto;
import com.consoltant.consoltant.domain.product.dto.ProductSaveRequestDto;
import com.consoltant.consoltant.domain.product.entity.Product;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiredemanddepositaccount.InquireDemandDepositAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.inquiredepositinfo.InquireDepositInfoResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquireloanaccount.InquireLoanAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.inquiresavinginfo.InquireSavingInfoResponseDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductRequestDto productRequestDto) {
        if ( productRequestDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.productType( productRequestDto.getProductType() );
        product.accountNo( productRequestDto.getAccountNo() );
        product.startDate( productRequestDto.getStartDate() );
        product.endDate( productRequestDto.getEndDate() );

        return product.build();
    }

    @Override
    public Product toProduct(ProductSaveRequestDto productSaveRequestDto) {
        if ( productSaveRequestDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.productType( productSaveRequestDto.getProductType() );
        product.accountNo( productSaveRequestDto.getAccountNo() );
        product.startDate( productSaveRequestDto.getStartDate() );
        product.endDate( productSaveRequestDto.getEndDate() );

        return product.build();
    }

    @Override
    public ProductResponseDto toProductResponseDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponseDto productResponseDto = new ProductResponseDto();

        productResponseDto.setUserId( productUserId( product ) );
        productResponseDto.setId( product.getId() );
        productResponseDto.setAccountNo( product.getAccountNo() );
        productResponseDto.setProductType( product.getProductType() );
        productResponseDto.setStartDate( product.getStartDate() );
        productResponseDto.setEndDate( product.getEndDate() );
        productResponseDto.setIsDeleted( product.getIsDeleted() );

        return productResponseDto;
    }

    @Override
    public ProductInfo toProductInfo(InquireDemandDepositAccountResponseDto inquireDemandDepositAccountResponseDto) {
        if ( inquireDemandDepositAccountResponseDto == null ) {
            return null;
        }

        ProductInfo productInfo = new ProductInfo();

        productInfo.setBankCode( inquireDemandDepositAccountResponseDto.getBankCode() );
        productInfo.setBankName( inquireDemandDepositAccountResponseDto.getBankName() );
        productInfo.setUserName( inquireDemandDepositAccountResponseDto.getUserName() );
        productInfo.setAccountNo( inquireDemandDepositAccountResponseDto.getAccountNo() );
        productInfo.setAccountName( inquireDemandDepositAccountResponseDto.getAccountName() );
        productInfo.setAccountTypeCode( inquireDemandDepositAccountResponseDto.getAccountTypeCode() );
        productInfo.setAccountTypeName( inquireDemandDepositAccountResponseDto.getAccountTypeName() );
        productInfo.setAccountCreatedDate( inquireDemandDepositAccountResponseDto.getAccountCreatedDate() );
        productInfo.setAccountExpiryDate( inquireDemandDepositAccountResponseDto.getAccountExpiryDate() );
        productInfo.setDailyTransferLimit( inquireDemandDepositAccountResponseDto.getDailyTransferLimit() );
        productInfo.setOneTimeTransferLimit( inquireDemandDepositAccountResponseDto.getOneTimeTransferLimit() );
        productInfo.setAccountBalance( inquireDemandDepositAccountResponseDto.getAccountBalance() );
        productInfo.setLastTransactionDate( inquireDemandDepositAccountResponseDto.getLastTransactionDate() );
        productInfo.setCurrency( inquireDemandDepositAccountResponseDto.getCurrency() );

        return productInfo;
    }

    @Override
    public ProductInfo toProductInfo(InquireSavingInfoResponseDto inquireSavingInfoResponseDto) {
        if ( inquireSavingInfoResponseDto == null ) {
            return null;
        }

        ProductInfo productInfo = new ProductInfo();

        productInfo.setBankCode( inquireSavingInfoResponseDto.getBankCode() );
        productInfo.setBankName( inquireSavingInfoResponseDto.getBankName() );
        productInfo.setUserName( inquireSavingInfoResponseDto.getUserName() );
        productInfo.setAccountNo( inquireSavingInfoResponseDto.getAccountNo() );
        productInfo.setAccountName( inquireSavingInfoResponseDto.getAccountName() );
        productInfo.setAccountExpiryDate( inquireSavingInfoResponseDto.getAccountExpiryDate() );

        return productInfo;
    }

    @Override
    public ProductInfo toProductInfo(InquireDepositInfoResponseDto inquireDepositInfoResponseDto) {
        if ( inquireDepositInfoResponseDto == null ) {
            return null;
        }

        ProductInfo productInfo = new ProductInfo();

        productInfo.setBankCode( inquireDepositInfoResponseDto.getBankCode() );
        productInfo.setBankName( inquireDepositInfoResponseDto.getBankName() );
        productInfo.setUserName( inquireDepositInfoResponseDto.getUserName() );
        productInfo.setAccountNo( inquireDepositInfoResponseDto.getAccountNo() );
        productInfo.setAccountName( inquireDepositInfoResponseDto.getAccountName() );
        productInfo.setAccountExpiryDate( inquireDepositInfoResponseDto.getAccountExpiryDate() );

        return productInfo;
    }

    @Override
    public ProductInfo toProductInfo(InquireLoanAccountResponseDto inquireLoanAccountResponseDto) {
        if ( inquireLoanAccountResponseDto == null ) {
            return null;
        }

        ProductInfo productInfo = new ProductInfo();

        productInfo.setAccountNo( inquireLoanAccountResponseDto.getAccountNo() );
        productInfo.setAccountName( inquireLoanAccountResponseDto.getAccountName() );

        return productInfo;
    }

    private Long productUserId(Product product) {
        if ( product == null ) {
            return null;
        }
        User user = product.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
