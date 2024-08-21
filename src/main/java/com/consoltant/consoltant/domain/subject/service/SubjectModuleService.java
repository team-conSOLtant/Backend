package com.consoltant.consoltant.domain.subject.service;

import com.consoltant.consoltant.domain.subject.entity.Subject;
import com.consoltant.consoltant.domain.subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectModuleService {
    private final SubjectRepository subjectRepository;

    // Subject ID로 단일 조회
    public Subject findById(Long id) {
        return subjectRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Subject ID"));
    }

    // Subject 저장
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    // Subject 삭제
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }
}
