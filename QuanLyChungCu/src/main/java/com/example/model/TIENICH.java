package com.example.model;

import java.sql.Date;

public class TIENICH {
	private int Idtienich;
	private int Idhogiadinh;
	private String LoaiTienIch;
	private double TongTien;
	private Date ThangThu;

	public TIENICH() {
		super();
	}

	public TIENICH(int idtienich, int idhogiadinh, String loaiTienIch, double tongTien, Date thangThu) {
		super();
		Idtienich = idtienich;
		Idhogiadinh = idhogiadinh;
		LoaiTienIch = loaiTienIch;
		TongTien = tongTien;
		ThangThu = thangThu;
	}

	public TIENICH(int idhogiadinh, String loaiTienIch, double tongTien, Date thangThu) {
		super();
		Idhogiadinh = idhogiadinh;
		LoaiTienIch = loaiTienIch;
		TongTien = tongTien;
		ThangThu = thangThu;
	}

	public int getIdtienich() {
		return Idtienich;
	}

	public void setIdtienich(int idtienich) {
		Idtienich = idtienich;
	}

	public int getIdhogiadinh() {
		return Idhogiadinh;
	}

	public void setIdhogiadinh(int idhogiadinh) {
		Idhogiadinh = idhogiadinh;
	}

	public String getLoaiTienIch() {
		return LoaiTienIch;
	}

	public void setLoaiTienIch(String loaiTienIch) {
		LoaiTienIch = loaiTienIch;
	}

	public double getTongTien() {
		return TongTien;
	}

	public void setTongTien(double tongTien) {
		TongTien = tongTien;
	}

	public Date getThangThu() {
		return ThangThu;
	}

	public void setThangThu(Date thangThu) {
		ThangThu = thangThu;
	}
}
