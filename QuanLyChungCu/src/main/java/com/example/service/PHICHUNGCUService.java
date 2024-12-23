package com.example.service;

import com.example.dao.PHICHUNGCUDao;
import com.example.model.PHICHUNGCU;

import java.util.ArrayList;

public class PHICHUNGCUService {

    private PHICHUNGCUDao phichungcuDao;

    public PHICHUNGCUService() {
        this.phichungcuDao = new PHICHUNGCUDao();
    }

    // Thêm mới PHICHUNGCU
    public boolean addPHICHUNGCU(PHICHUNGCU phi) {
        int result = phichungcuDao.insert(phi);
        return result > 0;
    }

    // Cập nhật PHICHUNGCU
    public boolean updatePHICHUNGCU(PHICHUNGCU phi) {
        int result = phichungcuDao.update(phi);
        return result > 0;
    }

    // Xóa PHICHUNGCU
    public boolean deletePHICHUNGCU(int idCanHo) {
        PHICHUNGCU phi = new PHICHUNGCU(idCanHo, null, 0);
        return phichungcuDao.delete(phi);
    }

    // Lấy danh sách tất cả PHICHUNGCU
    public ArrayList<PHICHUNGCU> getAllPHICHUNGCU() {
        return phichungcuDao.selectAll();
    }

    // Lấy thông tin PHICHUNGCU theo ID
    public PHICHUNGCU getPHICHUNGCUById(int idCanHo) {
        return phichungcuDao.selectById(new PHICHUNGCU(idCanHo, null, 0));
    }

    // Tìm kiếm PHICHUNGCU theo điều kiện
    public ArrayList<PHICHUNGCU> searchPHICHUNGCUByCondition(String condition) {
        return phichungcuDao.selectByCondition(condition);
    }
}
