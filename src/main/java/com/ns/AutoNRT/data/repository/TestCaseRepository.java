package com.ns.autoNRT.data.repository;

import com.ns.autoNRT.data.entity.Environment;
import com.ns.autoNRT.data.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCase, Integer> {

    @Query("select t from TestCase t " +
        "where lower(t.name) like lower(concat('%', :searchTerm, '%')) ")
    List<TestCase> search(@Param("searchTerm") String searchTerm);
}
