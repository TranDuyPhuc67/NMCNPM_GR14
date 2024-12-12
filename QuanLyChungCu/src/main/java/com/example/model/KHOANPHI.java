package com.example.model;

public class KHOANPHI {
	private int Idhogiadinh;
	private int Idcanho;
	private double Phidichvu;
	private double Phiquanly;

	public KHOANPHI() {
		super();
	}

	public KHOANPHI(int idhogiadinh, int idcanho, double phidichvu, double phiquanly) {
		super();
		Idhogiadinh = idhogiadinh;
		Idcanho = idcanho;
		Phidichvu = phidichvu;
		Phiquanly = phiquanly;
	}

	public int getIdhogiadinh() {
		return Idhogiadinh;
	}

	public void setIdhogiadinh(int idhogiadinh) {
		Idhogiadinh = idhogiadinh;
	}

	public int getIdcanho() {
		return Idcanho;
	}

	public void setIdcanho(int idcanho) {
		Idcanho = idcanho;
	}

	public double getPhidichvu() {
		return Phidichvu;
	}

	public void setPhidichvu(double phidichvu) {
		Phidichvu = phidichvu;
	}

	public double getPhiquanly() {
		return Phiquanly;
	}

	public void setPhiquanly(double phiquanly) {
		Phiquanly = phiquanly;
	}

}
