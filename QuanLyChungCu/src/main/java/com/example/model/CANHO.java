package com.example.model;

public class CANHO {
	private int Idcanho;
	private String Sonha;
	private String Loaicanho;
	private double Dientich;
	private String Diachi;

	public CANHO() {
		super();
	}

	public CANHO(String sonha, String loaicanho, double dientich,String diachi) {
		super();
		Sonha = sonha;
		Loaicanho = loaicanho;
		Dientich = dientich;
		Diachi = diachi;
	}

	public CANHO(int idcanho, String sonha, String loaicanho, double dientich,String diachi) {
		super();
		Idcanho = idcanho;
		Sonha = sonha;
		Loaicanho = loaicanho;
		Dientich = dientich;
		Diachi = diachi;
	}

	public int getIdcanho() {
		return Idcanho;
	}

	public void setIdcanho(int idcanho) {
		Idcanho = idcanho;
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
	public String getDiachi() {
		return Diachi;
	}
	public void setDiachi(String diachi) {
		Diachi = diachi;
	}

}
