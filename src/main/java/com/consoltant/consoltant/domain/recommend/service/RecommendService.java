package com.consoltant.consoltant.domain.recommend.service;

import com.consoltant.consoltant.domain.recommend.dto.RecommendRequestDto;
import com.consoltant.consoltant.domain.recommend.dto.RecommendResponseDto;
import com.consoltant.consoltant.domain.recommend.entity.Recommend;
import com.consoltant.consoltant.domain.recommend.mapper.RecommendMapper;
import com.consoltant.consoltant.domain.recommend.repository.RecommendRepository;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserModuleRepository;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.api.RestTemplateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendService {

    private final RecommendRepository recommendRepository;
    private final RecommendMapper recommendMapper;
    private final UserRepository userRepository;
    private final RestTemplateUtil restTemplateUtil;
    private final UserModuleRepository userModuleRepository;

    //나의 상품 장바구니 전체 조회
    public List<RecommendResponseDto> findAllByUserId(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        return  recommendRepository.findAllByUserId(user.getId()).stream()
                .map(recommendMapper::toResponseDto)
                .toList();
    }

    //여정에 따른 내 상품 장바구니 조회
    public List<RecommendResponseDto> findAllByUserIdAndJourney(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        return  recommendRepository.findAllByUserIdAndJourneyType(user.getId(),user.getCurrentJourneyType()).stream()
                .map(recommendMapper::toResponseDto)
                .toList();
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
    
}
