package com.testcasesdb.service;

import com.testcasesdb.entity.TestCase;
import com.testcasesdb.entity.TestStep;
import com.testcasesdb.errors.TestErrorsResponse;
import com.testcasesdb.mapper.TestStepMapper;
import com.testcasesdb.repository.TestCasesRepository;
import com.testcasesdb.repository.TestStepRepository;
import com.testcasesdb.request.TestCaseRequest;
import com.testcasesdb.request.TestStepRequest;
import com.testcasesdb.response.TestStepResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestStepService {
    private final TestStepRepository testStepRepository;
    private final TestCasesRepository testCaseRepository;
    private final TestStepMapper testStepMapper;
    public TestStepResponse createStep(TestStep testStep){
        TestStep ts = testStepRepository.saveAndFlush(testStep);
        return testStepMapper.toResponseFromEntity(ts);
    }

    public TestStep findById(Integer id) {
        return testStepRepository.findById(id).orElse(null);
    }
    public TestStepResponse findStepResponseById(Integer id) {
        return testStepMapper.toResponseFromEntity(findById(id));
    }
    public List<TestStepResponse> findAll() {

        List<TestStep> testSteps = testStepRepository.findAll();
        List<TestStepResponse> testStepResponses = testSteps.stream()
                .map(testStepMapper::toResponseFromEntity)
                .collect(Collectors.toList());

        return testStepResponses;
    }

    public ResponseEntity<Object> addCaseToStep(Integer caseId, Integer stepId) {
        TestStep testStep = testStepRepository.findById(stepId).orElse(null);
        if (!Optional.ofNullable(testStep).isPresent()){
            return ResponseEntity.ok(TestErrorsResponse.builder()
                    .error("Test Step with id=%s not found".formatted(stepId))
                    .status(HttpStatus.NOT_FOUND.name()).build());
        }
        TestCase testCase = testCaseRepository.findById(caseId).orElse(null);
        if (!Optional.ofNullable(testCase).isPresent()){
            return ResponseEntity.ok(TestErrorsResponse.builder()
                    .error("Test Step with id=%s not found".formatted(stepId))
                    .status(HttpStatus.NOT_FOUND.name()).build());
        }
        testStep.getTestCase().add(testCase);
        testStepRepository.save(testStep);
        return ResponseEntity.ok(testStepMapper.toResponseFromEntity(testStep));
    }

    public void delete(Integer id) {
        TestStep testStep = testStepRepository.findById(id).orElse(null);
        if (Objects.nonNull(testStep)){
            testStepRepository.delete(testStep);
        }
    }
}
