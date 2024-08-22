package com.consoltant.consoltant.domain.award.mapper;

import com.consoltant.consoltant.domain.award.dto.AwardRequestDto;
import com.consoltant.consoltant.domain.award.dto.AwardResponseDto;
import com.consoltant.consoltant.domain.award.entity.Award;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-21T17:13:12+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class AwardMapperImpl implements AwardMapper {

    @Override
    public Award toAward(AwardRequestDto awardRequestDto) {
        if ( awardRequestDto == null ) {
            return null;
        }

        Award.AwardBuilder award = Award.builder();

        award.title( awardRequestDto.getTitle() );
        award.acquisitionDate( awardRequestDto.getAcquisitionDate() );
        award.awardOrganization( awardRequestDto.getAwardOrganization() );
        award.awardGrade( awardRequestDto.getAwardGrade() );
        award.content( awardRequestDto.getContent() );

        return award.build();
    }

    @Override
    public AwardResponseDto toAwardResponseDto(Award award) {
        if ( award == null ) {
            return null;
        }

        AwardResponseDto awardResponseDto = new AwardResponseDto();

        awardResponseDto.setId( award.getId() );
        awardResponseDto.setTitle( award.getTitle() );
        awardResponseDto.setAcquisitionDate( award.getAcquisitionDate() );
        awardResponseDto.setAwardOrganization( award.getAwardOrganization() );
        awardResponseDto.setAwardGrade( award.getAwardGrade() );
        awardResponseDto.setContent( award.getContent() );
        awardResponseDto.setIsDeleted( award.getIsDeleted() );

        return awardResponseDto;
    }
}
