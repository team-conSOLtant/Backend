package com.consoltant.consoltant.domain.portfoliocomment.service;

import com.consoltant.consoltant.domain.portfoliocomment.entity.PortfolioComment;
import com.consoltant.consoltant.domain.portfoliocomment.repository.PortfolioCommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioCommentModuleService {
    private final PortfolioCommentRepository portfolioCommentRepository;

    // PortfolioComment ID로 단일 조회
    public PortfolioComment findById(Long id) {
        return portfolioCommentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid PortfolioComment ID"));
    }

    // 포트폴리오 ID로 모든 PortfolioComment 조회
    public List<PortfolioComment> findAllByPortfolioId(Long portfolioId) {
        return portfolioCommentRepository.findAllByPortfolioId(portfolioId);
    }

    // PortfolioComment 저장
    public PortfolioComment save(PortfolioComment portfolioComment) {
        return portfolioCommentRepository.save(portfolioComment);
    }

    // PortfolioComment 삭제
    public void delete(Long id) {
        portfolioCommentRepository.deleteById(id);
    }
}
