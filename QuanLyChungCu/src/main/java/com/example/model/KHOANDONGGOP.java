package com.example.model;

public class KHOANDONGGOP {
	private int Idhogiadinh;
	private double Quyvinguoingheo;
	private double QuyvibiendaoVN;
	private double Quyvitretho;
	private double Quytuthien;

	public KHOANDONGGOP() {
		super();
	}

	public KHOANDONGGOP(int idhogiadinh, double quyvinguoingheo, double quyvibiendaoVN, double quyvitretho,
			double quytuthien) {
		super();
		Idhogiadinh = idhogiadinh;
		Quyvinguoingheo = quyvinguoingheo;
		QuyvibiendaoVN = quyvibiendaoVN;
		Quyvitretho = quyvitretho;
		Quytuthien = quytuthien;
	}

	public int getIdhogiadinh() {
		return Idhogiadinh;
	}

	public void setIdhogiadinh(int idhogiadinh) {
		Idhogiadinh = idhogiadinh;
	}

	public double getQuyvinguoingheo() {
		return Quyvinguoingheo;
	}

	public void setQuyvinguoingheo(double quyvinguoingheo) {
		Quyvinguoingheo = quyvinguoingheo;
	}

	public double getQuyvibiendaoVN() {
		return QuyvibiendaoVN;
	}

	public void setQuyvibiendaoVN(double quyvibiendaoVN) {
		QuyvibiendaoVN = quyvibiendaoVN;
	}

	public double getQuyvitretho() {
		return Quyvitretho;
	}

	public void setQuyvitretho(double quyvitretho) {
		Quyvitretho = quyvitretho;
	}

	public double getQuytuthien() {
		return Quytuthien;
	}

	public void setQuytuthien(double quytuthien) {
		Quytuthien = quytuthien;
	}

}
