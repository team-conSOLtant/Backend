package com.consoltant.consoltant.domain.roadmap.controller;

import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roadmaps")
@RequiredArgsConstructor
@Slf4j
public class RoadmapController {
    //연봉 별 모범 로드맵 재생성
    //@GetMapping
    //public List<Roadmap> selectRoadmap() {}

//    @GetMapping
//    public BaseSuccessResponse<Void> bastRoadMap(){
//        return new BaseSuccessResponse<>(null);
//    }
//
//    @GetMapping
//    public BaseSuccessResponse<Void> expectedRoadMap(){
//        return new BaseSuccessResponse<>(null);
//    }
}
