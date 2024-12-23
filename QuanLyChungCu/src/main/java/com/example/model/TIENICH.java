package com.example.model;

import java.sql.Date;

public class TIENICH {
	private int Idcanho;
	private int Sodien;
	private int Sonuoc;
	private int Internet;
	private Date Hanthu;
	private int Tongtienich;

	public TIENICH() {
		super();
	}

	public TIENICH(int idcanho, int sodien, int sonuoc, int internet, Date hanthu) {
		super();
		Idcanho = idcanho;
		Sodien = sodien;
		Sonuoc = sonuoc;
		Internet = internet;
		Hanthu = hanthu;
	}

	public TIENICH(int idcanho, int sodien, int sonuoc, int internet, Date thoihan, int tongtienich) {
		super();
		Idcanho = idcanho;
		Sodien = sodien;
		Sonuoc = sonuoc;
		Internet = internet;
		Hanthu = thoihan;
		Tongtienich = tongtienich;
	}

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

	public Date getHanthu() {
		return Hanthu;
	}

	public void setHanthu(Date thoihan) {
		Hanthu = thoihan;
	}

	public int getTongtienich() {
		return Tongtienich;
	}

	public void setTongtienich(int tongtienich) {
		Tongtienich = tongtienich;
	}

}
