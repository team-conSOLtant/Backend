package com.consoltant.consoltant.domain.career.mapper;

import com.consoltant.consoltant.domain.career.dto.CareerRequestDto;
import com.consoltant.consoltant.domain.career.dto.CareerResponseDto;
import com.consoltant.consoltant.domain.career.entity.Career;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CareerMapper {

    Career toCareer(CareerRequestDto careerRequestDto);

    CareerResponseDto toCareerResponseDto(Career career);

}
