package com.consoltant.consoltant.domain.certification.repository;

import com.consoltant.consoltant.domain.certification.entity.Certification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
    List<Certification> findAllByPortfolioId(Long id);
}
