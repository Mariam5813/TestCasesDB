package com.testcasesdb.controller;

import java.util.List;

import com.testcasesdb.dto.TestCaseDto;
import com.testcasesdb.request.TestCaseRequest;
import com.testcasesdb.response.TestAreaResponse;
import com.testcasesdb.response.TestCaseResponse;
import com.testcasesdb.service.TestCaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.testcasesdb.dto.TestAreaDto;
import com.testcasesdb.entity.TestArea;
import com.testcasesdb.service.TestAreaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/test/case")
@RequiredArgsConstructor
public class TestCaseController {
	

    private final TestCaseService testCaseService;

    @PostMapping
    public ResponseEntity<TestCaseResponse> createTestCase(@RequestBody TestCaseRequest testCaseRequest) {
        TestCaseResponse testArea = testCaseService.createTestCase(testCaseRequest);
        return ResponseEntity.ok(testArea);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<TestCaseResponse> updateTestCase(@PathVariable Integer id,@RequestBody TestCaseRequest testCaseRequest) {
        return testCaseService.updateTestCase(id, testCaseRequest);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<TestCaseResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(testCaseService.findById(id));
    }

	@GetMapping("/all")
    public ResponseEntity<List<TestCaseResponse>> findAll() {
        return ResponseEntity.ok(testCaseService.findAll());
    }

	@DeleteMapping("/{id}")
    public ResponseEntity<TestCaseResponse>delete(@PathVariable Integer id) {
        testCaseService.delete(id);
        return ResponseEntity.ok().build();
    }
    // добавляем к кейсу степ
    @PutMapping("/{case_id}/add/{step_id}/step")
    public ResponseEntity<Object> addStepToCase(@PathVariable(name="case_id") Integer caseId,
                                                          @PathVariable(name="step_id") Integer stepId) {
        return testCaseService.addStepToCase(caseId,stepId);
    }
}
