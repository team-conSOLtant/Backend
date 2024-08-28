package com.consoltant.consoltant.domain.follow.controller;

import com.consoltant.consoltant.domain.follow.dto.FollowRequestDto;
import com.consoltant.consoltant.domain.follow.dto.FollowResponseDto;
import com.consoltant.consoltant.domain.follow.service.FollowService;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioSearchResponseDto;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/follows")
public class FollowController {

    private final FollowService followService;

    // 유저 ID로 팔로잉 조회
    @GetMapping("/following")
    public BaseSuccessResponse<List<PortfolioSearchResponseDto>> findFollowings(@RequestParam Long userId){
        return new BaseSuccessResponse<>(followService.findAllByUserId(userId));
    }

    // 포폴 ID로 팔로워 조회
    @GetMapping("/follower")
    public BaseSuccessResponse<List<PortfolioSearchResponseDto>> findFollowers(@RequestParam Long portfolioId){
        return new BaseSuccessResponse<>(followService.findAllByPortfolioId(portfolioId));
    }

    //팔로잉 등록
    @PostMapping()
    public BaseSuccessResponse<FollowResponseDto> saveFollowings(@RequestBody FollowRequestDto followRequestDto){
        return new BaseSuccessResponse<>(followService.save(followRequestDto));
    }

    // 팔로우 삭제
    @DeleteMapping("/{id}")
    public BaseSuccessResponse<Void> delete(@PathVariable Long id){
        followService.delete(id);
        return new BaseSuccessResponse<>(null);
    }
}
