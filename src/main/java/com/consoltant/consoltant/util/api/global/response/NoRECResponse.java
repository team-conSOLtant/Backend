package com.consoltant.consoltant.util.api.global.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class NoRECResponse<T> {
    @JsonProperty("REC")
    private T REC;
}
