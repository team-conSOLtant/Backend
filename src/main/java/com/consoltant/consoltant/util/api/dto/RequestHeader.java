package com.consoltant.consoltant.util.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestHeader {
    private String responseCode;
    private String responseMessage;
    private String apiName;
    private String transmissionDate;
    private String transmissionTime;


}
