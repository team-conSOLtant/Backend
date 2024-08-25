package com.consoltant.consoltant.domain.recommend.service;

import com.consoltant.consoltant.domain.recommend.dto.RecommendRequestDto;
import com.consoltant.consoltant.domain.recommend.dto.RecommendResponseDto;
import com.consoltant.consoltant.domain.recommend.entity.Recommend;
import com.consoltant.consoltant.domain.recommend.mapper.RecommendMapper;
import com.consoltant.consoltant.domain.recommend.repository.RecommendRepository;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final RecommendRepository recommendRepository;
    private final RecommendMapper recommendMapper;
    private final UserRepository userRepository;

    public List<RecommendResponseDto> findAllById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new BadRequestException("존재하지 않는 사용자입니다."));

        return  recommendRepository.findAllByIdAndJourneyType(user.getId(),user.getCurrentJourneyType()).stream()
                .map(recommendMapper::toResponseDto)
                .toList();
    }

    public List<RecommendResponseDto> save(Long userId, RecommendRequestDto recommendRequestDto){
//        recommendRequestDto
//        Recommend recommend = recommendRepository.save()

        return null;
    }
}
