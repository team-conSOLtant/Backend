package com.consoltant.consoltant.domain.career.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class CareerResponseDto {

    private Long id;
    private String company;
    private String positionLevel;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isDeleted;

}
