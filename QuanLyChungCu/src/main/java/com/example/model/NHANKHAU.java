package com.example.model;

import java.sql.Date;

public class NHANKHAU {
	private String CCCD;
	private String CCCDchuho;
	private String Hovaten;
	private String Gioitinh;
	private Date Ngaysinh;
	private String Dantoc;
	private String Tongiao;
	private String Quoctich;
	private String Diachi;
	private String Sdt;
	private String Email;
	private String Quanhe;
	private String Trangthai;

	public NHANKHAU() {
		super();
	}

	public NHANKHAU(String cCCD, String cCCDchuho, String hovaten, String gioitinh, Date ngaysinh, String dantoc,
			String tongiao, String quoctich, String diachi, String sdt, String email, String quanhe, String trangthai) {
		super();
		CCCD = cCCD;
		CCCDchuho = cCCDchuho;
		Hovaten = hovaten;
		Gioitinh = gioitinh;
		Ngaysinh = ngaysinh;
		Dantoc = dantoc;
		Tongiao = tongiao;
		Quoctich = quoctich;
		Diachi = diachi;
		Sdt = sdt;
		Email = email;
		Quanhe = quanhe;
		Trangthai = trangthai;
	}

	public String getCccd() {
		return CCCD;
	}

	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}

	public String getCCCDchuho() {
		return CCCDchuho;
	}

	public void setCCCDchuho(String cCCDchuho) {
		CCCDchuho = cCCDchuho;
	}

	public String getHovaten() {
		return Hovaten;
	}

	public void setHovaten(String hovaten) {
		Hovaten = hovaten;
	}

	public String getGioitinh() {
		return Gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		Gioitinh = gioitinh;
	}

	public Date getNgaysinh() {
		return Ngaysinh;
	}

	public void setNgaysinh(Date ngaysinh) {
		Ngaysinh = ngaysinh;
	}

	public String getDantoc() {
		return Dantoc;
	}

	public void setDantoc(String dantoc) {
		Dantoc = dantoc;
	}

	public String getTongiao() {
		return Tongiao;
	}

	public void setTongiao(String tongiao) {
		Tongiao = tongiao;
	}

	public String getQuoctich() {
		return Quoctich;
	}

	public void setQuoctich(String quoctich) {
		Quoctich = quoctich;
	}

	public String getDiachi() {
		return Diachi;
	}

	public void setDiachi(String diachi) {
		Diachi = diachi;
	}

	public String getSdt() {
		return Sdt;
	}

	public void setSdt(String sdt) {
		Sdt = sdt;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getQuanhe() {
		return Quanhe;
	}

	public void setQuanhe(String quanhe) {
		Quanhe = quanhe;
	}

	public String getTrangthai() {
		return Trangthai;
	}

	public void setTrangthai(String trangthai) {
		Trangthai = trangthai;
	}
}
