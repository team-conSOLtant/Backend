package com.consoltant.consoltant.domain.follow.mapper;

import com.consoltant.consoltant.domain.follow.dto.FollowRequestDto;
import com.consoltant.consoltant.domain.follow.dto.FollowResponseDto;
import com.consoltant.consoltant.domain.follow.entity.Follow;
import com.consoltant.consoltant.domain.portfolio.entity.Portfolio;
import com.consoltant.consoltant.domain.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class FollowMapperImpl implements FollowMapper {

    @Override
    public Follow toFollow(FollowRequestDto followRequestDto) {
        if ( followRequestDto == null ) {
            return null;
        }

        Follow.FollowBuilder follow = Follow.builder();

        return follow.build();
    }

    @Override
    public FollowResponseDto toFollowResponseDto(Follow follow) {
        if ( follow == null ) {
            return null;
        }

        FollowResponseDto followResponseDto = new FollowResponseDto();

        followResponseDto.setPortfolioId( followPortfolioId( follow ) );
        followResponseDto.setUserId( followUserId( follow ) );
        followResponseDto.setId( follow.getId() );
        followResponseDto.setIsDeleted( follow.getIsDeleted() );

        return followResponseDto;
    }

    private Long followPortfolioId(Follow follow) {
        if ( follow == null ) {
            return null;
        }
        Portfolio portfolio = follow.getPortfolio();
        if ( portfolio == null ) {
            return null;
        }
        Long id = portfolio.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long followUserId(Follow follow) {
        if ( follow == null ) {
            return null;
        }
        User user = follow.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
