package com.consoltant.consoltant.domain.course.service;

import com.consoltant.consoltant.domain.course.dto.CourseRequestDto;
import com.consoltant.consoltant.domain.course.dto.CourseResponseDto;
import com.consoltant.consoltant.domain.course.entity.Course;
import com.consoltant.consoltant.domain.course.mapper.CourseMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseModuleService courseModuleService;
    private final CourseMapper courseMapper;


    // 유저 ID로 수강 리스트 조회
    public List<CourseResponseDto> findAllByUserId(Long userId){
        return courseModuleService.findAllByUserId(userId).stream()
            .map(courseMapper::toCourseResponseDto)
            .toList();
    }

    // 유저 ID로 선택한 수강 리스트 조회
    public List<CourseResponseDto> findAllByUserIdAndIsSelectedTrue(Long userId){
        return courseModuleService.findAllByUserIdAndIsSelectedTrue(userId).stream()
            .map(courseMapper::toCourseResponseDto)
            .toList();
    }

    // 선택여부 변경
    public CourseResponseDto update(Long id, CourseRequestDto courseRequestDto){
        Course course = courseModuleService.findById(id);
        course.update(courseRequestDto);
        courseModuleService.save(course);
        return courseMapper.toCourseResponseDto(course);
    }
}