package com.consoltant.consoltant.domain.course.repository;

import com.consoltant.consoltant.domain.course.entity.Course;
import com.consoltant.consoltant.domain.subject.entity.Subject;
import com.consoltant.consoltant.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByUserId(Long id);
    List<Course> findAllByUserIdAndIsSelectedTrue(Long id);
    boolean existsByUserAndSubject(User user, Subject subject);

}
