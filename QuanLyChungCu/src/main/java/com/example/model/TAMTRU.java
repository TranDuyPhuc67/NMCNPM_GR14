package com.example.model;

import java.sql.Date;
import com.example.model.NHANKHAU;

public class TAMTRU {
    private String CCCD;
	private String Hovaten;
    private int IdCanHo;
    private Date Ngaybatdau;
    private Date Ngayketthuc;
	private String Lydo;
	private String Trangthai;
    private NHANKHAU Nhankhau;
    private String Sonha;

    public TAMTRU() {
		super();
	}
    public TAMTRU(String cCCD, int idcanho, String hovaten, Date ngaybd, Date ngaykt, String lydo, NHANKHAU nhankhau) {
        super();
        CCCD = cCCD;
        IdCanHo = idcanho;
        Hovaten = hovaten;
        Ngaybatdau = ngaybd;
        Ngayketthuc = ngaykt;
        Trangthai = "TV";
        Lydo = lydo;
        Nhankhau = nhankhau;
        }

    public String getCccd() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public int getIdCanHo() {
        return IdCanHo;
    }

    public void setIdCanHo(int idcanho) {
        this.IdCanHo = idcanho;
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
    public NHANKHAU getNhankhau(){
        return Nhankhau;
    }
    public void setNhankhau(NHANKHAU nhankhau){
        this.Nhankhau = nhankhau;
    }
    public String getSonha() {
        return Sonha;
    }
    public void setSonha(String Sonha) {
        this.Sonha = Sonha;
    }

}

