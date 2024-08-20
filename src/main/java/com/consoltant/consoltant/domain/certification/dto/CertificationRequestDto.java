package com.consoltant.consoltant.domain.certification.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CertificationRequestDto {

    private Long portfolioId;
    private String title;
    private String issuingOrganization;
    private LocalDate acquisitionDate;

}
