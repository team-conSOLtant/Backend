package com.consoltant.consoltant.domain.follow.mapper;

import com.consoltant.consoltant.domain.follow.dto.FollowRequestDto;
import com.consoltant.consoltant.domain.follow.dto.FollowResponseDto;
import com.consoltant.consoltant.domain.follow.entity.Follow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FollowMapper {

    Follow toFollow(FollowRequestDto followRequestDto);

    @Mapping(source = "portfolio.id", target = "portfolioId")
    @Mapping(source = "user.id", target = "userId")
    FollowResponseDto toFollowResponseDto(Follow follow);

}
