package com.example.practise.Model;

public class LabModelClass {
    String rft;
    String tp;
    String lft;
    String ic;
    String cbc;
    String ci;
    boolean isChecked;

    public LabModelClass(String rft, String tp, String lft, String ic, String cbc, String ci) {
        this.rft = rft;
        this.tp = tp;
        this.lft = lft;
        this.ic = ic;
        this.cbc = cbc;
        this.ci = ci;
        this.isChecked = false;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getRft() {
        return rft;
    }

    public void setRft(String rft) {
        this.rft = rft;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getLft() {
        return lft;
    }

    public void setLft(String lft) {
        this.lft = lft;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public String getCbc() {
        return cbc;
    }

    public void setCbc(String cbc) {
        this.cbc = cbc;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }
}
