package com.example.model;

public class TIENICH {
    private int Idcanho;
    private int Sodien;
    private int Sonuoc;
    private int Internet;
    private String Hanthu; // Định dạng YYYY-MM dưới dạng chuỗi
    private int Tongtienich;

    // Constructor mặc định
    public TIENICH() {
        super();
    }

    // Constructor đầy đủ không có Tongtienich (tùy chọn khi không tính tổng)
    public TIENICH(int idcanho, int sodien, int sonuoc, int internet, String hanthu) {
        super();
        Idcanho = idcanho;
        Sodien = sodien;
        Sonuoc = sonuoc;
        Internet = internet;
        Hanthu = hanthu;
    }

    // Constructor đầy đủ với tất cả các trường
    public TIENICH(int idcanho, int sodien, int sonuoc, int internet, String hanthu, int tongtienich) {
        super();
        Idcanho = idcanho;
        Sodien = sodien;
        Sonuoc = sonuoc;
        Internet = internet;
        Hanthu = hanthu;
        Tongtienich = tongtienich;
    }

    // Getters và Setters
    public int getIdcanho() {
        return Idcanho;
    }

    public void setIdcanho(int idcanho) {
        Idcanho = idcanho;
    }

    public int getSodien() {
        return Sodien;
    }

    public void setSodien(int sodien) {
        Sodien = sodien;
    }

    public int getSonuoc() {
        return Sonuoc;
    }

    public void setSonuoc(int sonuoc) {
        Sonuoc = sonuoc;
    }

    public int getInternet() {
        return Internet;
    }

    public void setInternet(int internet) {
        Internet = internet;
    }

    public String getHanthu() {
        return Hanthu;
    }

    public void setHanthu(String hanthu) {
        Hanthu = hanthu;
    }

    public int getTongtienich() {
        return Tongtienich;
    }

    public void setTongtienich(int tongtienich) {
        Tongtienich = tongtienich;
    }
}