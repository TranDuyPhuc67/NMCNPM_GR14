package com.example.service;

import java.sql.Date;
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
            System.out.println("Thêm tiện ích: " + tienich);
            return tienichDao.insert(tienich);
        } catch (Exception e) {
            System.err.println("Lỗi khi thêm tiện ích: " + e.getMessage());
            throw e;
        }
    }

    // Cập nhật tiện ích
    public int updateTienIch(TIENICH tienich) {
        validateTienIch(tienich);
        try {
            if (!tienichDao.checkExists(tienich.getIdcanho(), tienich.getHanthu())) {
                throw new IllegalArgumentException("Không tìm thấy tiện ích để cập nhật.");
            }
            System.out.println("Cập nhật tiện ích: " + tienich);
            return tienichDao.update(tienich);
        } catch (Exception e) {
            System.err.println("Lỗi khi cập nhật tiện ích: " + e.getMessage());
            throw e;
        }
    }

    // Xóa tiện ích
    public boolean deleteTienIch(int idcanho, Date hanthu) {
        validateIds(idcanho, hanthu);
        try {
            if (!tienichDao.checkExists(idcanho, hanthu)) {
                throw new IllegalArgumentException("Không tìm thấy tiện ích để xóa.");
            }
            System.out.println("Xóa tiện ích với Idcanho: " + idcanho + ", Hanthu: " + hanthu);
            TIENICH tienich = new TIENICH();
            tienich.setIdcanho(idcanho);
            tienich.setHanthu(hanthu);
            return tienichDao.delete(tienich);
        } catch (Exception e) {
            System.err.println("Lỗi khi xóa tiện ích: " + e.getMessage());
            throw e;
        }
    }

    // Lấy tất cả tiện ích
    public ArrayList<TIENICH> getAllTienIch() {
        try {
            return tienichDao.selectAll();
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách tiện ích: " + e.getMessage());
            throw e;
        }
    }

    // Lấy tiện ích theo Id căn hộ
    public TIENICH getTienIchByIdcanho(int idcanho) {
        try {
            if (idcanho <= 0) {
                throw new IllegalArgumentException("Idcanho không hợp lệ.");
            }
            TIENICH tienich = new TIENICH();
            tienich.setIdcanho(idcanho);
            return tienichDao.selectById(tienich);
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy tiện ích theo Idcanho: " + e.getMessage());
            throw e;
        }
    }

    // Lấy tất cả tiện ích kèm theo thông tin chủ hộ
    public List<Map<String, Object>> getAllTienIchWithChuHo() {
        try {
            List<Map<String, Object>> result = tienichDao.getAllTienIchWithChuHo();
            if (result == null || result.isEmpty()) {
                System.out.println("Danh sách tiện ích trống hoặc không tồn tại dữ liệu.");
            }
            return result;
        } catch (Exception e) {
            System.err.println("Lỗi khi lấy danh sách tiện ích với chủ hộ: " + e.getMessage());
            throw e;
        }
    }

    // Kiểm tra tiện ích tồn tại
    public boolean checkExists(int idcanho, Date hanthu) {
        validateIds(idcanho, hanthu);
        try {
            return tienichDao.checkExists(idcanho, hanthu);
        } catch (Exception e) {
            System.err.println("Lỗi khi kiểm tra tiện ích tồn tại: " + e.getMessage());
            throw e;
        }
    }

    // Phương thức validate các trường của TIENICH
    private void validateTienIch(TIENICH tienich) {
        if (tienich == null) {
            throw new IllegalArgumentException("Dữ liệu tiện ích không được null.");
        }
        if (tienich.getIdcanho() <= 0 || tienich.getHanthu() == null) {
            throw new IllegalArgumentException("Idcanho và Hanthu không được để trống hoặc không hợp lệ.");
        }
        if (tienich.getSodien() < 0 || tienich.getSonuoc() < 0 || tienich.getInternet() < 0) {
            throw new IllegalArgumentException("Số điện, nước, internet không được âm.");
        }
    }

    // Phương thức validate Idcanho và Hanthu
    private void validateIds(int idcanho, Date hanthu) {
        if (idcanho <= 0 || hanthu == null) {
            throw new IllegalArgumentException("Idcanho và Hanthu không được để trống hoặc không hợp lệ.");
        }
    }
    
    public List<Map<String, Object>> searchTienIch(String sonha, String tenChuHo, Integer day, Integer month, Integer year) {
        return tienichDao.searchTienIch(sonha, tenChuHo, day, month, year);
    }

}
