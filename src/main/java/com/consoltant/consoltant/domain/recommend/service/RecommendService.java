package com.consoltant.consoltant.domain.recommend.service;

import com.consoltant.consoltant.domain.product.dto.ProductInfo;
import com.consoltant.consoltant.domain.product.mapper.ProductMapper;
import com.consoltant.consoltant.domain.recommend.dto.RecommendRequestDto;
import com.consoltant.consoltant.domain.recommend.dto.RecommendRequestDtoList;
import com.consoltant.consoltant.domain.recommend.dto.RecommendResponseDto;
import com.consoltant.consoltant.domain.recommend.entity.Recommend;
import com.consoltant.consoltant.domain.recommend.mapper.RecommendMapper;
import com.consoltant.consoltant.domain.recommend.repository.RecommendRepository;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserModuleRepository;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.api.RestTemplateUtil;
import com.consoltant.consoltant.util.api.dto.demanddeposit.inquiredemanddeposit.InquireDemandDepositResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.inquiredepositinfo.InquireDepositInfoResponseDto;
import com.consoltant.consoltant.util.api.dto.deposit.inquiredepositproducts.InquireDepositProductsResponseDto;
import com.consoltant.consoltant.util.api.dto.loan.inquireloanproduct.InquireLoanProductResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.inquiresavinginfo.InquireSavingInfoResponseDto;
import com.consoltant.consoltant.util.api.dto.saving.inquiresavingproducts.InquireSavingProductsResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendService {

    private final RecommendRepository recommendRepository;
    private final RecommendMapper recommendMapper;
    private final UserRepository userRepository;
    private final RestTemplateUtil restTemplateUtil;
    private final UserModuleRepository userModuleRepository;
    private final ProductMapper productMapper;

    //나의 상품 장바구니 전체 조회
    public List<RecommendResponseDto> findAllByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        List<RecommendResponseDto>recommendResponseDtoList = recommendRepository.findAllByUserId(user.getId()).stream()
                .map(recommendMapper::toResponseDto)
                .toList();

        List<InquireDemandDepositResponseDto> demandDepositList = restTemplateUtil.inquireDemandDepositList();
        List<InquireDepositProductsResponseDto> depositInfoList = restTemplateUtil.inquireDepositProducts();
        List<InquireSavingProductsResponseDto> savingInfoList = restTemplateUtil.inquireSavingProducts();
        List<InquireLoanProductResponseDto> loanProductList = restTemplateUtil.inquireLoanProductList();

        //상품 상세정보 가져오기
        for(RecommendResponseDto recommendResponseDto : recommendResponseDtoList){
            switch (recommendResponseDto.getProductType()){
                case DEMAND_DEPOSIT -> {recommendResponseDto.setProductInfo(
                        productMapper.toProductInfo(demandDepositList.stream()
                                .filter(s-> Objects.equals(s.getAccountTypeUniqueNo(), recommendResponseDto.getAccountTypeUniqueNo()))
                                .findFirst().orElse(null))
                );}
                case DEPOSIT -> {recommendResponseDto.setProductInfo(
                        productMapper.toProductInfo(depositInfoList.stream()
                                .filter(s-> Objects.equals(s.getAccountTypeUniqueNo(), recommendResponseDto.getAccountTypeUniqueNo()))
                                .findFirst().orElse(null))
                );}
                case SAVING -> {recommendResponseDto.setProductInfo(
                        productMapper.toProductInfo(savingInfoList.stream()
                                .filter(s-> Objects.equals(s.getAccountTypeUniqueNo(), recommendResponseDto.getAccountTypeUniqueNo()))
                                .findFirst().orElse(null))
                );}
                case LOAN -> {recommendResponseDto.setProductInfo(
                        productMapper.toProductInfo(loanProductList.stream()
                                .filter(s-> Objects.equals(s.getAccountTypeUniqueNo(), recommendResponseDto.getAccountTypeUniqueNo()))
                                .findFirst().orElse(null))
                );}
            }
        }

        return recommendResponseDtoList;

    }

    //여정에 따른 내 상품 장바구니 조회
    public List<RecommendResponseDto> findAllByUserIdAndJourney(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        return  recommendRepository.findAllByUserIdAndJourneyType(user.getId(),user.getCurrentJourneyType()).stream()
                .map(recommendMapper::toResponseDto)
                .toList();
    }

    //연도별 상품 장바구니 개수
    public List<RecommendResponseDto> findAllByUserIdAndYear(Long userId, Integer year){
        User user = userRepository.findById(userId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        //상품 목록 조회
        List<RecommendResponseDto> recommendResponseDtoList = recommendRepository.findAllByUserIdAndYear(user.getId(),year).stream()
                .map(recommendMapper::toResponseDto)
                .toList();

        List<InquireDemandDepositResponseDto> demandDepositList = restTemplateUtil.inquireDemandDepositList();
        List<InquireDepositProductsResponseDto> depositInfoList = restTemplateUtil.inquireDepositProducts();
        List<InquireSavingProductsResponseDto> savingInfoList = restTemplateUtil.inquireSavingProducts();
        List<InquireLoanProductResponseDto> loanProductList = restTemplateUtil.inquireLoanProductList();

        //상품 상세정보 가져오기
        for(RecommendResponseDto recommendResponseDto : recommendResponseDtoList){
            switch (recommendResponseDto.getProductType()){
                case DEMAND_DEPOSIT -> {recommendResponseDto.setProductInfo(
                        productMapper.toProductInfo(demandDepositList.stream()
                                .filter(s-> Objects.equals(s.getAccountTypeUniqueNo(), recommendResponseDto.getAccountTypeUniqueNo()))
                                .findFirst().orElse(null))
                );}
                case DEPOSIT -> {recommendResponseDto.setProductInfo(
                        productMapper.toProductInfo(depositInfoList.stream()
                                .filter(s-> Objects.equals(s.getAccountTypeUniqueNo(), recommendResponseDto.getAccountTypeUniqueNo()))
                                .findFirst().orElse(null))
                );}
                case SAVING -> {recommendResponseDto.setProductInfo(
                        productMapper.toProductInfo(savingInfoList.stream()
                                .filter(s-> Objects.equals(s.getAccountTypeUniqueNo(), recommendResponseDto.getAccountTypeUniqueNo()))
                                .findFirst().orElse(null))
                );}
                case LOAN -> {recommendResponseDto.setProductInfo(
                        productMapper.toProductInfo(loanProductList.stream()
                                .filter(s-> Objects.equals(s.getAccountTypeUniqueNo(), recommendResponseDto.getAccountTypeUniqueNo()))
                                .findFirst().orElse(null))
                );}
            }
        }

        return recommendResponseDtoList;
    }

    // 상품 장바구니 등록
    public List<RecommendResponseDto> save(Long userId, RecommendRequestDto recommendRequestDto){

        Recommend recommend = recommendMapper.toRecommend(recommendRequestDto);
        User user = userModuleRepository.findById(userId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        recommend.setUser(user);
        recommendRepository.save(recommend);

        return recommendRepository.findAllByUserId(userId).stream()
                .map(recommendMapper::toResponseDto)
                .toList();
    }

    public void delete(Long id){
        recommendRepository.deleteById(id);
    }

    public List<RecommendResponseDto> saveAll(Long userId, RecommendRequestDtoList requestDto) {
        User user = userRepository.findById(userId).orElseThrow((()->new BadRequestException("존재하지 않는 사용자입니다.")));

        for(RecommendRequestDto recommendRequestDto : requestDto.getRecommendRequestDtoList()) {
            Recommend recommend = recommendMapper.toRecommend(recommendRequestDto);
            recommend.setUser(user);
            recommendRepository.save(recommend);
        }



        return recommendRepository.findAllByUserId(userId).stream()
                .map(recommendMapper::toResponseDto)
                .toList();
    }
    
}
