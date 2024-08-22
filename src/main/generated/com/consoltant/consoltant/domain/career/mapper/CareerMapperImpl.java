package com.consoltant.consoltant.domain.career.mapper;

import com.consoltant.consoltant.domain.career.dto.CareerRequestDto;
import com.consoltant.consoltant.domain.career.dto.CareerResponseDto;
import com.consoltant.consoltant.domain.career.entity.Career;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-21T17:13:12+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class CareerMapperImpl implements CareerMapper {

    @Override
    public Career toCareer(CareerRequestDto careerRequestDto) {
        if ( careerRequestDto == null ) {
            return null;
        }

        Career.CareerBuilder career = Career.builder();

        career.company( careerRequestDto.getCompany() );
        career.positionLevel( careerRequestDto.getPositionLevel() );
        career.startDate( careerRequestDto.getStartDate() );
        career.endDate( careerRequestDto.getEndDate() );

        return career.build();
    }

    @Override
    public CareerResponseDto toCareerResponseDto(Career career) {
        if ( career == null ) {
            return null;
        }

        CareerResponseDto careerResponseDto = new CareerResponseDto();

        careerResponseDto.setId( career.getId() );
        careerResponseDto.setCompany( career.getCompany() );
        careerResponseDto.setPositionLevel( career.getPositionLevel() );
        careerResponseDto.setStartDate( career.getStartDate() );
        careerResponseDto.setEndDate( career.getEndDate() );

        return careerResponseDto;
    }
}
