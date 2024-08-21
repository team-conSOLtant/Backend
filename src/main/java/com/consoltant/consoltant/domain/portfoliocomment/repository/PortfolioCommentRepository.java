package com.consoltant.consoltant.domain.portfoliocomment.repository;

import com.consoltant.consoltant.domain.portfoliocomment.entity.PortfolioComment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioCommentRepository extends JpaRepository<PortfolioComment,Long> {
    List<PortfolioComment> findAllByPortfolioId(Long id);
}
