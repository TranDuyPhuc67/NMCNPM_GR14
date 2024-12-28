package com.example.model;

import java.util.Date;

public class TONGTHANHTOAN {
    private int Idcanho;
    private int Tongphichungcu;
    private int Tongtienich;
    private int Tongguixe;
    private int Tongphi;
    private int Sotiendanop;
    private int Sodu;
    private String Trangthai;
    private String Hanthu; // Định dạng YYYY-MM dưới dạng chuỗi
    private Date Thoigianthu; // Ngày thu phí

    public TONGTHANHTOAN() {
        super();
    }

    public TONGTHANHTOAN(int idcanho, int tongphichungcu, int tongtienich, int tongguixe, int tongphi, int sotiendanop,
            int sodu, String trangthai, String hanthu, Date thoigianthu) {
        super();
        Idcanho = idcanho;
        Tongphichungcu = tongphichungcu;
        Tongtienich = tongtienich;
        Tongguixe = tongguixe;
        Tongphi = tongphi;
        Sotiendanop = sotiendanop;
        Sodu = sodu;
        Trangthai = trangthai;
        Hanthu = hanthu;
        Thoigianthu = thoigianthu;
    }

    public int getIdcanho() {
        return Idcanho;
    }

    public void setIdcanho(int idcanho) {
        Idcanho = idcanho;
    }

    public int getTongphichungcu() {
        return Tongphichungcu;
    }

    public void setTongphichungcu(int tongphichungcu) {
        Tongphichungcu = tongphichungcu;
    }

    public int getTongtienich() {
        return Tongtienich;
    }

    public void setTongtienich(int tongtienich) {
        Tongtienich = tongtienich;
    }

    public int getTongguixe() {
        return Tongguixe;
    }

    public void setTongguixe(int tongguixe) {
        Tongguixe = tongguixe;
    }

    public int getTongphi() {
        return Tongphi;
    }

    public void setTongphi(int tongphi) {
        Tongphi = tongphi;
    }

    public int getSotiendanop() {
        return Sotiendanop;
    }

    public void setSotiendanop(int sotiendanop) {
        Sotiendanop = sotiendanop;
    }

    public int getSodu() {
        return Sodu;
    }

    public void setSodu(int sodu) {
        Sodu = sodu;
    }

    public String getTrangthai() {
        return Trangthai;
    }

    public void setTrangthai(String trangthai) {
        Trangthai = trangthai;
    }

    public String getHanthu() {
        return Hanthu;
    }

    public void setHanthu(String hanthu) {
        Hanthu = hanthu;
    }

    public Date getThoigianthu() {
        return Thoigianthu;
    }

    public void setThoigianthu(Date thoigianthu) {
        Thoigianthu = thoigianthu;
    }
}
