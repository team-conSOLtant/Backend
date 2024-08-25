package com.consoltant.consoltant.domain.product.service;

import com.consoltant.consoltant.domain.journey.entity.Journey;
import com.consoltant.consoltant.domain.journey.service.JourneyModuleService;
import com.consoltant.consoltant.domain.product.dto.ProductInfoResponseDto;
import com.consoltant.consoltant.domain.product.dto.ProductRequestDto;
import com.consoltant.consoltant.domain.product.dto.ProductResponseDto;
import com.consoltant.consoltant.domain.product.dto.ProductStatsResponseDto;
import com.consoltant.consoltant.domain.product.entity.Product;
import com.consoltant.consoltant.domain.product.mapper.ProductMapper;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.domain.user.service.UserService;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.api.RestTemplateUtil;
import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiredemanddepositaccount.InquireDemandDepositAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquireloanaccount.InquireLoanAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquiremycreditrating.InquireMyCreditRatingResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.inquiresavinginfo.InquireSavingInfoResponseDto;
import com.consoltant.consoltant.util.constant.JourneyType;
import com.consoltant.consoltant.util.constant.ProductType;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductModuleService productModuleService;
    private final UserRepository userRepository;
    private final JourneyModuleService journeyModuleService;
    private final ProductMapper productMapper;
    private final RestTemplateUtil restTemplateUtil;
    private final UserService userService;

    // 단일 조회
    public ProductResponseDto findById(Long id) {
        ProductResponseDto productResponseDto = productMapper.toProductResponseDto(
            productModuleService.findById(id));
        //TODO: product의 타입과 계좌번호로 금융 API 호출 (리턴 타입도 바꿔야할듯)
        return productResponseDto;
    }

    // 사용자 ID로 금융상품 통계 조회
    public ProductStatsResponseDto findStatsByUserId(Long userId){

        User user= userRepository.findById(userId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));
        String userKey = user.getUserKey();
        String accountNo = user.getAccountNo();

        //사용자 총 자산
        InquireMyCreditRatingResponseDto inquireMyCreditRatingResponseDto = restTemplateUtil.inquireMyCreditRating(userKey);

        Long totalAssetValue = inquireMyCreditRatingResponseDto.getTotalAssetValue();
        Long demandDepositAssetValue = inquireMyCreditRatingResponseDto.getDemandDepositAssetValue();

        Long savingAssetValue = restTemplateUtil.inquireSavingInfoList(userKey).stream()
                .mapToLong(InquireSavingInfoResponseDto::getDepositBalance)
                .sum();
        Long depositAssetValue = inquireMyCreditRatingResponseDto.getDepositSavingsAssetValue() - savingAssetValue;

        Long loanAssetValue = restTemplateUtil.inquireLoanAccountList(userKey).stream()
                .mapToLong(InquireLoanAccountResponseDto::getLoanBalance)
                .sum();

        InquireDemandDepositAccountResponseDto inquireDemandDepositAccountResponseDto = restTemplateUtil.inquireDemandDepositAccount(userKey,accountNo);
        String accountName = inquireDemandDepositAccountResponseDto.getAccountName();
        String accountType = inquireDemandDepositAccountResponseDto.getAccountTypeName();

        return ProductStatsResponseDto.builder()
                .accountNo(user.getAccountNo())
                .accountType(accountType)
                .accountName(accountName)
                .loan(loanAssetValue.doubleValue() / totalAssetValue.doubleValue() * 100)
                .totalAssetValue(totalAssetValue)
                .demandDeposit(demandDepositAssetValue.doubleValue() / totalAssetValue.doubleValue() * 100)
                .deposit(depositAssetValue.doubleValue() / totalAssetValue.doubleValue() * 100)
                .savings(savingAssetValue.doubleValue() / totalAssetValue.doubleValue() * 100)
                .build();
    }
    
    // 사용자 ID로 금융상품 리스트 조회
    public List<ProductResponseDto> findAllByUserId(Long userId){
        List<ProductInfoResponseDto> productList = new ArrayList<>();
        //TODO: 금융상품 리스트 돌면서 금융 API 호출
        for(Product product: productModuleService.findAllByUserId(userId)){
            productList.add(restTemplateUtil.inquireDemandDepositAccount())
        }
        return productList;
    }

    //사용자 여정에 해당하는 금융상품 리스트 조회
    public List<ProductResponseDto> findProductsByUserIdAndJourneyStartDate(Long userId, JourneyType journeyType){
        Journey journey = journeyModuleService.findByUserIdAndJourneyType(userId,
            journeyType);
        return productModuleService.findProductsByUserIdAndJourneyStartDate(userId, journey.getStartDate()).stream()
            .map(productMapper::toProductResponseDto)
            .toList();
    }

    //상품 타입에 따른 금융상품 리스트 조회
    public List<ProductResponseDto> findProductsByUserIdAndProductType(Long userId, ProductType productType){
        List<ProductResponseDto> productList = productModuleService.findProductsByUserIdAndProductType(
                userId, productType).stream()
            .map(productMapper::toProductResponseDto)
            .toList();
        //TODO: 조회한 productList들 금융 API에서 조회하여 리턴
        return productList;
    }

    // 등록
    public ProductResponseDto save(ProductRequestDto productRequestDto) {
        //TODO: 금융 API 호출하여 등록해주는 로직
        User user = userRepository.findById(productRequestDto.getUserId()).orElseThrow();
        Product product = productMapper.toProduct(productRequestDto);
        product.setUser(user);
        return productMapper.toProductResponseDto(productModuleService.save(product));
    }

    // 삭제
    public void delete(Long id){
        productModuleService.delete(id);
    }
}
