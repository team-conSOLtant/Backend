package com.consoltant.consoltant.domain.follow.service;

import com.consoltant.consoltant.domain.follow.entity.Follow;
import com.consoltant.consoltant.domain.follow.repository.FollowRepository;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowModuleService {
    private final FollowRepository followRepository;

    // Follow ID로 단일 조회
    public Follow findById(Long id) {
        return followRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Follow ID"));
    }

    // 유저 ID로 모든 팔로잉 조회
    public List<Follow> findAllByUserId(Long userId) {
        return followRepository.findAllByUserId(userId);
    }

    // 포폴 ID로 모든 팔로워 조회
    public List<Follow> findAllByPortfolioId(Long portfolioId) {
        return followRepository.findAllByPortfolioId(portfolioId);
    }

    public Boolean isAlreadyFollowing(User user, Portfolio portfolio) {
        return followRepository.existsByUserAndPortfolio(user, portfolio);
    }

    // Follow 저장
    public Follow save(Follow follow) {
        return followRepository.save(follow);
    }

    // Follow 삭제
    public void delete(Long id) {
        followRepository.deleteById(id);
    }
}
