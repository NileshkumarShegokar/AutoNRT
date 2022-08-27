package com.ns.autoNRT.data.entity;

import com.ns.autoNRT.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class TestCaseRun  extends AbstractEntity {

    @ManyToOne
    private TestCase test;
    @OneToOne
    private TestOutput refOutput;
    @OneToOne
    private TestOutput testOutput;

    @ManyToOne
    private TestSuiteRun testSuiteRun;

    public TestCase getTest() {
        return test;
    }

    public void setTest(TestCase test) {
        this.test = test;
    }

    public TestOutput getRefOutput() {
        return refOutput;
    }

    public void setRefOutput(TestOutput refOutput) {
        this.refOutput = refOutput;
    }

    public TestOutput getTestOutput() {
        return testOutput;
    }

    public void setTestOutput(TestOutput testOutput) {
        this.testOutput = testOutput;
    }

    public TestSuiteRun getTestSuiteRun() {
        return testSuiteRun;
    }

    public void setTestSuiteRun(TestSuiteRun testSuiteRun) {
        this.testSuiteRun = testSuiteRun;
    }
}
