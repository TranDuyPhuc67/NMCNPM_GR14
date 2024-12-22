package com.example.model;

import java.sql.Date;

public class TAMTRUVANG {
    private String CCCD;
	private String Hovaten;
    private int IDCanHo;
    private Date Ngaybatdau;
    private Date Ngayketthuc;
	private String Lydo;
	private String Trangthai;

    public TAMTRUVANG() {
		super();
	}
    public TAMTRUVANG(String cCCD, int idcanho, String hovaten,  String trangthai, Date ngaybd, Date ngaykt, String lydo) {
        super();
        CCCD = cCCD;
        IDCanHo = idcanho;
        Hovaten = hovaten;
        Ngaybatdau = ngaybd;
        Ngayketthuc = ngaykt;
        Trangthai = trangthai;
        Lydo = lydo;
        }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public int getIdCanHo() {
        return IDCanHo;
    }

    public void setCCCDchuho(int idcanho) {
        this.IDCanHo = idcanho;
    }

    public String getHovaten() {
        return Hovaten;
    }

    public void setHovaten(String Hovaten) {
        this.Hovaten = Hovaten;
    }

    public String getTrangthai() {
        return Trangthai;
    }

    public void setTrangthai(String Trangthai) {
        this.Trangthai = Trangthai;
    }

    public Date getNgaybatdau() {
        return Ngaybatdau;
    }

    public void setNgaybatdau(Date Ngaybatdau) {
        this.Ngaybatdau = Ngaybatdau;
    }

    public Date getNgayketthuc() {
        return Ngayketthuc;
    }

    public void setNgayketthuc(Date Ngayketthuc) {
        this.Ngayketthuc = Ngayketthuc;
    }

    public String getLydo() {
        return Lydo;
    }

    public void setLydo(String Lydo) {
        this.Lydo = Lydo;
    }

}

