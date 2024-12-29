package com.example.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

import com.example.dao.KHOANDONGGOPDao;
import com.example.model.KHOANDONGGOP;

public class KHOANDONGGOPService {

	private KHOANDONGGOPDao khoandonggopDao;

	public KHOANDONGGOPService() {
		this.khoandonggopDao = new KHOANDONGGOPDao();
	}

	public void updateKHOANDONGGOP(KHOANDONGGOP donggop) {
		khoandonggopDao.update(donggop);
	}

	public ArrayList<Map<String, Object>> getAllDetails() {
		return khoandonggopDao.getAllWithDetails();
	}

	public void synchronizeData() {
		khoandonggopDao.synchronizeKHOANDONGGOP();
	}
    public ArrayList<Map<String, Object>> searchKHOANDONGGOP(String sonha, String tenChuHo) {
        return khoandonggopDao.searchKHOANDONGGOP(sonha, tenChuHo);
    }
    public int updateAllDateColumns(Date newDate) {
        if (newDate == null) {
            throw new IllegalArgumentException("Ngày thu không được để trống.");
        }

        return khoandonggopDao.updateAllDateColumns(newDate);
    }
}
