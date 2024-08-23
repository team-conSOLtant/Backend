package com.consoltant.consoltant.domain.matching.service;

import com.consoltant.consoltant.domain.matching.entity.Matching;
import com.consoltant.consoltant.domain.matching.repository.MatchingRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchingModuleService {

    private final MatchingRepository matchingRepository;

    public Set<Long> findAllByUserId(Long userId){
        return matchingRepository.findPortfolioIdsByUserId(userId);
    }

    public void save(Matching matching){
        matchingRepository.save(matching);
    }

}
