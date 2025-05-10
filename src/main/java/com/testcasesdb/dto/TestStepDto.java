package com.testcasesdb.dto;
import lombok.*;
import com.testcasesdb.entity.TestStep;

import java.time.Instant;
import java.util.List;

/**
 * DTO for {@link TestStep}
 */
@Builder(toBuilder = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestStepDto {
    Integer id;
    TestCaseDto testCase;

    String description;
    String screenshotName;
    //byte[] screenshot;


}
