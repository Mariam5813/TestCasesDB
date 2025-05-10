package com.testcasesdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.testcasesdb.entity.TestArea;

public interface TestAreaRepository extends JpaRepository<TestArea, Integer> {

}
