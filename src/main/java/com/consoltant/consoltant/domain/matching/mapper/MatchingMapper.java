package com.consoltant.consoltant.domain.matching.mapper;

import com.consoltant.consoltant.domain.matching.dto.MatchingRequestDto;
import com.consoltant.consoltant.domain.matching.entity.Matching;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MatchingMapper {

    Matching toMatching(MatchingRequestDto matchingRequestDto);
}
