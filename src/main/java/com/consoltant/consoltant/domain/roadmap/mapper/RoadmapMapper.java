package com.consoltant.consoltant.domain.roadmap.mapper;

import com.consoltant.consoltant.domain.projectuser.mapper.ProjectUserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {ProjectUserMapper.class})
public interface RoadmapMapper {
}
