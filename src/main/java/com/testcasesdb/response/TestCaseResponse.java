package com.testcasesdb.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestCaseResponse {
    @JsonProperty("id")
    Integer id;
    @JsonProperty("area")
    TestAreaResponse area;
    @JsonProperty("result")
    String result;
    @JsonProperty("name")
    String name;
    @JsonProperty("testname")
    String testname;
    @JsonProperty("timeofexecution")
    Instant timeofexecution;
    @JsonProperty("assumption")
    String assumption;
    /*@JsonProperty("testStep")
    Set<TestStepResponse> testStep;*/
    @JsonProperty("testStep")
    Set<TestStepResponse> testStep = new HashSet<TestStepResponse>();
}
