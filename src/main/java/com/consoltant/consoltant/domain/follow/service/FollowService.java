package com.consoltant.consoltant.domain.follow.service;

import com.consoltant.consoltant.domain.follow.dto.FollowRequestDto;
import com.consoltant.consoltant.domain.follow.dto.FollowResponseDto;
import com.consoltant.consoltant.domain.follow.entity.Follow;
import com.consoltant.consoltant.domain.follow.mapper.FollowMapper;
import com.consoltant.consoltant.domain.notification.entity.Notification;
import com.consoltant.consoltant.domain.notification.service.NotificationModuleService;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioModuleService;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.global.exception.BadRequestException;
import com.consoltant.consoltant.util.constant.NotificationType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowModuleService followModuleService;
    private final FollowMapper followMapper;
    private final PortfolioModuleService portfolioModuleService;
    private final NotificationModuleService notificationModuleService;
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
    @Transactional
    public FollowResponseDto save(FollowRequestDto followRequestDto) {
        User user = userRepository.findById(followRequestDto.getUserId()).orElseThrow();
        Portfolio portfolio = portfolioModuleService.findById(followRequestDto.getPortfolioId());

        // 이미 팔로우 중인지 확인
        if (followModuleService.isAlreadyFollowing(user, portfolio)) {
            throw new BadRequestException("이미 해당 포트폴리오를 팔로우하고 있습니다.");
        }

        Follow follow = followMapper.toFollow(followRequestDto);
        follow.setPortfolio(portfolio);
        follow.setUser(user);
        //사용자 알림
        notificationModuleService.save(Notification.builder()
            .user(portfolio.getUser())
            .notificationType(NotificationType.PORTFOLIO_FOLLOW)
            .content(user.getName() + " 님이 당신을 팔로우 했습니다.")
            .build());
        return followMapper.toFollowResponseDto(followModuleService.save(follow));
    }

    // 삭제
    public void delete(Long id){
        followModuleService.delete(id);
    }
}