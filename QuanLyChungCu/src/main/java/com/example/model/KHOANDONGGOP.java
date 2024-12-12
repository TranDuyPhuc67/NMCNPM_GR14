package com.example.model;

public class KHOANDONGGOP {
	private int Iddonggop;
	private int Idhogiadinh;
	private String Tenquy;
	private double Sotien;

	public KHOANDONGGOP() {
		super();
	}

	public KHOANDONGGOP(int Idhogiadinh, String Tenquy, double Sotien) {
		super();
		this.Idhogiadinh = Idhogiadinh;
		this.Tenquy = Tenquy;
		this.Sotien = Sotien;
	}

	public KHOANDONGGOP(int Iddonggop, int Idhogiadinh, String Tenquy, double Sotien) {
		super();
		this.Iddonggop = Iddonggop;
		this.Idhogiadinh = Idhogiadinh;
		this.Tenquy = Tenquy;
		this.Sotien = Sotien;
	}

	public int getIddonggop() {
		return Iddonggop;
	}

	public void setIddonggop(int Iddonggop) {
		this.Iddonggop = Iddonggop;
	}

	public int getIdhogiadinh() {
		return Idhogiadinh;
	}

	public void setIdhogiadinh(int Idhogiadinh) {
		this.Idhogiadinh = Idhogiadinh;
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
