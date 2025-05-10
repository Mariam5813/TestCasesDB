package com.testcasesdb.mapper;


import com.testcasesdb.entity.TestArea;
import com.testcasesdb.entity.TestCase;
import com.testcasesdb.entity.TestStep;
import com.testcasesdb.request.TestCaseRequest;
import com.testcasesdb.response.TestAreaResponse;
import com.testcasesdb.response.TestCaseResponse;
import com.testcasesdb.response.TestStepResponse;
import com.testcasesdb.service.TestAreaService;
import lombok.AllArgsConstructor;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TestCaseMapper{
    private final TestAreaService testAreaService;
    public TestCase toEntityFromRequest(TestCaseRequest testCaseRequest){
        TestArea testArea = testAreaService.findEntityById(testCaseRequest.getArea_id());
        if (Optional.ofNullable(testArea).isEmpty()){
            return null;
        }
        TestCase testCase = new TestCase();
        testCase.setResult(testCaseRequest.getResult());
        testCase.setName(testCaseRequest.getName());
        testCase.setArea(testArea);
        testCase.setTestname(testCaseRequest.getTestname());
        testCase.setAssumption(testCaseRequest.getAssumption());
        testCase.setTimeofexecution(testCaseRequest.getTimeofexecution().toInstant());
        /*if (Strings.isNotEmpty(testCaseRequest.getTaskdescription())) {
            testCase.getTestTasks().add(TestTasks.builder()
                    .description(testCaseRequest.getTaskdescription()).build());
        }*/
        return testCase;
    }
    public TestCase toEntityFromRequestUpdate(TestCase testCase,TestCaseRequest testCaseRequest){
        TestArea testArea = testAreaService.findEntityById(testCase.getArea().getId());
        if (Optional.ofNullable(testArea).isEmpty()){
            return null;
        }

        if (Strings.isNotEmpty(testCaseRequest.getResult())) {
            testCase.setResult(testCaseRequest.getResult());
        }
        if (Strings.isNotEmpty(testCaseRequest.getName())) {
            testCase.setName(testCaseRequest.getName());
        }
        testCase.setArea(testArea);

        if (Strings.isNotEmpty(testCaseRequest.getTestname())) {
            testCase.setTestname(testCaseRequest.getTestname());
        }
        if (Strings.isNotEmpty(testCaseRequest.getAssumption())) {
            testCase.setAssumption(testCaseRequest.getAssumption());
        }
        if (Objects.nonNull(testCaseRequest.getTimeofexecution())) {
            testCase.setTimeofexecution(testCaseRequest.getTimeofexecution().toInstant());
        }

        return testCase;
    }
    public TestCaseResponse toResponseFromEntity(TestCase testCase){
        return TestCaseResponse.builder()
                .id(testCase.getId())
                .area(testAreaToTestAreaResponse(testCase.getArea()))
                .name(testCase.getName())
                .result(testCase.getResult())
                .timeofexecution(testCase.getTimeofexecution())
                .testname(testCase.getTestname())
                .assumption(testCase.getAssumption())
                .testStep(testStepToTestCaseResponseMap(testCase.getTestStep()))
                //.testTasks(testTaskToTestTaskResponse(testCase.getTestTasks()))
                .build();
    }

    private TestAreaResponse testAreaToTestAreaResponse(TestArea testArea){
        return TestAreaResponse.builder()
                .id(testArea.getId())
                .description(testArea.getDescription())
                .build();
    }
    private Set<TestStepResponse> testStepToTestCaseResponseMap(Set<TestStep> testSteps) {
        return testSteps.stream().map(ts-> TestStepResponse.builder()
                .id(ts.getId())
                .description(ts.getDescription())
                .screenshotName(ts.getScreenshotName()).build()).collect(Collectors.toSet());
    }
}
