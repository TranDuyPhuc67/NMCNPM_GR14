package com.example.model;

public class KHOANPHI {
    private int Idhogiadinh;
    private int Idcanho;
    private double Phidichvu;
    private double Phiquanly;

    public KHOANPHI() {
        super();
    }

    public KHOANPHI(int idhogiadinh, int idcanho, double phidichvu, double phiquanly) {
        this.Idhogiadinh = idhogiadinh;
        this.Idcanho = idcanho;
        this.Phidichvu = phidichvu;
        this.Phiquanly = phiquanly;
    }

    public int getIdhogiadinh() {
        return Idhogiadinh;
    }

    public void setIdhogiadinh(int idhogiadinh) {
        this.Idhogiadinh = idhogiadinh;
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
