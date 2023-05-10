package com.example.practise.Model;

public class Imaging {
    String doctorName;
    String timeDate;
    String testName;
    String location;
    String status;
    String labDetail;

    public Imaging(String doctorName, String timeDate, String testName, String location, String status, String labDetail) {
        this.doctorName = doctorName;
        this.timeDate = timeDate;
        this.testName = testName;
        this.location = location;
        this.status = status;
        this.labDetail = labDetail;
    }

    public String getLabDetail() {
        return labDetail;
    }

    public void setLabDetail(String labDetail) {
        this.labDetail = labDetail;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
