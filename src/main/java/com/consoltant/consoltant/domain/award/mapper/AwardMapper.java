package com.consoltant.consoltant.domain.award.mapper;

import com.consoltant.consoltant.domain.award.dto.AwardRequestDto;
import com.consoltant.consoltant.domain.award.dto.AwardResponseDto;
import com.consoltant.consoltant.domain.award.entity.Award;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AwardMapper {

    Award toAward(AwardRequestDto awardRequestDto);
    AwardResponseDto toAwardResponseDto(Award award);

}
