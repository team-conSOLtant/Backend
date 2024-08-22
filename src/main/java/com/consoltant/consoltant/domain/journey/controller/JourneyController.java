package com.consoltant.consoltant.domain.journey.controller;

import com.consoltant.consoltant.domain.journey.dto.JourneyRequestDto;
import com.consoltant.consoltant.domain.journey.dto.JourneyResponseDto;
import com.consoltant.consoltant.domain.journey.service.JourneyService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import com.consoltant.consoltant.util.constant.JourneyType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}