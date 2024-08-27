package com.consoltant.consoltant.domain.portfolio.service;

import com.consoltant.consoltant.domain.portfolio.dto.PortfolioSearchRequestDto;
import com.consoltant.consoltant.domain.portfolio.entity.PortfolioDocument;
import com.consoltant.consoltant.domain.portfolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioElasticService {
    private PortfolioRepository portfolioRepository;
    private final ElasticsearchOperations operations;


    public void searchPortfolios(PortfolioSearchRequestDto request, Long cursor, Pageable pageable) {
        Query query = NativeQuery.builder()
                .withQuery(q -> q
                        .bool(b -> {
                            if (request.getKeyword() != null && !request.getKeyword().isEmpty()) {
                                b.must(m -> m.match(mm -> mm.field("allContent").query(request.getKeyword())));
                            }
                            if (request.getIsEmployed() != null) {
                                b.must(m -> m.term(t -> t.field("isEmployed").value(request.getIsEmployed())));
                            }
                            if(cursor != null) {
                                b.must(m -> m.range(
                                        t -> t.field("cursor").from(cursor.toString())));
                            }
                            return b;
                        })
                )
                .withPageable(pageable)  // 페이징 처리
                .build();

        SearchHits<PortfolioDocument> hits = operations.search(query, PortfolioDocument.class);
        System.out.println(hits.getTotalHits());
//        return hits;
    }
}
