package com.testcasesdb.mapper;

import com.testcasesdb.entity.TestCase;
import com.testcasesdb.entity.TestStep;
import com.testcasesdb.request.TestStepRequest;

import com.testcasesdb.response.TestStepResponse;
import com.testcasesdb.service.TestAreaService;
import com.testcasesdb.service.TestCaseService;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class TestStepMapper {
    private final TestCaseService testCaseService;
    private final TestAreaMapper testAreaMapper;
    public TestStep toEntityFromRequest(TestStepRequest testStepRequest){
        TestStep testStep = new TestStep();

        testStep.setDescription(testStepRequest.getDescription());
        /*if (Objects.nonNull(testStepRequest.getCase_id())) {
            TestCase testCase = testCaseService.findEntityById(testStepRequest.getCase_id());
            if (Objects.nonNull(testCase)) {
                testStep.getTestCase().add(testCase);
            }
        }*/
        return testStep;
    }
    public TestStepResponse toResponseFromEntity(TestStep testStep){
        return TestStepResponse.builder()
                .id(testStep.getId())
                .screenshotName(testStep.getScreenshotName())
                .description(testStep.getDescription())
                .testCase(testAreaMapper.testCaseToTestCaseResponseMap(testStep.getTestCase()))
                .build();

    }
}
