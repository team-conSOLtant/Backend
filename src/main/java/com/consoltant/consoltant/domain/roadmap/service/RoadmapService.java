package com.consoltant.consoltant.domain.roadmap.service;

import com.consoltant.consoltant.domain.roadmap.repository.RoadmapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoadmapService {
    private final RoadmapRepository roadmapRepository;
}
