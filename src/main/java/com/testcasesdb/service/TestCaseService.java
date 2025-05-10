package com.testcasesdb.service;

import com.testcasesdb.entity.TestCase;
import com.testcasesdb.entity.TestStep;
import com.testcasesdb.errors.TestErrorsResponse;
import com.testcasesdb.mapper.TestCaseMapper;
import com.testcasesdb.repository.TestCasesRepository;
import com.testcasesdb.repository.TestStepRepository;
import com.testcasesdb.request.TestCaseRequest;
import com.testcasesdb.response.TestCaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestCaseService {
    private final TestCasesRepository testCaseRepository;
    private final TestStepRepository testStepRepository;
    private final TestCaseMapper testCaseMapper;

    public TestCaseResponse createTestCase(TestCaseRequest testCaseRequest){
        TestCase testCase = testCaseMapper.toEntityFromRequest(testCaseRequest);
        testCase = testCaseRepository.save(testCase);
        return testCaseMapper.toResponseFromEntity(testCase);
    }

    public TestCaseResponse findById(Integer id) {
        TestCase testCase = testCaseRepository.getById(id);
        return testCaseMapper.toResponseFromEntity(testCase);
    }
    public TestCase findEntityById(Integer id) {

        return testCaseRepository.getById(id);
    }

    public List<TestCaseResponse> findAll() {
        List<TestCase> testCase = testCaseRepository.findAll();
        return testCase.stream().map(testCaseMapper::toResponseFromEntity).toList();
    }

    public void delete(Integer id) {
        TestCase testCase = testCaseRepository.findById(id).orElse(null);
        if (testCase != null) {
            //testCase.
            testCaseRepository.delete(testCase);
        }

    }

    public ResponseEntity<TestCaseResponse> updateTestCase(Integer id, TestCaseRequest testCaseRequest) {
        TestCase testCase = findEntityById(id);
        if (Optional.ofNullable(testCase).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        testCaseMapper.toEntityFromRequestUpdate(testCase, testCaseRequest);
        testCaseRepository.save(testCase);
        return ResponseEntity.ok(testCaseMapper.toResponseFromEntity(testCase));


    }

    public ResponseEntity<Object> addStepToCase(Integer caseId, Integer stepId) {
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
        testCase.getTestStep().add(testStep);
        testCaseRepository.save(testCase);
        return ResponseEntity.ok(testCaseMapper.toResponseFromEntity(testCase));
    }
}
