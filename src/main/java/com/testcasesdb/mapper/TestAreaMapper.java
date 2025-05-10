package com.testcasesdb.mapper;


import com.testcasesdb.entity.TestCase;
import com.testcasesdb.entity.TestStep;
import com.testcasesdb.request.TestAreaRequest;
import com.testcasesdb.response.TestAreaResponse;

import com.testcasesdb.entity.TestArea;
import com.testcasesdb.response.TestCaseResponse;
import com.testcasesdb.response.TestStepResponse;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TestAreaMapper {
	public TestArea toEntityFromRequest(TestAreaRequest testAreaRequest){
		TestArea testArea = new TestArea();
		testArea.setDescription(testAreaRequest.getDescription());
		return testArea;
	}

	public TestAreaResponse toResponseFromEntity(TestArea testArea) {
		TestAreaResponse testAreaResponse = TestAreaResponse.builder()
				.id(testArea.getId())
				.description(testArea.getDescription())
				.testCase(testCaseToTestCaseResponseMap(testArea.getTestCases())).build();
		return testAreaResponse;
	}


	public Set<TestCaseResponse> testCaseToTestCaseResponseMap(Set<TestCase> testCases){
        return testCases.stream().map(tc->{
            return TestCaseResponse.builder().name(tc.getName())
                    .result(tc.getResult())
                    .testname(tc.getTestname())
                    .timeofexecution(tc.getTimeofexecution())
                    .assumption(tc.getAssumption())
                    .id(tc.getId())
                    .area(TestAreaResponse.builder().id(
							tc.getArea().getId())
							.description(tc.getArea().getDescription()).build())
                    .testStep(testStepToTestCaseResponseMap(tc.getTestStep()))
                    .build();
        }).collect(Collectors.toSet());
	}

	private Set<TestStepResponse> testStepToTestCaseResponseMap(Set<TestStep> testSteps) {
		return testSteps.stream().map(ts->{
			return TestStepResponse.builder()
					.id(ts.getId())
					.description(ts.getDescription())
					.screenshotName(ts.getScreenshotName()).build();
		}).collect(Collectors.toSet());
	}

	public void toEntityFromRequestUpdate(TestArea testArea, TestAreaRequest testAreaRequest) {
		testArea.setDescription(testAreaRequest.getDescription());
	}
}
