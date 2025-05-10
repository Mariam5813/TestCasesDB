package com.testcasesdb.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestCaseRequest {

    @JsonProperty("area_id")
    Integer area_id;
    @JsonProperty("result")
    String result;
    @JsonProperty("name")
    String name;
    @JsonProperty("testname")
    String testname;
    @JsonProperty("timeofexecution")
    Date timeofexecution;
    @JsonProperty("assumption")
    String assumption;
}
