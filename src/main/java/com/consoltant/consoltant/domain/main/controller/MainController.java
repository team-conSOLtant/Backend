package com.consoltant.consoltant.domain.main.controller;

import com.consoltant.consoltant.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {
    
    //필요 유무 확인
    //private final MainService mainService;

    //사용자 이름, 학력 정보
    //private final UserService userService;
    //현재 내 자산 상태
    //private final ProductService productService;
    //상품 장바구니
    //private final RecommendService recommendService;
    //포트폴리오 알림 개수
    //private final NotificationService notificationService
    //여정 그래프
    //private final JourneyService journeyService


    //TODO 메인 페이지 정보 불러오기 API JWT로 파싱해서 유저 아이디 제공
    @GetMapping
    public ResponseEntity<?> getMainInfo(@RequestHeader("userId") String userId){
        return null;
    }
}
