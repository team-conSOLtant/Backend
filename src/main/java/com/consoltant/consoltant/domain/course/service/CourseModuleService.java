package com.consoltant.consoltant.domain.course.service;

import com.consoltant.consoltant.domain.course.entity.Course;
import com.consoltant.consoltant.domain.course.repository.CourseRepository;
import com.consoltant.consoltant.domain.subject.entity.Subject;
import com.consoltant.consoltant.domain.user.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseModuleService {

    private final CourseRepository courseRepository;

    public Course findById(Long id){
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid award id: " + id));
    }

    // 유저 ID로 모든 Course 조회
    public List<Course> findAllByUserId(Long userId) {
        return courseRepository.findAllByUserId(userId);
    }

    // 유저 ID로 선택된 모든 Course 조회
    public List<Course> findAllByUserIdAndIsSelectedTrue(Long userId) {
        return courseRepository.findAllByUserIdAndIsSelectedTrue(userId);
    }

    public boolean existsByUserAndSubject(User user, Subject subject) {
        return courseRepository.existsByUserAndSubject(user, subject);
    }

    public Course save(Course course){
        return courseRepository.save(course);
    }
}