package com.consoltant.consoltant.domain.award.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AwardResponseDto {

    private Long id;
    private String title;
    private LocalDate acquisitionDate;
    private String awardOrganization;
    private String awardGrade;
    private String content;
    private Boolean isDeleted;

}
