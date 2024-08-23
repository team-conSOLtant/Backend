package com.consoltant.consoltant.domain.follow.service;

import com.consoltant.consoltant.domain.follow.dto.FollowRequestDto;
import com.consoltant.consoltant.domain.follow.dto.FollowResponseDto;
import com.consoltant.consoltant.domain.follow.entity.Follow;
import com.consoltant.consoltant.domain.follow.mapper.FollowMapper;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowModuleService followModuleService;
    private final FollowMapper followMapper;
    private final PortfolioModuleService portfolioModuleService;
    private final UserRepository userRepository;

    // 유저 ID로 팔로잉 리스트 조회
    public List<FollowResponseDto> findAllByUserId(Long userId){
        return followModuleService.findAllByUserId(userId).stream()
            .map(followMapper::toFollowResponseDto)
            .toList();
    }

    //포폴 아이디로 팔로워 리스트 조회
    public List<FollowResponseDto> findAllByPortfolioId(Long portfolioId){
        return followModuleService.findAllByPortfolioId(portfolioId).stream()
            .map(followMapper::toFollowResponseDto)
            .toList();
    }

    // 등록
    public FollowResponseDto save(FollowRequestDto followRequestDto) {
        User user = userRepository.findById(followRequestDto.getUserId()).orElseThrow();
        Portfolio portfolio = portfolioModuleService.findById(followRequestDto.getPortfolioId());
        Follow follow = followMapper.toFollow(followRequestDto);
        follow.setPortfolio(portfolio);
        follow.setUser(user);
        return followMapper.toFollowResponseDto(followModuleService.save(follow));
    }

    // 삭제
    public void delete(Long id){
        followModuleService.delete(id);
    }
}