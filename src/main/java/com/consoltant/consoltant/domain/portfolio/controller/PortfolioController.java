package com.consoltant.consoltant.domain.portfolio.controller;

import com.consoltant.consoltant.domain.portfolio.dto.ImageUploadRequest;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioRequestDto;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioResponseDto;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioSaveAllRequestDto;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioSearchRequestDto;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioSearchResponseDto;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioElasticService;
import com.consoltant.consoltant.domain.portfolio.service.PortfolioService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
@RequestMapping("/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final PortfolioElasticService portfolioElasticService;

    @GetMapping("/{id}")
    public BaseSuccessResponse<PortfolioResponseDto> findById(@PathVariable Long id) {
        return new BaseSuccessResponse<>(portfolioService.findById(id));
    }

    @GetMapping
    public BaseSuccessResponse<PortfolioResponseDto> findByUserId(@RequestParam Long userId)
        throws IOException {
        return new BaseSuccessResponse<>(portfolioService.findByUserId(userId));
    }

    @PostMapping
    public BaseSuccessResponse<PortfolioResponseDto> save(@RequestBody PortfolioRequestDto portfolioRequestDto) {
        return new BaseSuccessResponse<>(portfolioService.save(portfolioRequestDto));
    }

    @PostMapping("/upload-image/{id}")
    public BaseSuccessResponse<Void> uploadImage(@PathVariable Long id, @RequestBody ImageUploadRequest imageUploadRequest){
        portfolioService.uploadImage(id, imageUploadRequest.getImageUrl());
        return new BaseSuccessResponse<>(null);
    }

    @PutMapping("/{id}")
    public BaseSuccessResponse<PortfolioResponseDto> update(@PathVariable Long id, @RequestBody PortfolioRequestDto portfolioRequestDto){
        return new BaseSuccessResponse<>(portfolioService.update(id, portfolioRequestDto));
    }

    @DeleteMapping("/{id}")
    public BaseSuccessResponse<Void> delete(@PathVariable Long id){
        portfolioService.delete(id);
        return new BaseSuccessResponse<>(null);
    }

    @GetMapping("/matching")
    public BaseSuccessResponse<PortfolioResponseDto> matchingTest(@RequestParam Long userId){
        return new BaseSuccessResponse<>(portfolioService.getMatchingSeniorPortfolio(userId));
    }

    @PostMapping("/save-all")
    public BaseSuccessResponse<Void> saveAll(@RequestBody PortfolioSaveAllRequestDto portfolioSaveAllRequestDto){
        portfolioService.saveAll(portfolioSaveAllRequestDto);
        return new BaseSuccessResponse<>(null);
    }

    @PostMapping("/search")
    public BaseSuccessResponse<Slice<PortfolioSearchResponseDto>> search(@RequestBody PortfolioSearchRequestDto portfolioSearchRequestDto, @RequestParam(value = "cursor",required = false) Long cursor, Pageable pageable){
        return new BaseSuccessResponse<>(portfolioElasticService.searchPortfolios(portfolioSearchRequestDto, cursor, pageable));
    }
}