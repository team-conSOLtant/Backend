package com.consoltant.consoltant.domain.award.controller;

import com.consoltant.consoltant.domain.award.dto.AwardRequestDto;
import com.consoltant.consoltant.domain.award.dto.AwardResponseDto;
import com.consoltant.consoltant.domain.award.service.AwardService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
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
@RequestMapping("/awards")
public class AwardController {

    private final AwardService awardService;

    //단일 조회
    @GetMapping("/{id}")
    public BaseSuccessResponse<AwardResponseDto> findById(@PathVariable Long id) {
        return new BaseSuccessResponse<>(awardService.findById(id));
    }

    //포폴 아이디로 수상 리스트 조회
    @GetMapping
    public BaseSuccessResponse<List<AwardResponseDto>> findAllByPortfolioId(@RequestParam Long portfolioId){
        return new BaseSuccessResponse<>(awardService.findAllByPortfolioId(portfolioId));
    }

    //수상 등록
    @PostMapping
    public BaseSuccessResponse<AwardResponseDto> save(@RequestBody AwardRequestDto awardRequestDto) {
        return new BaseSuccessResponse<>(awardService.save(awardRequestDto));
    }

    //수상 수정
    @PutMapping("/{id}")
    public BaseSuccessResponse<AwardResponseDto> update(@PathVariable Long id, @RequestBody AwardRequestDto awardRequestDto){
        return new BaseSuccessResponse<>(awardService.update(id, awardRequestDto));
    }

    //수상 삭제
    @DeleteMapping("/{id}")
    public BaseSuccessResponse<Void> delete(@PathVariable Long id){
        awardService.delete(id);
        return new BaseSuccessResponse<>(null);
    }
}
