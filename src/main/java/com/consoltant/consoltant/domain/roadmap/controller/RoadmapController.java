package com.consoltant.consoltant.domain.roadmap.controller;

import com.consoltant.consoltant.domain.bestroadmap.service.BestRoadmapModuleService;
import com.consoltant.consoltant.domain.bestroadmap.service.BestRoadmapService;
import com.consoltant.consoltant.domain.roadmap.dto.*;
import com.consoltant.consoltant.domain.roadmap.service.RoadmapModuleService;
import com.consoltant.consoltant.domain.roadmap.service.RoadmapService;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.domain.user.service.UserService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roadmaps")
@RequiredArgsConstructor
@Slf4j
public class RoadmapController {

    private final UserService userService;
    private final RoadmapService roadmapService;
    private final RoadmapModuleService roadmapModuleService;
    private final BestRoadmapService bestRoadmapService;
    private final UserRepository userRepository;
    private final BestRoadmapModuleService bestRoadmapModuleService;

    //연봉 별 모범 로드맵 생성 및 추천
    @GetMapping("/generate")
    public BaseSuccessResponse<Void> generateRoadmap() {
        log.info("모범 로드맵 생성하기 API");

        //로드맵 생성
        bestRoadmapService.generateBestRoadmap();

        List<Long> userIdList = userRepository.findAll().stream().map(User::getId).toList();

        //모든 유저와 로드맵 매칭
        //TODO 본인은 누구랑 매칭됨?

        for (Long userId : userIdList) {
            roadmapService.matchingRoadmap(userId);
        }

        return new BaseSuccessResponse<>(null);
    }

    //모범 로드맵
    @GetMapping("/best")
    public BaseSuccessResponse<RoadmapGraphResponseDto> bastRoadMap(){
        log.info("모범 로드맵 API");

        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());

        log.info("사용자 id -> {} ",id);

        Long roadmapUserId = roadmapModuleService.findRoadmapUserId(id);
        log.info("사용자 아이디-> {}", roadmapUserId);
        //로드맵이 할당되지 않은 경우 새로 찾아서 할당
        if(roadmapUserId == null){
            roadmapUserId = bestRoadmapService.findBestRoadmap(id);

            roadmapModuleService.saveRoadmap(id, roadmapUserId);
        }

        return new BaseSuccessResponse<>(roadmapService.makeRoadmap(roadmapUserId));
    }

    //예상 로드맵
    @PostMapping("/expect")
    public BaseSuccessResponse<ExpectRoadmapGraphResponseDto> expectedRoadMap(@RequestBody ExpectRoadmapRequestDto expectRoadmapRequestDto){
        log.info("예상 로드맵 API");

        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        Integer age = bestRoadmapModuleService.findUserAge(id);

        return new BaseSuccessResponse<>(roadmapService.makeExpectedRoadmap(id, expectRoadmapRequestDto.getProduct(), age));
    }

    //마이 로드맵
    @GetMapping("/present")
    public BaseSuccessResponse<RoadmapGraphResponseDto> getRoadmap(){
        log.info("마이 로드맵 API");
        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());

        return new BaseSuccessResponse<>(roadmapService.makeRoadmap(id));
    }

    //로드맵 피드백
    @PostMapping("/feedback")
    public BaseSuccessResponse<ChatbotFeedbackResponseDto>feedback(@RequestBody ChatbotFeedbackRequestDto chatbotFeedbackRequestDto){

        log.info("로드맵 피드백 API");

        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());

        //TODO 유저 로드맵 정보 가져오기
        RoadmapGraphResponseDto roadmapGraphResponseDto = roadmapService.makeRoadmap(id);

        return new BaseSuccessResponse<>(roadmapService.feedback(id, roadmapGraphResponseDto, chatbotFeedbackRequestDto.getPrompt()));
    }
}
