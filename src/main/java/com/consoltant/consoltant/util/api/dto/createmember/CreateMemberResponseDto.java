package com.consoltant.consoltant.util.api.dto.createmember;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateMemberResponseDto {
    private String userId;
    private String userName;
    private String institutionCode;
    private String userKey;
    private String created;
    private String modified;
}
