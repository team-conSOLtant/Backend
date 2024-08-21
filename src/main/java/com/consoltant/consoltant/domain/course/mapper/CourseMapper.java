package com.consoltant.consoltant.domain.course.mapper;

import com.consoltant.consoltant.domain.course.dto.CourseRequestDto;
import com.consoltant.consoltant.domain.course.dto.CourseResponseDto;
import com.consoltant.consoltant.domain.course.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {

    Course toCourse(CourseRequestDto courseRequestDto);

    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "user.id", target = "userId")
    CourseResponseDto toCourseResponseDto(Course course);

}