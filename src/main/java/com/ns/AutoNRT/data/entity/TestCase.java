package com.ns.autoNRT.data.entity;

import com.ns.autoNRT.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class TestCase extends AbstractEntity {
    private String name;
    private String uri;
    private String type;
    private String body;
    private String contentType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @OneToMany(mappedBy = "test")
    List<TestCaseRun> testCaseRuns;
    @ManyToMany
    List<TestPlan> planList;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<TestCaseRun> getTestCaseRuns() {
        return testCaseRuns;
    }

    public void setTestCaseRuns(List<TestCaseRun> testCaseRuns) {
        this.testCaseRuns = testCaseRuns;
    }

    public List<TestPlan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<TestPlan> planList) {
        this.planList = planList;
    }
}
