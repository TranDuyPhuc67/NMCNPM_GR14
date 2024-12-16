package com.example.model;

import java.math.BigDecimal;

public class PHIGUIXE {
	private String CCCDchuho;
	private BigDecimal Phixemay;
	private BigDecimal Phioto;

	public PHIGUIXE() {
		super();
	}

	public PHIGUIXE(String cCCDchuho, BigDecimal phixemay, BigDecimal phioto) {
		super();
		CCCDchuho = cCCDchuho;
		Phixemay = phixemay;
		Phioto = phioto;
	}

	public String getCCCDchuho() {
		return CCCDchuho;
	}

	public void setCCCDchuho(String cCCDchuho) {
		CCCDchuho = cCCDchuho;
	}

	public BigDecimal getPhixemay() {
		return Phixemay;
	}

	public void setPhixemay(BigDecimal phixemay) {
		Phixemay = phixemay;
	}

	public BigDecimal getPhioto() {
		return Phioto;
	}

	public void setPhioto(BigDecimal phioto) {
		Phioto = phioto;
	}
}
