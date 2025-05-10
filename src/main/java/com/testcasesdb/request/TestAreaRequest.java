package com.testcasesdb.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TestAreaRequest {
    @JsonProperty("description")
    private String description;
}
