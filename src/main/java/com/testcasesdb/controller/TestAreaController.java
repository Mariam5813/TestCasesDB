package com.testcasesdb.controller;

import com.testcasesdb.entity.TestArea;
import com.testcasesdb.repository.TestAreaRepository;
import com.testcasesdb.service.TestAreaService;
import com.testcasesdb.request.TestAreaRequest;
import com.testcasesdb.response.TestAreaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/test/area")
@RequiredArgsConstructor
public class TestAreaController {
    private final TestAreaService testAreaService;
    private final TestAreaRepository testAreaRepository;
    @PostMapping
    public ResponseEntity<TestAreaResponse> createTestArea(@RequestBody TestAreaRequest testAreaRequest){

        TestAreaResponse testAreaResponse = testAreaService.createTestArea(testAreaRequest);
        return ResponseEntity.ok(testAreaResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TestAreaResponse>> getAllTestArea(){

        return ResponseEntity.ok(testAreaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestAreaResponse> getAreaResponseById(@PathVariable Integer id){
        return ResponseEntity.ok(testAreaService.findResponseById(id));
    }
    @PatchMapping
    public ResponseEntity<TestAreaResponse> patchTestArea(@RequestBody TestAreaRequest testAreaRequest){

        TestAreaResponse testAreaResponse = testAreaService.patchTestArea(testAreaRequest);
        return ResponseEntity.ok(testAreaResponse);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<TestAreaResponse> updateTestArea(@PathVariable Integer id,
                                                           @RequestBody TestAreaRequest testAreaRequest){

        TestAreaResponse testAreaResponse = testAreaService.updateTestArea(id,testAreaRequest);
        return ResponseEntity.ok(testAreaResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TestAreaResponse> delete(@PathVariable Integer id){

        testAreaService.delete(id);
        return ResponseEntity.ok().build();
    }

}
