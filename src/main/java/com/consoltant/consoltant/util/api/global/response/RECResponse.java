package com.consoltant.consoltant.util.api.global.response;

import com.consoltant.consoltant.util.api.global.header.ResponseHeader;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class RECResponse<T> {
    @JsonProperty("Header")
    private ResponseHeader Header;
    @JsonProperty("REC")
    private T REC;
}
