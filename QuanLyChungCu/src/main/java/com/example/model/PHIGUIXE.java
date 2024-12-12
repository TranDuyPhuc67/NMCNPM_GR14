package com.example.model;

import java.math.BigDecimal;

public class PHIGUIXE {
    private Integer Idhogiadinh; 
    private BigDecimal Phixemay;  
    private BigDecimal Phioto;    

    public PHIGUIXE() {
        super();
    }

    public PHIGUIXE(Integer idhogiadinh, BigDecimal phixemay, BigDecimal phioto) {
        super();
        Idhogiadinh = idhogiadinh;
        Phixemay = phixemay;
        Phioto = phioto;
    }

    public Integer getIdhogiadinh() {
        return Idhogiadinh;
    }

    public void setIdhogiadinh(Integer idhogiadinh) {
        Idhogiadinh = idhogiadinh;
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
