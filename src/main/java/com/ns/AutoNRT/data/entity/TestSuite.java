package com.ns.autoNRT.data.entity;

import com.ns.autoNRT.data.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
@Entity
public class TestSuite extends AbstractEntity {

    @NotEmpty
    private  String name;
    @ManyToOne
    private Environment refEnv;
    @ManyToOne
    private Environment testEnv;
    @ManyToOne
    private TestPlan testPlan;
    @OneToMany(mappedBy = "suite")
    List<TestSuiteRun> runs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Environment getRefEnv() {
        return refEnv;
    }

    public void setRefEnv(Environment refEnv) {
        this.refEnv = refEnv;
    }

    public Environment getTestEnv() {
        return testEnv;
    }

    public void setTestEnv(Environment testEnv) {
        this.testEnv = testEnv;
    }

    public TestPlan getTestPlan() {
        return testPlan;
    }

    public void setTestPlan(TestPlan testPlan) {
        this.testPlan = testPlan;
    }

    public List<TestSuiteRun> getRuns() {
        return runs;
    }

    public void setRuns(List<TestSuiteRun> runs) {
        this.runs = runs;
    }
}
