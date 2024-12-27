package com.example.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

import com.example.dao.PHICHUNGCUDao;
import com.example.model.PHICHUNGCU;

public class PHICHUNGCUService {

    private final PHICHUNGCUDao phichungcuDao;

    public PHICHUNGCUService() {
        this.phichungcuDao = new PHICHUNGCUDao();
    }

    public boolean addPHICHUNGCU(PHICHUNGCU phi) {
        validatePhiChungCu(phi);
        int result = phichungcuDao.insert(phi);
        return result > 0;
    }

    public boolean updatePHICHUNGCU(PHICHUNGCU phi) {
        validatePhiChungCu(phi);
        int result = phichungcuDao.update(phi);
        return result > 0;
    }

    public boolean deletePHICHUNGCU(int idCanHo, String hanthu) {
        validateHanThu(hanthu);
        PHICHUNGCU phi = new PHICHUNGCU(idCanHo, 0, 0, 0, hanthu);
        return phichungcuDao.delete(phi);
    }

    public ArrayList<PHICHUNGCU> getAllPHICHUNGCU() {
        ArrayList<PHICHUNGCU> phichungList = phichungcuDao.selectAll();
        if (phichungList.isEmpty()) {
            System.out.println("Danh sách phí chung cư hiện đang trống.");
        }
        return phichungList;
    }

    public PHICHUNGCU getPHICHUNGCUById(int idCanHo, String hanthu) {
        validateHanThu(hanthu);
        PHICHUNGCU phi = phichungcuDao.selectById(new PHICHUNGCU(idCanHo, 0, 0, 0, hanthu));
        if (phi == null) {
            throw new RuntimeException("Không tìm thấy phí chung cư với IdCanHo: " + idCanHo + " và Hanthu: " + hanthu);
        }
        return phi;
    }

    public int applyFeeForAll(int phidichvum2, int phiquanlym2, String hanthu, Date thoigianthu) {
        if (phidichvum2 < 0 || phiquanlym2 < 0) {
            throw new IllegalArgumentException("Phí dịch vụ hoặc phí quản lý không được nhỏ hơn 0.");
        }
        validateHanThu(hanthu);
        return phichungcuDao.applyFeeForAll(phidichvum2, phiquanlym2, hanthu, thoigianthu);
    }

    public ArrayList<Map<String, Object>> searchPHICHUNGCU(String tenChuHo, String sonha, Integer month, Integer year) {

        ArrayList<Map<String, Object>> results = phichungcuDao.searchPHICHUNGCU(tenChuHo, sonha, month, year);
        if (results.isEmpty()) {
            System.out.println("Không tìm thấy kết quả phù hợp.");
        }

        return results;
    }

    private void validatePhiChungCu(PHICHUNGCU phi) {
        if (phi.getTongphichungcu() < 0) {
            throw new IllegalArgumentException("Tổng phí chung cư không được nhỏ hơn 0.");
        }
        validateHanThu(phi.getHanthu());
    }

    private void validateHanThu(String hanthu) {
        if (hanthu == null || hanthu.trim().isEmpty()) {
            throw new IllegalArgumentException("Hạn thu không được để trống.");
        }
        if (!hanthu.matches("\\d{4}-\\d{2}")) {
            throw new IllegalArgumentException("Hạn thu phải đúng định dạng YYYY-MM.");
        }
    }
}
