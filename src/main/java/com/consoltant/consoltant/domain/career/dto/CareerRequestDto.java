package com.consoltant.consoltant.domain.career.dto;
import java.time.LocalDate;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CareerRequestDto {

    private Long portfolioId;
    private String company;
    private String positionLevel;
    private LocalDate startDate;
    private LocalDate endDate;

}
