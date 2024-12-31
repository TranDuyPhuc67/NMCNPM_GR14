package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.dao.TONGTHANHTOANDao;

public class TONGTHANHTOANService {
    private final TONGTHANHTOANDao dao;

    public TONGTHANHTOANService() {
        this.dao = new TONGTHANHTOANDao();
    }

    public List<Map<String, Object>> search(String sonha, String hotenchuho, String month, String year) {
        System.out.println("Executing search with parameters: sonha=" + sonha + ", hotenchuho=" + hotenchuho + ", month=" + month + ", year=" + year);
        return dao.search(sonha, hotenchuho, month, year);
    }

    public List<Map<String, Object>> getAll() {
        try {
            System.out.println("Fetching all payment data");
            return dao.getAll();
        } catch (Exception e) {
            System.err.println("Error while fetching all payment data: " + e.getMessage());
            throw new RuntimeException("Không thể lấy dữ liệu thanh toán. Vui lòng thử lại sau.");
        }
    }

    public void insertOrUpdateTongThanhToan() {
        try {
            System.out.println("Inserting or updating payment data");
            dao.insertOrUpdateTongThanhToan();
        } catch (Exception e) {
            System.err.println("Error while inserting or updating payment data: " + e.getMessage());
            throw new RuntimeException("Không thể cập nhật dữ liệu thanh toán. Vui lòng thử lại sau.");
        }
    }

    public void updateSoTienDaNopForCurrentMonth(int idCanHo, String hanThu, int soTienDaNop) {
        if (hanThu == null || !hanThu.matches("\\d{4}-\\d{2}")) {
            throw new IllegalArgumentException("HanThu phải có định dạng YYYY-MM");
        }

        if (soTienDaNop < 0) {
            throw new IllegalArgumentException("SoTienDaNop phải lớn hơn 0");
        }

        try {
            System.out.println("Updating payment data for IdCanHo=" + idCanHo + ", HanThu=" + hanThu + ", SoTienDaNop=" + soTienDaNop);
            dao.updateSoTienDaNopForCurrentMonth(idCanHo, hanThu, soTienDaNop);
        } catch (Exception e) {
            System.err.println("Error while updating payment data: " + e.getMessage());
            throw new RuntimeException("Không thể cập nhật số tiền đã nộp. Vui lòng thử lại sau.");
        }
    }

    public String getTrangThai(int idCanHo, String hanThu) {
        System.out.println("Fetching payment status for IdCanHo=" + idCanHo + ", HanThu=" + hanThu);
        return dao.getTrangThai(idCanHo, hanThu);
    }

    public void ensureRecordExists(int idCanHo, String hanThu) {
        System.out.println("Ensuring record exists for IdCanHo=" + idCanHo + ", HanThu=" + hanThu);
        dao.insertOrUpdateTongThanhToan();
    }
}
