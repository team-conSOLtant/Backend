package com.consoltant.consoltant.domain.portfolio.repository;

import com.consoltant.consoltant.domain.portfolio.entity.PortfolioDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioElasticRepository extends ElasticsearchRepository<PortfolioDocument, String> {

    Page<PortfolioDocument> findAllByAllContentContaining(String keyword, Pageable pageable);
}
