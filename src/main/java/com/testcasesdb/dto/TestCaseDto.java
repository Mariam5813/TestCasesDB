package com.testcasesdb.dto;


import java.util.Date;
import java.util.Set;

import lombok.*;


/**
 *
 */
@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestCaseDto {
	Integer id;
    TestAreaDto testArea;
    Set<TestStepDto> testSteps;
    String name;
    String result;
    String testname;
    Date timeofexecution;
    String assumption;
}
