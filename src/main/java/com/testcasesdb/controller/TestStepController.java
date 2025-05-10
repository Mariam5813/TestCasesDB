package com.testcasesdb.controller;

import com.testcasesdb.entity.TestStep;
import com.testcasesdb.mapper.TestStepMapper;
import com.testcasesdb.repository.TestStepRepository;
import com.testcasesdb.request.TestCaseRequest;
import com.testcasesdb.request.TestStepRequest;

import com.testcasesdb.response.TestCaseResponse;
import com.testcasesdb.response.TestStepResponse;
import com.testcasesdb.service.TestStepService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;

import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/test/step")
@RequiredArgsConstructor
public class TestStepController {
    private final TestStepMapper testStepMapper;
    private final TestStepService testStepService;

    private final TestStepRepository testStepRepository;

    @PostMapping
    public ResponseEntity<TestStepResponse> createStepAndUploadScreenshot(@RequestPart("request") TestStepRequest request,
                                                          @RequestPart("file") MultipartFile file) {

        TestStep testStep = testStepMapper.toEntityFromRequest(request);
        try {
            testStep.setScreenshot(file.getBytes());
            testStep.setScreenshotName(new String(file.getOriginalFilename().getBytes("UTF-8"), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(testStepService.createStep(testStep));
    }

    @PutMapping("/{id}/file")
    public ResponseEntity<TestStepResponse> uploadScreenshot(@PathVariable("id") Integer id,
                                           @RequestPart("file") MultipartFile file) {

        TestStep testStep = testStepService.findById(id);
        if (Objects.nonNull(testStep)) {
            try {
                testStep.setScreenshot(file.getBytes());
                testStep.setScreenshotName(new String(file.getOriginalFilename().getBytes("UTF-8"), StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return new ResponseEntity<>(testStepService.createStep(testStep), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}/file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadScreenshot(@PathVariable("id") Integer id) {

        TestStep testStep = testStepService.findById(id);
        if (Objects.nonNull(testStep)) {
            var array = testStep.getScreenshot();
            ByteArrayResource resource = new ByteArrayResource(array);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ContentDisposition
                                    .attachment()
                                    .filename(testStep.getScreenshotName(),StandardCharsets.UTF_8)
                                    .build().toString())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public List<TestStepResponse> getAllTestStep() {
        return testStepService.findAll();
    }

    @GetMapping("/{id}")
    public TestStepResponse getTestStepById(@PathVariable("id") Integer id) {
        return testStepService.findStepResponseById(id);
    }
    // добавляем к степу кейс
    @PutMapping("/{step_id}/add/{case_id}/case")
    public ResponseEntity<Object> addCaseToStep(@PathVariable(name="step_id") Integer stepId,
                                                @PathVariable(name="case_id") Integer caseId) {
        return testStepService.addCaseToStep(caseId,stepId);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TestStepResponse>delete(@PathVariable Integer id) {
        testStepService.delete(id);
        return ResponseEntity.ok().build();
    }
}
