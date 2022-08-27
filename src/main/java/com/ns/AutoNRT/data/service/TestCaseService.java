package com.ns.autoNRT.data.service;

import com.ns.autoNRT.data.entity.TestCase;
import com.ns.autoNRT.data.repository.TestCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseService {

    private final TestCaseRepository testCaseRepository;

    public TestCaseService(TestCaseRepository testCaseRepository) {
        this.testCaseRepository = testCaseRepository;
    }

    public List<TestCase> findAllTestCases(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return testCaseRepository.findAll();
        } else {
            return testCaseRepository.search(stringFilter);
        }
    }

    public long countTestCases() {
        return testCaseRepository.count();
    }

    public void deleteTestCase(TestCase testCase) {
        testCaseRepository.delete(testCase);
    }

    public void saveTestCase(TestCase testCase) {
        if (testCase == null) {
            System.err.println("TestCase is null. Are you sure you have connected your form to the application?");
            return;
        }
        testCaseRepository.save(testCase);
    }


}
