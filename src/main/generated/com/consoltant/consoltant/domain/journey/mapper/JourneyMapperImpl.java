package com.consoltant.consoltant.domain.journey.mapper;

import com.consoltant.consoltant.domain.journey.dto.JourneyRequestDto;
import com.consoltant.consoltant.domain.journey.dto.JourneyResponseDto;
import com.consoltant.consoltant.domain.journey.entity.Journey;
import com.consoltant.consoltant.domain.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class JourneyMapperImpl implements JourneyMapper {

    @Override
    public Journey toJourney(JourneyRequestDto journeyRequestDto) {
        if ( journeyRequestDto == null ) {
            return null;
        }

        Journey.JourneyBuilder journey = Journey.builder();

        journey.journeyType( journeyRequestDto.getJourneyType() );
        journey.productType( journeyRequestDto.getProductType() );
        journey.startDate( journeyRequestDto.getStartDate() );
        journey.endDate( journeyRequestDto.getEndDate() );
        journey.balance( journeyRequestDto.getBalance() );
        if ( journeyRequestDto.getAge() != null ) {
            journey.age( journeyRequestDto.getAge() );
        }

        return journey.build();
    }

    @Override
    public JourneyResponseDto toJourneyResponseDto(Journey journey) {
        if ( journey == null ) {
            return null;
        }

        JourneyResponseDto journeyResponseDto = new JourneyResponseDto();

        journeyResponseDto.setUserId( journeyUserId( journey ) );
        journeyResponseDto.setId( journey.getId() );
        journeyResponseDto.setJourneyType( journey.getJourneyType() );
        journeyResponseDto.setProductType( journey.getProductType() );
        journeyResponseDto.setBalance( journey.getBalance() );
        journeyResponseDto.setStartDate( journey.getStartDate() );
        journeyResponseDto.setEndDate( journey.getEndDate() );
        journeyResponseDto.setAge( journey.getAge() );
        journeyResponseDto.setIsDeleted( journey.getIsDeleted() );

        return journeyResponseDto;
    }

    private Long journeyUserId(Journey journey) {
        if ( journey == null ) {
            return null;
        }
        User user = journey.getUser();
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
