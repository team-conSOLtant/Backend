package com.consoltant.consoltant.domain.projectuser.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectUserRequestDto {

    private Long projectId;
    private Long userId;
    private String name;

}
