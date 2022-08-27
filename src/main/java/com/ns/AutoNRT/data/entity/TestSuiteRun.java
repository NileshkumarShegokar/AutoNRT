package com.ns.autoNRT.data.entity;

import com.ns.autoNRT.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class TestSuiteRun extends AbstractEntity {


    @ManyToOne
    private TestSuite suite;

    @OneToMany
    private List<TestCaseRun> testCaseRuns;

    public TestSuite getSuite() {
        return suite;
    }

    public void setSuite(TestSuite suite) {
        this.suite = suite;
    }

    public List<TestCaseRun> getTestCaseRuns() {
        return testCaseRuns;
    }

    public void setTestCaseRuns(List<TestCaseRun> testCaseRuns) {
        this.testCaseRuns = testCaseRuns;
    }
}
