package com.ns.autoNRT.data.entity;

import com.ns.autoNRT.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class TestOutput extends AbstractEntity {

    private String contentType;
    private String output;
    private Long responseTime;
    @OneToOne
    private TestCaseRun testCaseRun;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

    public TestCaseRun getTestCaseRun() {
        return testCaseRun;
    }

    public void setTestCaseRun(TestCaseRun testCaseRun) {
        this.testCaseRun = testCaseRun;
    }
}
