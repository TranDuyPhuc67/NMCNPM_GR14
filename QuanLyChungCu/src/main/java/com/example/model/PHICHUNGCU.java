package com.example.model;

import java.sql.Date;

public class PHICHUNGCU {
	private int Idcanho;
	private Date Hanthu;
	private int Tongphichungcu;

	public PHICHUNGCU(int idcanho, Date hanthu, int tongphichungcu) {
		super();
		Idcanho = idcanho;
		Hanthu = hanthu;
		Tongphichungcu = tongphichungcu;
	}

	public int getIdcanho() {
		return Idcanho;
	}

	public void setIdcanho(int idcanho) {
		Idcanho = idcanho;
	}

	public Date getHanthu() {
		return Hanthu;
	}

	public void setHanthu(Date hanthu) {
		Hanthu = hanthu;
	}

	public int getTongphichungcu() {
		return Tongphichungcu;
	}

	public void setTongphichungcu(int tongphichungcu) {
		Tongphichungcu = tongphichungcu;
	}

}
