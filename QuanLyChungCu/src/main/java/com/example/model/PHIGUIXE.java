package com.example.model;

public class PHIGUIXE {
	private int Idhogiadinh;
	private double Phixemay;
	private double Phioto;

	public PHIGUIXE() {
		super();
	}

	public PHIGUIXE(int idhogiadinh, double phixemay, double phioto) {
		super();
		Idhogiadinh = idhogiadinh;
		Phixemay = phixemay;
		Phioto = phioto;
	}

	public int getIdhogiadinh() {
		return Idhogiadinh;
	}

	public void setIdhogiadinh(int idhogiadinh) {
		Idhogiadinh = idhogiadinh;
	}

	public double getPhixemay() {
		return Phixemay;
	}

	public void setPhixemay(double phixemay) {
		Phixemay = phixemay;
	}

	public double getPhioto() {
		return Phioto;
	}

	public void setPhioto(double phioto) {
		Phioto = phioto;
	}

}
