package com.testcasesdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testcasesdb.entity.TestStep;

public interface TestStepRepository extends JpaRepository<TestStep, Integer> {

}
