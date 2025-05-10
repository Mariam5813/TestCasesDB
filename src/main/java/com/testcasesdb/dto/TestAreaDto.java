package com.testcasesdb.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

/**
 * DTO for {@link com.testcasesdb.dto.TestAreaDto}
 */

@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestAreaDto {

    private Integer id;

    private String description;

    private Set<TestCaseDto> testCase;

}
