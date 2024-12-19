package com.example.model;

import java.sql.Date;

public class HOGIADINH {
	private String CCCDchuho;
	private int Idcanho;
	private int Sothanhvien;
	private String Hotenchuho;
	private String Gioitinh;
	private Date Ngaysinh;
	private String Dantoc;
	private String Tongiao;
	private String Quoctich;
	private String Diachi;
	private String Sdt;
	private String Email;
	private String Trangthai;

	public HOGIADINH() {
		super();
	}

	public HOGIADINH(String cCCDchuho, int idcanho, int sothanhvien, String hotenchuho, String gioitinh, Date ngaysinh,
			String dantoc, String tongiao, String quoctich, String diachi, String sdt, String email, String trangthai) {
		super();
		CCCDchuho = cCCDchuho;
		Idcanho = idcanho;
		Sothanhvien = sothanhvien;
		Hotenchuho = hotenchuho;
		Gioitinh = gioitinh;
		Ngaysinh = ngaysinh;
		Dantoc = dantoc;
		Tongiao = tongiao;
		Quoctich = quoctich;
		Diachi = diachi;
		Sdt = sdt;
		Email = email;
		Trangthai = trangthai;
	}

	public String getCCCDchuho() {
		return CCCDchuho;
	}

	public void setCCCDchuho(String cCCDchuho) {
		CCCDchuho = cCCDchuho;
	}

	public int getIdcanho() {
		return Idcanho;
	}

	public void setIdcanho(int idcanho) {
		Idcanho = idcanho;
	}

	public int getSothanhvien() {
		return Sothanhvien;
	}

	public void setSothanhvien(int sothanhvien) {
		Sothanhvien = sothanhvien;
	}

	public String getHotenchuho() {
		return Hotenchuho;
	}

	public void setHotenchuho(String hotenchuho) {
		Hotenchuho = hotenchuho;
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

	public String getTrangthai() {
		return Trangthai;
	}

	public void setTrangthai(String trangthai) {
		Trangthai = trangthai;
	}

}
