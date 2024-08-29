package com.consoltant.consoltant.domain.journey.controller;

import co.elastic.clients.elasticsearch.xpack.usage.Base;
import com.consoltant.consoltant.domain.journey.dto.JourneyGraphResponseDto;
import com.consoltant.consoltant.domain.journey.dto.JourneyRequestDto;
import com.consoltant.consoltant.domain.journey.dto.JourneyResponseDto;
import com.consoltant.consoltant.domain.journey.dto.JourneyStatsResponseDto;
import com.consoltant.consoltant.domain.journey.service.JourneyModuleService;
import com.consoltant.consoltant.domain.journey.service.JourneyService;
import com.consoltant.consoltant.domain.user.entity.User;
import com.consoltant.consoltant.domain.user.repository.UserRepository;
import com.consoltant.consoltant.domain.user.service.UserService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import com.consoltant.consoltant.util.constant.JourneyType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/journeys")
public class JourneyController {

    private final JourneyService journeyService;
    private final UserService userService;
    private final JourneyModuleService journeyModuleService;
    private final UserRepository userRepository;

    @GetMapping("/default")
    public BaseSuccessResponse<Void> test(){

        Long userId = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        User user = userRepository.findById(userId).get();
        journeyModuleService.defaultSave(user);
        return new BaseSuccessResponse<>(null);
    }

    // 단일 조회
    @GetMapping("/{id}")
    public BaseSuccessResponse<JourneyResponseDto> findById(@PathVariable Long id) {
        return new BaseSuccessResponse<>(journeyService.findById(id));
    }

    //유저 Id와 Journey 타입으로 조회
    @GetMapping("/{id}/type")
    public BaseSuccessResponse<JourneyResponseDto> findByUserIdAndJourneyType(@PathVariable Long id, @RequestParam
        JourneyType journeyType) {
        return new BaseSuccessResponse<>(journeyService.findByUserIdAndJourneyType(id, journeyType));
    }

    // 유저 ID로 여정 리스트 조회
    @GetMapping
    public BaseSuccessResponse<List<JourneyResponseDto>> findAllByUserId(@RequestParam Long userId){
        return new BaseSuccessResponse<>(journeyService.findAllByUserId(userId));
    }

    // 여정 등록
    @PostMapping
    public BaseSuccessResponse<JourneyResponseDto> save(@RequestBody JourneyRequestDto journeyRequestDto) {
        return new BaseSuccessResponse<>(journeyService.save(journeyRequestDto));
    }

    // 여정 수정
    @PutMapping("/{id}")
    public BaseSuccessResponse<JourneyResponseDto> update(@PathVariable Long id, @RequestBody JourneyRequestDto journeyRequestDto){
        return new BaseSuccessResponse<>(journeyService.update(id, journeyRequestDto));
    }

    // 여정 삭제
    @DeleteMapping("/{id}")
    public BaseSuccessResponse<Void> delete(@PathVariable Long id){
        journeyService.delete(id);
        return new BaseSuccessResponse<>(null);
    }

    //여정 별 금융 상품 통계
    @GetMapping("/stats")
    public BaseSuccessResponse<List<JourneyStatsResponseDto>> stats(){
        log.info("여정 별 자산 통계 API");
        Long userId = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());

        return new BaseSuccessResponse<>(journeyService.findStatsByUserId(userId));
    }

    //여정 그래프
    @GetMapping("/graph")
    public BaseSuccessResponse<JourneyGraphResponseDto> graph(){
        log.info("여정 별 자산 그래프 API");

        Long userId = userService.getUserId(SecurityContextHolder.getContext().getAuthentication().getName());
        return new BaseSuccessResponse<>(journeyService.findGraphByUserId(userId));
    }
}