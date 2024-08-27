package com.consoltant.consoltant.domain.course.mapper;

import com.consoltant.consoltant.domain.course.dto.CourseRequestDto;
import com.consoltant.consoltant.domain.course.dto.CourseResponseDto;
import com.consoltant.consoltant.domain.course.entity.Course;
import com.consoltant.consoltant.domain.subject.entity.Subject;
import com.consoltant.consoltant.domain.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public Course toCourse(CourseRequestDto courseRequestDto) {
        if ( courseRequestDto == null ) {
            return null;
        }

        Course.CourseBuilder course = Course.builder();

        course.subjectName( courseRequestDto.getSubjectName() );
        course.grade( courseRequestDto.getGrade() );
        course.isSelected( courseRequestDto.getIsSelected() );

        return course.build();
    }

    @Override
    public CourseResponseDto toCourseResponseDto(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseResponseDto courseResponseDto = new CourseResponseDto();

        courseResponseDto.setSubjectId( courseSubjectId( course ) );
        courseResponseDto.setUserId( courseUserId( course ) );
        courseResponseDto.setId( course.getId() );
        courseResponseDto.setGrade( course.getGrade() );
        courseResponseDto.setIsSelected( course.getIsSelected() );
        courseResponseDto.setIsDeleted( course.getIsDeleted() );

        return courseResponseDto;
    }

    private Long courseSubjectId(Course course) {
        if ( course == null ) {
            return null;
        }
        Subject subject = course.getSubject();
        if ( subject == null ) {
            return null;
        }
        Long id = subject.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long courseUserId(Course course) {
        if ( course == null ) {
            return null;
        }
        User user = course.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
