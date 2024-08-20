package com.consoltant.consoltant.domain.certification.controller;

import com.consoltant.consoltant.domain.certification.dto.CertificationRequestDto;
import com.consoltant.consoltant.domain.certification.dto.CertificationResponseDto;
import com.consoltant.consoltant.domain.certification.service.CertificationService;
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
@RequestMapping("/certifications")
public class CertificationController {

    private final CertificationService certificationService;

    @GetMapping("/{id}")
    public BaseSuccessResponse<CertificationResponseDto> findById(@PathVariable Long id) {
        return new BaseSuccessResponse<>(certificationService.findById(id));
    }

    @GetMapping
    public BaseSuccessResponse<List<CertificationResponseDto>> findAllByPortfolioId(@RequestParam Long portfolioId){
        return new BaseSuccessResponse<>(certificationService.findAllByPortfolioId(portfolioId));
    }

    @PostMapping
    public BaseSuccessResponse<CertificationResponseDto> save(@RequestBody CertificationRequestDto certificationRequestDto) {
        return new BaseSuccessResponse<>(certificationService.save(certificationRequestDto));
    }

    @PutMapping("/{id}")
    public BaseSuccessResponse<CertificationResponseDto> update(@PathVariable Long id, @RequestBody CertificationRequestDto certificationRequestDto){
        return new BaseSuccessResponse<>(certificationService.update(id, certificationRequestDto));
    }

    @DeleteMapping("/{id}")
    public BaseSuccessResponse<Void> delete(@PathVariable Long id){
        certificationService.delete(id);
        return new BaseSuccessResponse<>(null);
    }
}