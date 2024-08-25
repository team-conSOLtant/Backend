package com.consoltant.consoltant.domain.recommend.controller;

import com.consoltant.consoltant.domain.recommend.dto.RecommendRequestDto;
import com.consoltant.consoltant.domain.recommend.dto.RecommendResponseDto;
import com.consoltant.consoltant.domain.recommend.service.RecommendService;
import com.consoltant.consoltant.domain.user.service.UserService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommends")
@RequiredArgsConstructor
@Slf4j
public class RecommendController {
    private final RecommendService recommendService;
    private final UserService userService;

    @GetMapping
    public BaseSuccessResponse<List<RecommendResponseDto>> findAllById(){
        Long userId = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        return new BaseSuccessResponse<>(recommendService.findAllById(userId));
    }

    @PostMapping
    public BaseSuccessResponse<List<RecommendResponseDto>> save(@RequestBody RecommendRequestDto recommendRequestDto){
        Long userId = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        return new BaseSuccessResponse<>(recommendService.save(userId, recommendRequestDto));
    }
}
