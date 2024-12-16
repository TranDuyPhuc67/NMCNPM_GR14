package com.example.model;

public class CANHO {
	private int Idcanho;
	private String CCCDchuho;
	private String Sonha;
	private String Loaicanho;
	private double Dientich;

	public CANHO() {
		super();
	}

	public CANHO(String cCCDchuho, String sonha, String loaicanho, double dientich) {
		super();
		CCCDchuho = cCCDchuho;
		Sonha = sonha;
		Loaicanho = loaicanho;
		Dientich = dientich;
	}

	public CANHO(int idcanho, String cCCDchuho, String sonha, String loaicanho, double dientich) {
		super();
		Idcanho = idcanho;
		CCCDchuho = cCCDchuho;
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

	public String getCCCDchuho() {
		return CCCDchuho;
	}

	public void setCCCDchuho(String cCCDchuho) {
		CCCDchuho = cCCDchuho;
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
