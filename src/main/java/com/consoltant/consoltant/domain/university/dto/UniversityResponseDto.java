package com.consoltant.consoltant.domain.university.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UniversityResponseDto {

    private Long id;

    private String name;

    private String universityCode;

    private Boolean isDeleted;

}
