package com.consoltant.consoltant.domain.certification.mapper;

import com.consoltant.consoltant.domain.certification.dto.CertificationRequestDto;
import com.consoltant.consoltant.domain.certification.dto.CertificationResponseDto;
import com.consoltant.consoltant.domain.certification.entity.Certification;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CertificationMapper {

    Certification toCertification(CertificationRequestDto certificationRequestDto);

    CertificationResponseDto toCertificationResponseDto(Certification certification);

}