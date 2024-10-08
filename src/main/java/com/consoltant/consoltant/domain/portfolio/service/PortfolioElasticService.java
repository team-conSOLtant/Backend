package com.consoltant.consoltant.domain.portfolio.service;

import com.consoltant.consoltant.domain.award.service.AwardModuleService;
import com.consoltant.consoltant.domain.career.entity.Career;
import com.consoltant.consoltant.domain.career.service.CareerModuleService;
import com.consoltant.consoltant.domain.certification.service.CertificationModuleService;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioSearchRequestDto;
import com.consoltant.consoltant.domain.portfolio.dto.PortfolioSearchResponseDto;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.portfolio.entity.PortfolioDocument;
import com.consoltant.consoltant.domain.portfolio.repository.PortfolioRepository;
import com.consoltant.consoltant.domain.project.service.ProjectModuleService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PortfolioElasticService {
    private final PortfolioRepository portfolioRepository;
    private final ElasticsearchOperations operations;
    private final AwardModuleService awardModuleService;
    private final CertificationModuleService certificationModuleService;
    private final ProjectModuleService projectModuleService;
    private final CareerModuleService careerModuleService;

    @Transactional(readOnly = true)
    public Slice<PortfolioSearchResponseDto> searchPortfolios(PortfolioSearchRequestDto request, Long cursor, Pageable pageable) {
        IndexCoordinates index = IndexCoordinates.of("portfolio");

        Criteria criteria = new Criteria("allContent");

        if (request.getKeyword() != null && !request.getKeyword().isEmpty()) {
            criteria = criteria.matches(request.getKeyword());
        }else {
            criteria = new Criteria(); // 전체 검색
        }

        if (request.getIsEmployed() != null) {
            criteria = criteria.and("isEmployed").is(request.getIsEmployed());
        }

        if (request.getMinGpa() != null && request.getMaxGpa() != null) {
            criteria = criteria.and("totalGpa").between(request.getMinGpa(), request.getMaxGpa());
        }

        if(cursor != null) {
            criteria = criteria.and("portfolioId").lessThan(cursor);
        }else{
            criteria = criteria.and("portfolioId").lessThan(Integer.MAX_VALUE);
        }

        Sort sort = Sort.by(Sort.Order.desc("portfolioId"));

        Pageable pageableWithExtra = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize() + 1);

        Query query = new CriteriaQuery(criteria).setPageable(pageableWithExtra).addSort(sort);

        // Elasticsearch에서 검색 실행
        SearchHits<PortfolioDocument> hits = operations.search(query, PortfolioDocument.class, index);

        List<Long> documents = hits.getSearchHits().stream()
                .map(hit -> hit.getContent().getPortfolioId())
                .collect(Collectors.toList());

        boolean hasNext = documents.size() > pageable.getPageSize();

        if (hasNext) {
            documents.remove(documents.size() - 1);
        }

        List<PortfolioSearchResponseDto> responses = new ArrayList<>();

        for(long document : documents) {
            Portfolio portfolio = portfolioRepository.findById(document)
                    .orElseThrow(() -> new RuntimeException("portfolio not found"));

            List<Career> careerList = careerModuleService.findAllByPortfolioId(portfolio.getId()).stream()
                .sorted(Comparator.comparing(Career::getStartDate).reversed())
                .toList();
            String careerTitle = careerList.isEmpty() ? "없음" : careerList.get(0).getCompany();

            PortfolioSearchResponseDto responseDto = PortfolioSearchResponseDto.builder()
                    .userName(portfolio.getUser().getName())
                    .portfolioId(portfolio.getId())
                    .universityName(portfolio.getUser().getUniversity().getName())
                    .major(portfolio.getUser().getMajor())
                    .totalGpa(portfolio.getTotalGpa())
                    .majorGpa(portfolio.getMajorGpa())
                    .job(portfolio.getJob())
                    .myKeyword(portfolio.getMyKeyword())
                    .careerTitle(careerTitle)
                    .awardCount(awardModuleService.countAllByPortfolioId(portfolio.getId()))
                    .certificationCount(certificationModuleService.countAllByPortfolioId(portfolio.getId()))
                    .projectCount(projectModuleService.countAllByPortfolioId(portfolio.getId()))
                    .build();

            responses.add(responseDto);
        }

         return new SliceImpl<>(responses, pageable, hasNext);
    }

}
