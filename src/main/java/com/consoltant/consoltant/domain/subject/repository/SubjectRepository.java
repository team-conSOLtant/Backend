package com.consoltant.consoltant.domain.subject.repository;

import com.consoltant.consoltant.domain.subject.entity.Subject;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findBySubjectNumber(String subjectNumber);
}
