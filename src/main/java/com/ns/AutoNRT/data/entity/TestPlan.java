package com.ns.autoNRT.data.entity;

import com.ns.autoNRT.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class TestPlan extends AbstractEntity {

    private String name;

    @OneToMany (mappedBy = "testPlan")
    private List<TestSuite> testSuites;

    @ManyToMany
    private List<TestCase> tests;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    public void setTestSuites(List<TestSuite> testSuites) {
        this.testSuites = testSuites;
    }

    public List<TestCase> getTests() {
        return tests;
    }

    public void setTests(List<TestCase> tests) {
        this.tests = tests;
    }
}
