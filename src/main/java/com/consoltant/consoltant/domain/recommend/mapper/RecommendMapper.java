package com.consoltant.consoltant.domain.recommend.mapper;

import com.consoltant.consoltant.domain.projectuser.mapper.ProjectUserMapper;
import com.consoltant.consoltant.domain.recommend.dto.RecommendResponseDto;
import com.consoltant.consoltant.domain.recommend.entity.Recommend;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ProjectUserMapper.class})
public interface RecommendMapper {
    RecommendResponseDto toResponseDto(Recommend recommend);
}
