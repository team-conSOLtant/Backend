package com.consoltant.consoltant.domain.journey.mapper;

import com.consoltant.consoltant.domain.journey.dto.JourneyRequestDto;
import com.consoltant.consoltant.domain.journey.dto.JourneyResponseDto;
import com.consoltant.consoltant.domain.journey.entity.Journey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface JourneyMapper {

    Journey toJourney(JourneyRequestDto journeyRequestDto);

    @Mapping(source = "user.id", target = "userId")
    JourneyResponseDto toJourneyResponseDto(Journey journey);

}