package com.consoltant.consoltant.domain.roadmap.dto;

import com.consoltant.consoltant.util.constant.FinanceKeyword;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo {
    private String name;
    private String universityName;
    private String birthDate;
    private String corporateName;
    private Integer salary;
    private String financeKeyword;
    private Long startAsset;
    private Long presentAsset;
    private Integer period;
}
