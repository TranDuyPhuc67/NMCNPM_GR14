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

	public CANHO(int idcanho, int idhogiadinh, String sonha, String loaicanho, double dientich) {
		super();
		Idcanho = idcanho;
		Idhogiadinh = idhogiadinh;
		Sonha = sonha;
		Loaicanho = loaicanho;
		Dientich = dientich;
	}

	public CANHO(int idhogiadinh, String sonha, String loaicanho, double dientich) {
		super();
		Idhogiadinh = idhogiadinh;
		Sonha = sonha;
		Loaicanho = loaicanho;
		Dientich = dientich;
	}

	public int getIdcanho() {
		return Idcanho;
	}

	public void setIdcanho(int idcanho) {
		Idcanho = idcanho;
	}

	public int getIdhogiadinh() {
		return Idhogiadinh;
	}

	public void setIdhogiadinh(int idhogiadinh) {
		Idhogiadinh = idhogiadinh;
	}

	public String getSonha() {
		return Sonha;
	}

	public void setSonha(String sonha) {
		Sonha = sonha;
	}

	public String getLoaicanho() {
		return Loaicanho;
	}

	public void setLoaicanho(String loaicanho) {
		Loaicanho = loaicanho;
	}

	public double getDientich() {
		return Dientich;
	}

	public void setDientich(double dientich) {
		Dientich = dientich;
	}

}
