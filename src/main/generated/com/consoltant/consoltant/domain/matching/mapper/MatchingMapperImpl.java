package com.consoltant.consoltant.domain.matching.mapper;

import com.consoltant.consoltant.domain.matching.dto.MatchingRequestDto;
import com.consoltant.consoltant.domain.matching.entity.Matching;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class MatchingMapperImpl implements MatchingMapper {

    @Override
    public Matching toMatching(MatchingRequestDto matchingRequestDto) {
        if ( matchingRequestDto == null ) {
            return null;
        }

        Matching.MatchingBuilder matching = Matching.builder();

        return matching.build();
    }
}
