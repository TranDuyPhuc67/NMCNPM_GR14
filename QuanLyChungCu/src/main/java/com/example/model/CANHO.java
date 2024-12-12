package com.example.model;

public class CANHO {
	private int Idcanho;
	private int Idhogiadinh;
	private String Sonha;
	private String Loaicanho;
	private double Dientich;

	public CANHO() {
		super();
	}

	public CANHO(int Idhogiadinh, String Sonha, String Loaicanho, double Dientich) {
		this.Idhogiadinh = Idhogiadinh;
		this.Sonha = Sonha;
		this.Loaicanho = Loaicanho;
		this.Dientich = Dientich;
	}

	public CANHO(int idcanho, int idhogiadinh, String sonha, String loaicanho, double dientich) {
		super();
		Idcanho = idcanho;
		Idhogiadinh = idhogiadinh;
		Sonha = sonha;
		Loaicanho = loaicanho;
		Dientich = dientich;
	}

	public int getIdcanho() {
		return Idcanho;
	}

	public void setIdcanho(int Idcanho) {
		this.Idcanho = Idcanho;
	}

	public int getIdhogiadinh() {
		return Idhogiadinh;
	}

	public void setIdhogiadinh(int Idhogiadinh) {
		this.Idhogiadinh = Idhogiadinh;
	}

	public String getSonha() {
		return Sonha;
	}

	public void setSonha(String Sonha) {
		this.Sonha = Sonha;
	}

	public String getLoaicanho() {
		return Loaicanho;
	}

	public void setLoaicanho(String Loaicanho) {
		this.Loaicanho = Loaicanho;
	}

	public double getDientich() {
		return Dientich;
	}

	public void setDientich(double Dientich) {
		this.Dientich = Dientich;
	}
}
