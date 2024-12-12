package com.example.model;

import java.math.BigDecimal;
import java.sql.Date;

public class TIENICH {
    private Integer Idtienich;     
    private Integer Idhogiadinh;      
    private String LoaiTienIch;
    private BigDecimal TongTien;      
    private Date ThangThu;

    public TIENICH() {
        super();
    }

	public TIENICH(Integer idtienich, Integer idhogiadinh, String loaiTienIch, BigDecimal tongTien, Date thangThu) {
        super();
        Idtienich = idtienich;
        Idhogiadinh = idhogiadinh;
        LoaiTienIch = loaiTienIch;
        TongTien = tongTien;
        ThangThu = thangThu;
    }

    public TIENICH(Integer idhogiadinh, String loaiTienIch, BigDecimal tongTien, Date thangThu) {
        super();
        Idhogiadinh = idhogiadinh;
        LoaiTienIch = loaiTienIch;
        TongTien = tongTien;
        ThangThu = thangThu;
    }

    public Integer getIdtienich() {
        return Idtienich;
    }

    public void setIdtienich(Integer idtienich) {
        Idtienich = idtienich;
    }

    public Integer getIdhogiadinh() {
        return Idhogiadinh;
    }

    public void setIdhogiadinh(Integer idhogiadinh) {
        Idhogiadinh = idhogiadinh;
    }

    public String getLoaiTienIch() {
        return LoaiTienIch;
    }

    public void setLoaiTienIch(String loaiTienIch) {
        LoaiTienIch = loaiTienIch;
    }

    public BigDecimal getTongTien() {
        return TongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        TongTien = tongTien;
    }

    public Date getThangThu() {
        return ThangThu;
    }

    public void setThangThu(Date thangThu) {
        ThangThu = thangThu;
    }
}
