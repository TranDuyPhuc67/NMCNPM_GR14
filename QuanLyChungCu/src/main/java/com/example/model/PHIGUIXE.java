package com.example.model;

import java.sql.Date;

public class PHIGUIXE {
	private String CCCDchuho;
	private int Soxemay;
	private int Sooto;
	private Date Thoihan;

	public PHIGUIXE() {
		super();
	}

	public PHIGUIXE(String cCCDchuho, int soxemay, int sooto, Date thoihan) {
		super();
		CCCDchuho = cCCDchuho;
		Soxemay = soxemay;
		Sooto = sooto;
		Thoihan = thoihan;
	}

	public String getCCCDchuho() {
		return CCCDchuho;
	}

	public void setCCCDchuho(String cCCDchuho) {
		CCCDchuho = cCCDchuho;
	}

	public int getSoxemay() {
		return Soxemay;
	}

	public void setSoxemay(int soxemay) {
		Soxemay = soxemay;
	}

	public int getSooto() {
		return Sooto;
	}

	public void setSooto(int sooto) {
		Sooto = sooto;
	}

	public Date getThoihan() {
		return Thoihan;
	}

	public void setThoihan(Date thoihan) {
		Thoihan = thoihan;
	}

}
