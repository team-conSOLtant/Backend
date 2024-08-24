package com.consoltant.consoltant.domain.university.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UniversityResponseDto {

    private Long id;

    private String name;

    private String universityCode;

    private Boolean isDeleted;

}
