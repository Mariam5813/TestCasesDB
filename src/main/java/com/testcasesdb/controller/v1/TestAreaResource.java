package com.testcasesdb.controller.v1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testcasesdb.entity.TestArea;
import com.testcasesdb.repository.TestAreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/rest/admin-ui/testAreas", method = {RequestMethod.GET})
@RequiredArgsConstructor
public class TestAreaResource {

    private final TestAreaRepository testAreaRepository;

    private final ObjectMapper objectMapper;

    @GetMapping
    public PagedModel<TestArea> getAll(Pageable pageable) {
        Page<TestArea> testAreas = testAreaRepository.findAll(pageable);
        return new PagedModel<>(testAreas);
    }

    @GetMapping("/{id}")
    public TestArea getOne(@PathVariable Integer id) {
        Optional<TestArea> testAreaOptional = testAreaRepository.findById(id);
        return testAreaOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));
    }

    @GetMapping("/by-ids")
    public List<TestArea> getMany(@RequestParam List<Integer> ids) {
        return testAreaRepository.findAllById(ids);
    }

    @PostMapping
    public TestArea create(@RequestBody TestArea testArea) {
        return testAreaRepository.save(testArea);
    }

    @PatchMapping("/{id}")
    public TestArea patch(@PathVariable Integer id, @RequestBody JsonNode patchNode) throws IOException {
        TestArea testArea = testAreaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        objectMapper.readerForUpdating(testArea).readValue(patchNode);

        return testAreaRepository.save(testArea);
    }

    @PatchMapping
    public List<Integer> patchMany(@RequestParam List<Integer> ids, @RequestBody JsonNode patchNode) throws IOException {
        Collection<TestArea> testAreas = testAreaRepository.findAllById(ids);

        for (TestArea testArea : testAreas) {
            objectMapper.readerForUpdating(testArea).readValue(patchNode);
        }

        List<TestArea> resultTestAreas = testAreaRepository.saveAll(testAreas);
        return resultTestAreas.stream()
                .map(TestArea::getId)
                .toList();
    }

    @DeleteMapping("/{id}")
    public TestArea delete(@PathVariable Integer id) {
        TestArea testArea = testAreaRepository.findById(id).orElse(null);
        if (testArea != null) {
            testAreaRepository.delete(testArea);
        }
        return testArea;
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Integer> ids) {
        testAreaRepository.deleteAllById(ids);
    }
}
