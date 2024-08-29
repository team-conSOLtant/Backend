package com.consoltant.consoltant.domain.product.service;

import com.consoltant.consoltant.domain.journey.dto.JourneyStatsResponseDto;
import com.consoltant.consoltant.domain.journey.entity.Journey;
import com.consoltant.consoltant.domain.journey.service.JourneyModuleService;
import com.consoltant.consoltant.domain.product.dto.*;
import com.consoltant.consoltant.domain.product.entity.Product;
import com.consoltant.consoltant.domain.product.mapper.ProductMapper;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.domain.user.service.UserService;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.api.RestTemplateUtil;
import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiredemanddeposit.InquireDemandDepositResponseDto;
import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiredemanddepositaccount.InquireDemandDepositAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.inquiredepositinfo.InquireDepositInfoResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.inquiredepositproducts.InquireDepositProductsResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquireloanaccount.InquireLoanAccountResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquireloanproduct.InquireLoanProductResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquiremycreditrating.InquireMyCreditRatingResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.inquiresavinginfo.InquireSavingInfoResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.inquiresavingproducts.InquireSavingProductsResponseDto;
import com.consoltant.consoltant.util.constant.JourneyType;
import com.consoltant.consoltant.util.constant.ProductType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductModuleService productModuleService;
    private final UserRepository userRepository;
    private final JourneyModuleService journeyModuleService;
    private final ProductMapper productMapper;
    private final RestTemplateUtil restTemplateUtil;
    private final UserService userService;

    //판매중인 금융 상품 조회
    public List<?> findBankProductByType(ProductType productType){
        return switch (productType) {
            case DEMAND_DEPOSIT -> restTemplateUtil.inquireDemandDepositList();
            case SAVING -> null;
            case LOAN -> null;
            case DEPOSIT -> null;
        };
    }

    // 단일 조회
    public ProductResponseDto findById(Long id) {
        ProductResponseDto productResponseDto = productMapper.toProductResponseDto(
            productModuleService.findById(id));
        //TODO: product의 타입과 계좌번호로 금융 API 호출 (리턴 타입도 바꿔야할듯)
        return productResponseDto;
    }

    // JourneyType 별 사용자 ID로 금융상품 통계 조회
    public List<JourneyStatsResponseDto> findStatsByUserId(Long userId){

        return null;
    }
    
    // 사용자 ID로 금융상품 리스트 조회
    public ProductListResponseDto findAllByUserId(Long userId, String userKey){
        ProductListResponseDto productInfoList = new ProductListResponseDto();

        List<Product> productList = productModuleService.findAllByUserId(userId);

        List<InquireDemandDepositResponseDto> demandDepositList = restTemplateUtil.inquireDemandDepositList();
        List<InquireDepositProductsResponseDto> depositList = restTemplateUtil.inquireDepositProducts();
        List<InquireSavingProductsResponseDto> savingList = restTemplateUtil.inquireSavingProducts();
        List<InquireLoanProductResponseDto> loanList = restTemplateUtil.inquireLoanProductList();

        for(Product product: productList){
            switch (product.getProductType()){
                case DEMAND_DEPOSIT:
                    log.info("수시입출금");
//                    productInfoList.getDemandDeposit().add(productMapper.toProductInfo(restTemplateUtil.inquireDemandDepositAccount(userKey,product.getAccountNo())));
                    productInfoList.getDemandDeposit().add(
                            demandDepositList.stream()
                                    .filter(s-> Objects.equals(s.getAccountTypeUniqueNo(), product.getAccountTypeUniqueNo()))
                                    .findAny()
                                    .get()
                    );
                    break;
                case DEPOSIT:
                    log.info("예금");
//                    productInfoList.getDeposit().add(productMapper.toProductInfo(restTemplateUtil.inquireDemandDepositAccount(userKey,product.getAccountNo())));
                    productInfoList.getDeposit().add(
                            depositList.stream()
                                    .filter(s-> Objects.equals(s.getAccountTypeUniqueNo(), product.getAccountTypeUniqueNo()))
                                    .findAny()
                                    .get()
                    );
                    break;
                case LOAN:
                    log.info("대출");
//                    productInfoList.getLoan().add(productMapper.toProductInfo(restTemplateUtil.inquireDemandDepositAccount(userKey,product.getAccountNo())));
                    productInfoList.getLoan().add(
                            loanList.stream()
                                    .filter(s-> Objects.equals(s.getAccountTypeUniqueNo(), product.getAccountTypeUniqueNo()))
                                    .findAny()
                                    .get()
                    );
                    break;
                case SAVING:
                    log.info("적금");
//                    productInfoList.getSaving().add(productMapper.toProductInfo(restTemplateUtil.inquireDemandDepositAccount(userKey,product.getAccountNo())));
                    productInfoList.getSaving().add(
                            savingList.stream()
                                    .filter(s-> Objects.equals(s.getAccountTypeUniqueNo(), product.getAccountTypeUniqueNo()))
                                    .findAny()
                                    .get()
                    );
                    break;
                default:
                    break;
            }
        }

        return productInfoList;
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
    public ProductResponseDto save(Long userId, ProductSaveRequestDto productRequestDto) {
        //TODO: 금융 API 호출하여 등록해주는 로직
        User user = userRepository.findById(userId).orElseThrow();

        String startDate = "";
        String endDate = "";
        switch (productRequestDto.getProductType()){
            case DEMAND_DEPOSIT:
                log.info("수시입출금 등록");
                InquireDemandDepositAccountResponseDto inquireDemandDepositAccountResponseDto = restTemplateUtil.inquireDemandDepositAccount(user.getUserKey(), productRequestDto.getAccountNo());

                startDate = inquireDemandDepositAccountResponseDto.getAccountCreatedDate();
                endDate = inquireDemandDepositAccountResponseDto.getAccountExpiryDate();


                productRequestDto.setStartDate(
                        LocalDate.of(Integer.parseInt(startDate.substring(0,4)),
                                Integer.parseInt(startDate.substring(4,6)),
                                Integer.parseInt(startDate.substring(6,8)))
                );

                productRequestDto.setEndDate(
                        LocalDate.of(Integer.parseInt(endDate.substring(0,4)),
                                Integer.parseInt(endDate.substring(4,6)),
                                Integer.parseInt(endDate.substring(6,8)))
                );

                break;
            case DEPOSIT:
                log.info("예금 등록");
//                InquireDepositInfoResponseDto inquireDepositInfoResponseDto = restTemplateUtil.inquireDepo(user.getUserKey(),productRequestDto.getAccountNo());
//
//                startDate = inquireDepositInfoResponseDto.getAccountCreateDate();
//                endDate = inquireDepositInfoResponseDto.getAccountExpiryDate();
//
//
//                productRequestDto.setStartDate(
//                        LocalDate.of(Integer.parseInt(startDate.substring(0,4)),
//                                Integer.parseInt(startDate.substring(4,6)),
//                                Integer.parseInt(startDate.substring(6,8)))
//                );
//
//                productRequestDto.setEndDate(
//                        LocalDate.of(Integer.parseInt(endDate.substring(0,4)),
//                                Integer.parseInt(endDate.substring(4,6)),
//                                Integer.parseInt(endDate.substring(6,8)))
//                );

                break;
            case LOAN:
//                log.info("대출 등록");
//                InquireLoanAccountResponseDto inquireLoanAccountResponseDto = restTemplateUtil.inquireLoan(user.getUserKey(), productRequestDto.getAccountNo());
//
//                String startDate = inquireDemandDepositAccountResponseDto.getAccountCreatedDate();
//                String endDate = inquireDemandDepositAccountResponseDto.getAccountExpiryDate();
//
//
//                productRequestDto.setStartDate(
//                        LocalDate.of(Integer.parseInt(startDate.substring(0,4)),
//                                Integer.parseInt(startDate.substring(4,6)),
//                                Integer.parseInt(startDate.substring(6,8)))
//                );
//
//                productRequestDto.setStartDate(
//                        LocalDate.of(Integer.parseInt(endDate.substring(0,4)),
//                                Integer.parseInt(endDate.substring(4,6)),
//                                Integer.parseInt(endDate.substring(6,8)))
//                );

                break;
            case SAVING:
//                log.info("적금 등록");
//                InquireDemandDepositAccountResponseDto inquireDemandDepositAccountResponseDto = restTemplateUtil.inquireDemandDepositAccount(user.getUserKey(), productRequestDto.getAccountNo());
//
//                String startDate = inquireDemandDepositAccountResponseDto.getAccountCreatedDate();
//                String endDate = inquireDemandDepositAccountResponseDto.getAccountExpiryDate();
//
//
//                productRequestDto.setStartDate(
//                        LocalDate.of(Integer.parseInt(startDate.substring(0,4)),
//                                Integer.parseInt(startDate.substring(4,6)),
//                                Integer.parseInt(startDate.substring(6,8)))
//                );
//
//                productRequestDto.setStartDate(
//                        LocalDate.of(Integer.parseInt(endDate.substring(0,4)),
//                                Integer.parseInt(endDate.substring(4,6)),
//                                Integer.parseInt(endDate.substring(6,8)))
//                );

                break;
            default:
                break;
        }

        Product product = productMapper.toProduct(productRequestDto);

        product.setUser(user);
        return productMapper.toProductResponseDto(productModuleService.save(product));
    }

    // 삭제
    public void delete(Long id){
        productModuleService.delete(id);
    }
}
