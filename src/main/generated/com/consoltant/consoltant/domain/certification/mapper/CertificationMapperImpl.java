package com.consoltant.consoltant.domain.certification.mapper;

import com.consoltant.consoltant.domain.certification.dto.CertificationRequestDto;
import com.consoltant.consoltant.domain.certification.dto.CertificationResponseDto;
import com.consoltant.consoltant.domain.certification.entity.Certification;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T10:59:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
@Component
public class CertificationMapperImpl implements CertificationMapper {

    @Override
    public Certification toCertification(CertificationRequestDto certificationRequestDto) {
        if ( certificationRequestDto == null ) {
            return null;
        }

        Certification.CertificationBuilder certification = Certification.builder();

        certification.title( certificationRequestDto.getTitle() );
        certification.acquisitionDate( certificationRequestDto.getAcquisitionDate() );
        certification.issuingOrganization( certificationRequestDto.getIssuingOrganization() );

        return certification.build();
    }

    @Override
    public CertificationResponseDto toCertificationResponseDto(Certification certification) {
        if ( certification == null ) {
            return null;
        }

        CertificationResponseDto certificationResponseDto = new CertificationResponseDto();

        certificationResponseDto.setId( certification.getId() );
        certificationResponseDto.setTitle( certification.getTitle() );
        certificationResponseDto.setIssuingOrganization( certification.getIssuingOrganization() );
        certificationResponseDto.setAcquisitionDate( certification.getAcquisitionDate() );
        certificationResponseDto.setIsDeleted( certification.getIsDeleted() );

        return certificationResponseDto;
    }
}
