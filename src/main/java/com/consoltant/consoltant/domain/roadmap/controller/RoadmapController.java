package com.consoltant.consoltant.domain.roadmap.controller;

import com.consoltant.consoltant.domain.bestroadmap.service.BestRoadmapService;
import com.consoltant.consoltant.domain.roadmap.dto.RoadmapGraphResponseDto;
import com.consoltant.consoltant.domain.roadmap.entity.Roadmap;
import com.consoltant.consoltant.domain.roadmap.service.RoadmapModuleService;
import com.consoltant.consoltant.domain.roadmap.service.RoadmapService;
import com.consoltant.consoltant.domain.user.service.UserService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //연봉 별 모범 로드맵 생성 및 추천
    @GetMapping("/generate")
    public BaseSuccessResponse<Void> selectRoadmap() {
        log.info("모범 로드맵 생성하기 API");
        return new BaseSuccessResponse<>(null);
    }

    //모범 로드맵
    @GetMapping("/best")
    public BaseSuccessResponse<RoadmapGraphResponseDto> bastRoadMap(){
        log.info("모범 로드맵 API");
        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());

        Long roadmapUserId = roadmapModuleService.findRoadmapUserId(id);
        
        //로드맵이 할당되지 않은 경우 새로 찾아서 할당
        if(roadmapUserId == null){
            roadmapUserId = bestRoadmapService.findBestRoadmap(id);

            roadmapModuleService.saveRoadmap(id, roadmapUserId);
        }

        return new BaseSuccessResponse<>(roadmapService.makeGraph(roadmapUserId));
    }

    //예상 로드맵
    @GetMapping("/expect")
    public BaseSuccessResponse<RoadmapGraphResponseDto> expectedRoadMap(){
        log.info("예상 로드맵 API");
        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        roadmapService.makeGraph(id);

        return new BaseSuccessResponse<>(null);
    }

    //마이 로드맵
    @GetMapping("/present")
    public BaseSuccessResponse<RoadmapGraphResponseDto> getRoadmap(){
        log.info("마이 로드맵 API");
        Long id = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());

        return new BaseSuccessResponse<>(roadmapService.makeGraph(id));
    }
}
