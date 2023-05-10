package com.example.practise.Model;

public class Prescription {
    String checkInTime;
    String visitNumber;
    String consultedTime;
    String doctorName;

    public Prescription(String checkInTime, String visitNumber, String consultedTime, String doctorName) {
        this.checkInTime = checkInTime;
        this.visitNumber = visitNumber;
        this.consultedTime = consultedTime;
        this.doctorName = doctorName;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(String visitNumber) {
        this.visitNumber = visitNumber;
    }

    public String getConsultedTime() {
        return consultedTime;
    }

    public void setConsultedTime(String consultedTime) {
        this.consultedTime = consultedTime;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
