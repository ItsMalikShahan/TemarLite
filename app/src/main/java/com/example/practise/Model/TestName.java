package com.example.practise.Model;

public class TestName {
    String testName;
    String testPrice;

    public TestName(String testName, String testPrice) {
        this.testName = testName;
        this.testPrice = testPrice;
    }

    public String getTestPrice() {
        return testPrice;
    }

    public void setTestPrice(String testPrice) {
        this.testPrice = testPrice;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
