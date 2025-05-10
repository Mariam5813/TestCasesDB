package com.testcasesdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testcasesdb.entity.TestCase;

public interface TestCasesRepository extends JpaRepository<TestCase, Integer> {

}
