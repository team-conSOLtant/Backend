package com.consoltant.consoltant.domain.career.controller;

import com.consoltant.consoltant.domain.career.dto.CareerRequestDto;
import com.consoltant.consoltant.domain.career.dto.CareerResponseDto;
import com.consoltant.consoltant.domain.career.service.CareerService;
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
@RequestMapping("/careers")
public class CareerController {

    private final CareerService careerService;

    //단일 조회
    @GetMapping("/{id}")
    public CareerResponseDto findById(@PathVariable Long id) {
        return careerService.findById(id);
    }

    //포폴 아이디로 경력 리스트 조회
    @GetMapping
    public List<CareerResponseDto> findAllByPortfolioId(@RequestParam Long portfolioId){
        return careerService.findAllByPortfolioId(portfolioId);
    }

    //경력 등록
    @PostMapping
    public CareerResponseDto save(@RequestBody CareerRequestDto careerRequestDto) {
        return careerService.save(careerRequestDto);
    }

    //경력 수정
    @PutMapping("/{id}")
    public CareerResponseDto update(@PathVariable Long id, @RequestBody CareerRequestDto careerRequestDto){
        return careerService.update(id, careerRequestDto);
    }

    //경력 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        careerService.delete(id);
    }
}
