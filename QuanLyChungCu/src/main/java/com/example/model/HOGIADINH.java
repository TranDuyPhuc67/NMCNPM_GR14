package com.example.model;

public class HOGIADINH {
	private int Idhogiadinh;
	private String Hotenchuho;

	public HOGIADINH() {
		super();
	}

	public HOGIADINH(String hotenchuho) {
		super();
		Hotenchuho = hotenchuho;
	}

	public HOGIADINH(int idhogiadinh, String hotenchuho) {
		super();
		Idhogiadinh = idhogiadinh;
		Hotenchuho = hotenchuho;
	}

	public int getIdhogiadinh() {
		return Idhogiadinh;
	}

	public void setIdhogiadinh(int idhogiadinh) {
		Idhogiadinh = idhogiadinh;
	}

	public String getHotenchuho() {
		return Hotenchuho;
	}

	public void setHotenchuho(String hotenchuho) {
		Hotenchuho = hotenchuho;
	}

}
