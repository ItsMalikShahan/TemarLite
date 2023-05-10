package com.example.practise.Model;

public class Centers {
    int centerLogo;
    String centerName;
    String centerLocation;
    String centerStatus;

    public Centers(int centerLogo, String centerName, String centerLocation, String centerStatus) {
        this.centerLogo = centerLogo;
        this.centerName = centerName;
        this.centerLocation = centerLocation;
        this.centerStatus = centerStatus;
    }

    public int getCenterLogo() {
        return centerLogo;
    }

    public void setCenterLogo(int centerLogo) {
        this.centerLogo = centerLogo;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterLocation() {
        return centerLocation;
    }

    public void setCenterLocation(String centerLocation) {
        this.centerLocation = centerLocation;
    }

    public String getCenterStatus() {
        return centerStatus;
    }

    public void setCenterStatus(String centerStatus) {
        this.centerStatus = centerStatus;
    }
}
