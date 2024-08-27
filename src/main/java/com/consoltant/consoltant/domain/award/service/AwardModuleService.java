package com.consoltant.consoltant.domain.award.service;

import com.consoltant.consoltant.domain.award.entity.Award;
import com.consoltant.consoltant.domain.award.repository.AwardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AwardModuleService {

    private final AwardRepository awardRepository;

    public Award findById(Long id) {
        return awardRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Invalid award id: " + id));
    }

    public List<Award> findAllByPortfolioId(Long portfolioId) {
        return awardRepository.findAllByPortfolioId(portfolioId);
    }

    public Integer countAllByPortfolioId(Long portfolioId) {
        return awardRepository.findAllByPortfolioId(portfolioId).size();
    }

    public Award save(Award award) {
        return awardRepository.save(award);
    }

    public void delete(Long id) {
        awardRepository.deleteById(id);
    }

    public void deleteAllByPortfolioId(Long portfolioId){
        awardRepository.deleteAllByPortfolioId(portfolioId);
    }
}
