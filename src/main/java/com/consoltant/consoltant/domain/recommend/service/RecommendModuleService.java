package com.consoltant.consoltant.domain.recommend.service;

import com.consoltant.consoltant.domain.recommend.dto.RecommendRequestDto;
import com.consoltant.consoltant.domain.recommend.dto.RecommendResponseDto;
import com.consoltant.consoltant.domain.recommend.mapper.RecommendMapper;
import com.consoltant.consoltant.domain.recommend.repository.RecommendRepository;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class RecommendModuleService {
    private final RecommendRepository recommendRepository;
    private final RecommendMapper recommendMapper;
    private final UserRepository userRepository;
    

}
