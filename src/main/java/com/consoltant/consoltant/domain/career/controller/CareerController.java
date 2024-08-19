package com.consoltant.consoltant.domain.career.controller;

import com.consoltant.consoltant.domain.career.dto.CareerRequestDto;
import com.consoltant.consoltant.domain.career.dto.CareerResponseDto;
import com.consoltant.consoltant.domain.career.service.CareerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/careers")
public class CareerController {

    private final CareerService careerService;

    @GetMapping("/{id}")
    public CareerResponseDto findById(@PathVariable Long id) {
        return careerService.findById(id);
    }

    @PostMapping
    public void save(@RequestBody CareerRequestDto careerRequestDto) {
        careerService.save(careerRequestDto);
    }
}
