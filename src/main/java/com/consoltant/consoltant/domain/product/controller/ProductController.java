package com.consoltant.consoltant.domain.product.controller;

import com.consoltant.consoltant.domain.journey.dto.JourneyRequestDto;
import com.consoltant.consoltant.domain.product.dto.*;
import com.consoltant.consoltant.domain.product.service.ProductService;
import com.consoltant.consoltant.domain.user.dto.UserResponseDto;
import com.consoltant.consoltant.domain.user.service.UserService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    // 타입별 금융 상품 목록 조회
    @PostMapping("/bank/type")
    public BaseSuccessResponse<List<?>> findBankProductByType(@RequestBody BankProductInfoRequestDto bankProductInfoRequestDto) {
        Long userId = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        String userKey = userService.getUserKey(userId);
        return new BaseSuccessResponse<>(productService.findBankProductByType(userKey, bankProductInfoRequestDto.getProductType()));
    }
    
    // 단일 조회
    @GetMapping("/{id}")
    public BaseSuccessResponse<ProductResponseDto> findById(@PathVariable Long id) {
        return new BaseSuccessResponse<>(productService.findById(id));
    }

    // 사용자 ID로 금융상품 통계 보기
    @GetMapping("/stats")
    public BaseSuccessResponse<ProductStatsResponseDto> findStatsByUserId(){
        log.info("금융상품 통계 API");
        Long userId = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());

        return new BaseSuccessResponse<>(productService.findStatsByUserId(userId));
    }
    
    // 사용자 ID로 금융상품 리스트 조회
    @GetMapping
    public BaseSuccessResponse<ProductListResponseDto> findAllByUserId(){
        log.info("금융상품 리스트 조회 API");
        Long userId = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        String userKey = userService.getUserKey(userId);
        return new BaseSuccessResponse<>(productService.findAllByUserId(userId, userKey));
    }

    //타입별로 사용자 상품 리스트 조회
    @PostMapping("/type")
    public BaseSuccessResponse<List<ProductResponseDto>> findProductsByUserIdAndJourneyType(
        @RequestBody ProductRequestDto productRequestDto){
        log.info("타입별 사용자 상품 리스트 조회");
        return new BaseSuccessResponse<>(productService.findProductsByUserIdAndProductType(
           productRequestDto.getUserId(), productRequestDto.getProductType()));
    }

    // 금융 상품 등록
    @PostMapping
    public BaseSuccessResponse<ProductResponseDto> save(@RequestBody ProductSaveRequestDto productRequestDto) {
        log.info("금융 상품 등록");
        Long userId = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        return new BaseSuccessResponse<>(productService.save(userId, productRequestDto));
    }


    // 금융상품 삭제
    @DeleteMapping("/{id}")
    public BaseSuccessResponse<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return new BaseSuccessResponse<>(null);
    }

    //사용자 여정에 해당하는 금융상품 조회 테스트용 API
    @PostMapping("/journey")
    public BaseSuccessResponse<List<ProductResponseDto>> findProductsByUserIdAndJourneyStartDate(
        @RequestBody JourneyRequestDto journeyRequestDto){
        return new BaseSuccessResponse<>(productService.findProductsByUserIdAndJourneyStartDate(
            journeyRequestDto.getUserId(), journeyRequestDto.getJourneyType()));
    }


}
