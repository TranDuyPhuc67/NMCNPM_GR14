package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.dao.TIENICHDao;
import com.example.model.TIENICH;

public class TIENICHService {

    private TIENICHDao tienichDao;

    public TIENICHService() {
        this.tienichDao = new TIENICHDao();
    }

    // Thêm tiện ích
    public int addTienIch(TIENICH tienich) {
        validateTienIch(tienich);
        try {
            if (tienichDao.checkExists(tienich.getIdcanho(), tienich.getHanthu())) {
                throw new IllegalArgumentException("Tiện ích đã tồn tại cho Idcanho và Hanthu này.");
            }
            return tienichDao.insert(tienich);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi thêm tiện ích: " + e.getMessage(), e);
        }
    }

    // Cập nhật tiện ích
    public int updateTienIch(TIENICH tienich) {
        validateTienIch(tienich);
        try {
            if (!tienichDao.checkExists(tienich.getIdcanho(), tienich.getHanthu())) {
                throw new IllegalArgumentException("Không tìm thấy tiện ích để cập nhật.");
            }
            return tienichDao.update(tienich);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi cập nhật tiện ích: " + e.getMessage(), e);
        }
    }

    // Xóa tiện ích
    public boolean deleteTienIch(int idcanho, String hanthu) {
        validateIds(idcanho, hanthu);
        try {
            if (!tienichDao.checkExists(idcanho, hanthu)) {
                throw new IllegalArgumentException("Không tìm thấy tiện ích để xóa.");
            }
            TIENICH tienich = new TIENICH();
            tienich.setIdcanho(idcanho);
            tienich.setHanthu(hanthu);
            return tienichDao.delete(tienich);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi xóa tiện ích: " + e.getMessage(), e);
        }
    }

    // Lấy tất cả tiện ích
    public ArrayList<TIENICH> getAllTienIch() {
        try {
            return tienichDao.selectAll();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách tiện ích: " + e.getMessage(), e);
        }
    }

    // Lấy tất cả tiện ích kèm thông tin chủ hộ
    public List<Map<String, Object>> getAllTienIchWithChuHo() {
        try {
            return tienichDao.getAllTienIchWithChuHo();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách tiện ích với chủ hộ: " + e.getMessage(), e);
        }
    }

    // Kiểm tra tiện ích tồn tại
    public boolean checkExists(int idcanho, String hanthu) {
        validateIds(idcanho, hanthu);
        try {
            return tienichDao.checkExists(idcanho, hanthu);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi kiểm tra tiện ích tồn tại: " + e.getMessage(), e);
        }
    }

    // Tìm kiếm tiện ích theo điều kiện
    public List<Map<String, Object>> searchTienIch(String sonha, String tenChuHo, Integer month, Integer year) {
        try {
            return tienichDao.searchTienIch(sonha, tenChuHo, month, year);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm kiếm tiện ích: " + e.getMessage(), e);
        }
    }

    // Phương thức validate các trường của TIENICH
    private void validateTienIch(TIENICH tienich) {
        if (tienich == null) {
            throw new IllegalArgumentException("Dữ liệu tiện ích không được null.");
        }
        if (tienich.getIdcanho() <= 0 || tienich.getHanthu() == null || tienich.getHanthu().isEmpty()) {
            throw new IllegalArgumentException("Idcanho và Hanthu không được để trống hoặc không hợp lệ.");
        }
        if (tienich.getSodien() < 0 || tienich.getSonuoc() < 0 || tienich.getInternet() < 0) {
            throw new IllegalArgumentException("Số điện, nước, internet không được âm.");
        }
    }

    // Phương thức validate Idcanho và Hanthu
    private void validateIds(int idcanho, String hanthu) {
        if (idcanho <= 0 || hanthu == null || hanthu.isEmpty()) {
            throw new IllegalArgumentException("Idcanho và Hanthu không được để trống hoặc không hợp lệ.");
        }
    }
}
