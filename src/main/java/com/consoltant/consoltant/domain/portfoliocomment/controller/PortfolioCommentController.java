package com.consoltant.consoltant.domain.portfoliocomment.controller;

import com.consoltant.consoltant.domain.portfoliocomment.dto.PortfolioCommentRequestDto;
import com.consoltant.consoltant.domain.portfoliocomment.dto.PortfolioCommentResponseDto;
import com.consoltant.consoltant.domain.portfoliocomment.service.PortfolioCommentService;
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
@RequestMapping("/portfolio-comments")
public class PortfolioCommentController {

    private final PortfolioCommentService portfolioCommentService;

    // 단일 조회 (필요 시 사용)
    @GetMapping("/{id}")
    public BaseSuccessResponse<PortfolioCommentResponseDto> findById(@PathVariable Long id) {
        return new BaseSuccessResponse<>(portfolioCommentService.findById(id));
    }

    // 포트폴리오 ID로 코멘트 리스트 조회
    @GetMapping
    public BaseSuccessResponse<List<PortfolioCommentResponseDto>> findAllByPortfolioId(@RequestParam Long portfolioId){
        return new BaseSuccessResponse<>(portfolioCommentService.findAllByPortfolioId(portfolioId));
    }

    // 코멘트 등록
    @PostMapping
    public BaseSuccessResponse<PortfolioCommentResponseDto> save(@RequestBody PortfolioCommentRequestDto portfolioCommentRequestDto) {
        return new BaseSuccessResponse<>(portfolioCommentService.save(portfolioCommentRequestDto));
    }

    // 코멘트 수정
    @PutMapping("/{id}")
    public BaseSuccessResponse<PortfolioCommentResponseDto> update(@PathVariable Long id, @RequestBody PortfolioCommentRequestDto portfolioCommentRequestDto){
        return new BaseSuccessResponse<>(portfolioCommentService.update(id, portfolioCommentRequestDto));
    }

    // 코멘트 삭제
    @DeleteMapping("/{id}")
    public BaseSuccessResponse<Void> delete(@PathVariable Long id){
        portfolioCommentService.delete(id);
        return new BaseSuccessResponse<>(null);
    }
}
