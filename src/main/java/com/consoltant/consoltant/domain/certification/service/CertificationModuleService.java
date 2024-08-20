package com.consoltant.consoltant.domain.certification.service;

import com.consoltant.consoltant.domain.certification.entity.Certification;
import com.consoltant.consoltant.domain.certification.repository.CertificationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificationModuleService {
    private final CertificationRepository certificationRepository;

    public Certification findById(Long id) {
        return certificationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid Certification ID"));
    }

    public List<Certification> findAllByPortfolioId(Long portfolioId) {
        return certificationRepository.findAllByPortfolioId(portfolioId);
    }

    public Certification save(Certification certification) {
        return certificationRepository.save(certification);
    }

    public void delete(Long id) {
        certificationRepository.deleteById(id);
    }
}