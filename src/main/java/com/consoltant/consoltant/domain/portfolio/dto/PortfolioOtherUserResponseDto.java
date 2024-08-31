package com.consoltant.consoltant.domain.portfolio.dto;

import com.consoltant.consoltant.domain.university.dto.UniversityResponseDto;
import com.consoltant.consoltant.util.constant.FinanceKeyword;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioOtherUserResponseDto {

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

    private String userName;
    private String phoneNumber;
    private String birthDate;
    private String major;
    private UniversityResponseDto university;

    public void updateFromDto(PortfolioResponseDto dto) {
        this.id = dto.getId();
        this.totalGpa = dto.getTotalGpa();
        this.majorGpa = dto.getMajorGpa();
        this.financeKeyword = dto.getFinanceKeyword();
        this.myKeyword = dto.getMyKeyword();
        this.email = dto.getEmail();
        this.job = dto.getJob();
        this.imageUrl = dto.getImageUrl();
        this.description = dto.getDescription();
        this.backgroundColor = dto.getBackgroundColor();
        this.isMine = dto.getIsMine();
        this.isDeleted = dto.getIsDeleted();
    }

}
