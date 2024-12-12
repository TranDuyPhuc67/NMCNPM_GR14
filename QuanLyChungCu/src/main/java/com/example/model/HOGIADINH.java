package com.example.model;

public class HOGIADINH {
	private int Idhogiadinh;
	private String Hotenchuho;

	public HOGIADINH() {
		super();
	}

	public HOGIADINH(int idhogiadinh, String hotenchuho) {
		this.Idhogiadinh = idhogiadinh;
		this.Hotenchuho = hotenchuho;
	}

	public HOGIADINH(String hotenchuho) {
		this.Hotenchuho = hotenchuho;
	}

	public int getIdhogiadinh() {
		return Idhogiadinh;
	}

	public void setIdhogiadinh(int idhogiadinh) {
		this.Idhogiadinh = idhogiadinh;
	}

	public String getHotenchuho() {
		return Hotenchuho;
	}

	public void setHotenchuho(String hotenchuho) {
		this.Hotenchuho = hotenchuho;
	}
}
