package com.consoltant.consoltant.domain.portfolio.dto;

import com.consoltant.consoltant.util.constant.FinanceKeyword;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PortfolioResponseDto {

    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Double totalGpa;
    @JsonSerialize(using = ToStringSerializer.class)
    private Double majorGpa;
    private FinanceKeyword financeKeyword;
    private String myKeyword;
    private String email;
    private String job;
    private String imageUrl;
    private String description;
    private String backgroundColor;
    private Boolean isMine;
    private Boolean isDeleted;

}
