package com.consoltant.consoltant.util.api.global.response;

import com.consoltant.consoltant.util.api.global.header.ResponseHeader;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class RECListResponse<T> {
    @JsonProperty("Header")
    private ResponseHeader HEADER;
    @JsonProperty("REC")
    private List<T> REC;
}
