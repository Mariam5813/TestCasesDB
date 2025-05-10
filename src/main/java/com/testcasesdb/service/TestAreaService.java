package com.testcasesdb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.testcasesdb.request.TestAreaRequest;
import com.testcasesdb.response.TestAreaResponse;
import org.springframework.stereotype.Service;

import com.testcasesdb.dto.TestAreaDto;
import com.testcasesdb.entity.TestArea;
import com.testcasesdb.mapper.TestAreaMapper;
import com.testcasesdb.repository.TestAreaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TestAreaService {
    private final TestAreaRepository testAreaRepository;
    private final TestAreaMapper testAreaMapper;


    public void delete(Integer id) {
        TestArea testArea = testAreaRepository.findById(id).orElse(null);
        if (testArea!=null) {
            testAreaRepository.delete(testArea);
        }
    }

    public List<TestAreaResponse> findAll() {
        List<TestAreaResponse> result = new ArrayList<>();

        List<TestArea> testAreas = testAreaRepository.findAll();
        result = testAreas.stream()
                .map(testAreaMapper::toResponseFromEntity).collect(Collectors.toList());
        return result;
    }

    public TestAreaResponse findById(Integer id) {
        TestArea testArea = testAreaRepository.getReferenceById(id);
        return testAreaMapper.toResponseFromEntity(testArea);
    }
    public TestArea findEntityById(Integer id) {
        TestArea testArea = testAreaRepository.getReferenceById(id);
        return testArea;
    }
    public TestAreaResponse createTestArea(TestAreaRequest testAreaRequest) {
        TestArea testArea = testAreaMapper.toEntityFromRequest(testAreaRequest);
        testArea = testAreaRepository.save(testArea);
        return testAreaMapper.toResponseFromEntity(testArea);
    }
    public TestAreaResponse patchTestArea(TestAreaRequest testAreaRequest) {
        TestArea testArea = testAreaMapper.toEntityFromRequest(testAreaRequest);
        testArea = testAreaRepository.save(testArea);
        return testAreaMapper.toResponseFromEntity(testArea);
    }


    public TestAreaResponse findResponseById(Integer id) {
        TestArea testArea = testAreaRepository.getReferenceById(id);
        return testAreaMapper.toResponseFromEntity(testArea);
    }

    public TestAreaResponse updateTestArea(Integer id, TestAreaRequest testAreaRequest) {
        TestArea testArea = findEntityById(id);
        testAreaMapper.toEntityFromRequestUpdate(testArea,testAreaRequest);
        testAreaRepository.save(testArea);
        return testAreaMapper.toResponseFromEntity(testArea);
    }
}
