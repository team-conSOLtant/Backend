package com.consoltant.consoltant.domain.journey.dto;

import com.consoltant.consoltant.util.constant.JourneyType;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JourneyRequestDto {

    private Long userId;
    private JourneyType journeyType;
    private Long balance;
    private LocalDate startDate;
    private LocalDate endDate;
    private int age;

}
