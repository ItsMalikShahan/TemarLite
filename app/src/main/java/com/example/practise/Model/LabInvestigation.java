package com.example.practise.Model;

public class LabInvestigation {
    String doctorName;
    String dateTime;
    String testName;
    String report;
    String prescribed;

    public LabInvestigation(String doctorName, String dateTime, String testName, String report, String prescribed) {
        this.doctorName = doctorName;
        this.dateTime = dateTime;
        this.testName = testName;
        this.report = report;
        this.prescribed = prescribed;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getPrescribed() {
        return prescribed;
    }

    public void setPrescribed(String prescribed) {
        this.prescribed = prescribed;
    }
}
