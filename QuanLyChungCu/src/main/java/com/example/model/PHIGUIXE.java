package com.example.model;

public class PHIGUIXE {
    private int Idcanho;
    private int Tienxemay;
    private int Tienxeoto;
    private String Hanthu; 
    private int Tongguixe;

    public PHIGUIXE(int idcanho, int tienxemay, int tienxeoto, String hanthu, int tongguixe) {
        super();
        Idcanho = idcanho;
        Tienxemay = tienxemay;
        Tienxeoto = tienxeoto;
        Hanthu = hanthu;
        Tongguixe = tongguixe;
    }

    public PHIGUIXE() {
        super();
    }

    public int getIdcanho() {
        return Idcanho;
    }

    public void setIdcanho(int idcanho) {
        Idcanho = idcanho;
    }

    public int getTienxemay() {
        return Tienxemay;
    }

    public void setTienxemay(int tienxemay) {
        Tienxemay = tienxemay;
    }

    public int getTienxeoto() {
        return Tienxeoto;
    }

    public void setTienxeoto(int tienxeoto) {
        Tienxeoto = tienxeoto;
    }

    public String getHanthu() {
        return Hanthu;
    }

    public void setHanthu(String hanthu) {
        Hanthu = hanthu;
    }

    public int getTongguixe() {
        return Tongguixe;
    }

    public void setTongguixe(int tongguixe) {
        Tongguixe = tongguixe;
    }
}
