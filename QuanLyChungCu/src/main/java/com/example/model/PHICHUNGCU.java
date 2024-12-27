package com.example.model;

import java.sql.Date;

public class PHICHUNGCU {
    private int Idcanho;          // ID của căn hộ
    private int Phidichvu;        // Phí dịch vụ
    private int Phiquanly;        // Phí quản lý
    private int Tongphichungcu;   // Tổng phí chung cư
    private String Hanthu;        // Hạn thu (định dạng YYYY-MM)
    private Date Thoigianthu;
    
    public PHICHUNGCU(int idcanho, int phidichvu, int phiquanly, int tongphichungcu, String hanthu, Date thoigianthu) {
		super();
		Idcanho = idcanho;
		Phidichvu = phidichvu;
		Phiquanly = phiquanly;
		Tongphichungcu = tongphichungcu;
		Hanthu = hanthu;
		Thoigianthu = thoigianthu;
	}

	// Constructor đầy đủ
    public PHICHUNGCU(int idcanho, int phidichvu, int phiquanly, int tongphichungcu, String hanthu) {
        super();
        Idcanho = idcanho;
        Phidichvu = phidichvu;
        Phiquanly = phiquanly;
        Tongphichungcu = tongphichungcu;
        Hanthu = hanthu;
    }

    // Constructor không có tổng phí (để tổng phí được tính toán sau)
    public PHICHUNGCU(int idcanho, int phidichvu, int phiquanly, String hanthu) {
        super();
        Idcanho = idcanho;
        Phidichvu = phidichvu;
        Phiquanly = phiquanly;
        Tongphichungcu = phidichvu + phiquanly; // Tính tổng phí tự động
        Hanthu = hanthu;
    }

    // Constructor mặc định
    public PHICHUNGCU() {
        super();
    }

    // Getter và Setter
    public int getIdcanho() {
        return Idcanho;
    }

    public void setIdcanho(int idcanho) {
        Idcanho = idcanho;
    }

    public int getPhidichvu() {
        return Phidichvu;
    }

    public void setPhidichvu(int phidichvu) {
        Phidichvu = phidichvu;
    }

    public int getPhiquanly() {
        return Phiquanly;
    }

    public void setPhiquanly(int phiquanly) {
        Phiquanly = phiquanly;
    }

    public int getTongphichungcu() {
        return Tongphichungcu;
    }

    public void setTongphichungcu(int tongphichungcu) {
        Tongphichungcu = tongphichungcu;
    }

    public String getHanthu() {
        return Hanthu;
    }

    public void setHanthu(String hanthu) {
        Hanthu = hanthu;
    }

    public Date getThoigianthu() {
		return Thoigianthu;
	}

	public void setThoigianthu(Date thoigianthu) {
		Thoigianthu = thoigianthu;
	}

	// Phương thức tính lại tổng phí
    public void tinhTongphichungcu() {
        this.Tongphichungcu = this.Phidichvu + this.Phiquanly;
    }
}
