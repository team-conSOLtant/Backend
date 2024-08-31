package com.consoltant.consoltant.domain.bestroadmap.service;

import com.consoltant.consoltant.domain.bestroadmap.dto.BestRoadmapResponseDto;
import com.consoltant.consoltant.domain.bestroadmap.mapper.BestRoadmapMapper;
import com.consoltant.consoltant.domain.bestroadmap.repository.BestRoadmapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BestRoadmapModuleService {
    private final BestRoadmapRepository bestRoadmapRepository;
    private final BestRoadmapMapper bestRoadmapMapper;

    public List<BestRoadmapResponseDto> findAll(){
        return bestRoadmapMapper.toBestRoadmapResponseDtoList(bestRoadmapRepository.findAll());
    }

    public Integer findUserAge(Long id){
        return bestRoadmapRepository.findByUserId(id).get().getAge();
    }
}
