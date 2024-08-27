package com.consoltant.consoltant.domain.recommend.mapper;

import com.consoltant.consoltant.domain.recommend.dto.RecommendRequestDto;
import com.consoltant.consoltant.domain.recommend.dto.RecommendResponseDto;
import com.consoltant.consoltant.domain.recommend.entity.Recommend;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class RecommendMapperImpl implements RecommendMapper {

    @Override
    public RecommendResponseDto toResponseDto(Recommend recommend) {
        if ( recommend == null ) {
            return null;
        }

        RecommendResponseDto recommendResponseDto = new RecommendResponseDto();

        recommendResponseDto.setId( recommend.getId() );
        recommendResponseDto.setProductType( recommend.getProductType() );
        recommendResponseDto.setAccountTypeUniqueNo( recommend.getAccountTypeUniqueNo() );
        recommendResponseDto.setJourneyType( recommend.getJourneyType() );
        recommendResponseDto.setStartDate( recommend.getStartDate() );
        recommendResponseDto.setEndDate( recommend.getEndDate() );

        return recommendResponseDto;
    }

    @Override
    public Recommend toRecommend(RecommendRequestDto requestDto) {
        if ( requestDto == null ) {
            return null;
        }

        Recommend.RecommendBuilder recommend = Recommend.builder();

        recommend.productType( requestDto.getProductType() );
        recommend.accountTypeUniqueNo( requestDto.getAccountTypeUniqueNo() );
        recommend.journeyType( requestDto.getJourneyType() );
        recommend.startDate( requestDto.getStartDate() );
        recommend.endDate( requestDto.getEndDate() );

        return recommend.build();
    }
}
