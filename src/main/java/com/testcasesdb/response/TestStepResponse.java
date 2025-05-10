package com.testcasesdb.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.testcasesdb.dto.TestCaseDto;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.HashSet;
import java.util.Set;

/**
 * DTO for {@link com.testcasesdb.entity.TestStep}
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(toBuilder = true)
public class TestStepResponse {
    @JsonProperty("id")
    Integer id;
    @JsonProperty("description")
    String description;

    @JsonProperty("testCase")
    Set<TestCaseResponse> testCase = new HashSet<TestCaseResponse>();

    @JsonProperty("screenshot_name")
    String screenshotName;
}