package com.consoltant.consoltant.domain.course.controller;

import com.consoltant.consoltant.domain.course.dto.CourseRequestDto;
import com.consoltant.consoltant.domain.course.dto.CourseResponseDto;
import com.consoltant.consoltant.domain.course.service.CourseService;
import com.consoltant.consoltant.util.base.BaseSuccessResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    // 유저 ID로 수강 리스트 조회
    @GetMapping
    public BaseSuccessResponse<List<CourseResponseDto>> findAllByUserId(@RequestParam Long userId){
        return new BaseSuccessResponse<>(courseService.findAllByUserId(userId));
    }

    //유저 ID로 선택된 수강 리스트 조회
    @GetMapping("/selected")
    public BaseSuccessResponse<List<CourseResponseDto>> findAllByUserIdAndIsSelected(@RequestParam Long userId){
        return new BaseSuccessResponse<>(courseService.findAllByUserIdAndIsSelectedTrue(userId));
    }

    //선택여부 수정
    @PutMapping("/{id}")
    public BaseSuccessResponse<CourseResponseDto> update(@PathVariable Long id, @RequestBody CourseRequestDto courseRequestDto) {
        return new BaseSuccessResponse<>(courseService.update(id,courseRequestDto));
    }

}