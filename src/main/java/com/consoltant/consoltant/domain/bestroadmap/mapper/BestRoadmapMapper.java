package com.consoltant.consoltant.domain.bestroadmap.mapper;

import com.consoltant.consoltant.domain.bestroadmap.dto.BestRoadmapResponseDto;
import com.consoltant.consoltant.domain.bestroadmap.entity.BestRoadmap;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BestRoadmapMapper {
    BestRoadmapResponseDto toBestRoadmapResponseDto(BestRoadmap bestRoadmap);
    List<BestRoadmapResponseDto> toBestRoadmapResponseDtoList(List<BestRoadmap> bestRoadmap);
}
