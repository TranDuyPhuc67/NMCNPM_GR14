package com.example.model;

public class KHOANDONGGOP {
	private int Iddonggop;
	private String CCCDchuho;
	private String Tenquy;
	private double Sotien;

	public KHOANDONGGOP() {
		super();
	}

	public KHOANDONGGOP(String CCCDchuho, String Tenquy, double Sotien) {
		super();
		this.CCCDchuho = CCCDchuho;
		this.Tenquy = Tenquy;
		this.Sotien = Sotien;
	}

	public KHOANDONGGOP(int Iddonggop, String CCCDchuho, String Tenquy, double Sotien) {
		super();
		this.Iddonggop = Iddonggop;
		this.CCCDchuho = CCCDchuho;
		this.Tenquy = Tenquy;
		this.Sotien = Sotien;
	}

	public int getIddonggop() {
		return Iddonggop;
	}

	public void setIddonggop(int Iddonggop) {
		this.Iddonggop = Iddonggop;
	}

	public String getCCCDchuho() {
		return CCCDchuho;
	}

	public void setCCCDchuho(String CCCDchuho) {
		this.CCCDchuho = CCCDchuho;
	}

	public String getTenquy() {
		return Tenquy;
	}

	public void setTenquy(String Tenquy) {
		this.Tenquy = Tenquy;
	}

	public double getSotien() {
		return Sotien;
	}

	public void setSotien(double Sotien) {
		this.Sotien = Sotien;
	}
}
