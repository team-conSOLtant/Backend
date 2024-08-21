package com.consoltant.consoltant.domain.subject.repository;

import com.consoltant.consoltant.domain.subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
