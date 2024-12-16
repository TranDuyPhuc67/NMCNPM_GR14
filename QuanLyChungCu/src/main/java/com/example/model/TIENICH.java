package com.example.model;

import java.math.BigDecimal;

public class TIENICH {
	private int Idtienich;
	private String CCCDchuho;
	private String LoaiTienIch;
	private BigDecimal TongTien;

	public TIENICH() {
		super();
	}

	public TIENICH(int idtienich, String cCCDchuho, String loaiTienIch, BigDecimal tongTien) {
		super();
		Idtienich = idtienich;
		CCCDchuho = cCCDchuho;
		LoaiTienIch = loaiTienIch;
		TongTien = tongTien;
	}

	public TIENICH(String cCCDchuho, String loaiTienIch, BigDecimal tongTien) {
		super();
		CCCDchuho = cCCDchuho;
		LoaiTienIch = loaiTienIch;
		TongTien = tongTien;
	}

	public int getIdtienich() {
		return Idtienich;
	}

	public void setIdtienich(int idtienich) {
		Idtienich = idtienich;
	}

	public String getCCCDchuho() {
		return CCCDchuho;
	}

	public void setCCCDchuho(String cCCDchuho) {
		CCCDchuho = cCCDchuho;
	}

	public String getLoaiTienIch() {
		return LoaiTienIch;
	}

	public void setLoaiTienIch(String loaiTienIch) {
		LoaiTienIch = loaiTienIch;
	}

	public BigDecimal getTongTien() {
		return TongTien;
	}

	public void setTongTien(BigDecimal tongTien) {
		TongTien = tongTien;
	}

}
