package com.example.model;

public class KHOANPHI {
    private String CCCDchuho;
    private int Idcanho;
    private double Phidichvu;
    private double Phiquanly;

    public KHOANPHI() {
        super();
    }

    public KHOANPHI(String CCCDchuho, int idcanho, double phidichvu, double phiquanly) {
        this.CCCDchuho = CCCDchuho;
        this.Idcanho = idcanho;
        this.Phidichvu = phidichvu;
        this.Phiquanly = phiquanly;
    }

    public String getCCCDchuho() {
        return CCCDchuho;
    }

    public void setCCCDchuho(String CCCDchuho) {
        this.CCCDchuho = CCCDchuho;
    }

    public int getIdcanho() {
        return Idcanho;
    }

    public void setIdcanho(int idcanho) {
        this.Idcanho = idcanho;
    }

    public double getPhidichvu() {
        return Phidichvu;
    }

    public void setPhidichvu(double phidichvu) {
        this.Phidichvu = phidichvu;
    }

    public double getPhiquanly() {
        return Phiquanly;
    }

    public void setPhiquanly(double phiquanly) {
        this.Phiquanly = phiquanly;
    }
}
