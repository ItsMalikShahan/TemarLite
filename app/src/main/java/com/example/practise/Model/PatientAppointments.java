package com.example.practise.Model;

import java.util.ArrayList;

public class PatientAppointments {
    String patient;
    String dateTime;
    String docName;
    String location;
    String sample;
    ArrayList<PatientTest> patientTests;

    public PatientAppointments(String patient, String dateTime, String docName, String location, String sample) {
        this.patient = patient;
        this.dateTime = dateTime;
        this.docName = docName;
        this.location = location;
        this.sample = sample;
        this.patientTests = new ArrayList<>();
    }


    public ArrayList<PatientTest> getPatientTests() {
        return patientTests;
    }

    public void setPatientTests(ArrayList<PatientTest> patientTests) {
        this.patientTests = patientTests;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }
}
