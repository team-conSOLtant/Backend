package com.consoltant.consoltant.util.api.global.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CountListResponse<T> {
    @JsonProperty("totalCount")
    private Long totalCount;
    @JsonProperty("list")
    private List<T> list;
}
