package com.example.service;

import java.util.ArrayList;
import com.example.dao.KHOANDONGGOPDao;
import com.example.model.KHOANDONGGOP;

public class KHOANDONGGOPService {

    private KHOANDONGGOPDao khoandonggopDao;

    public KHOANDONGGOPService() {
        this.khoandonggopDao = new KHOANDONGGOPDao();
    }

    public int addKHOANDONGGOP(KHOANDONGGOP khoandonggop) {
        return khoandonggopDao.insert(khoandonggop);
    }

    public int updateKHOANDONGGOP(KHOANDONGGOP khoandonggop) {
        return khoandonggopDao.update(khoandonggop);
    }

    public boolean deleteKHOANDONGGOP(int iddonggop) {
        KHOANDONGGOP khoandonggop = new KHOANDONGGOP();
        khoandonggop.setIddonggop(iddonggop);
        return khoandonggopDao.delete(khoandonggop);
    }

    public ArrayList<KHOANDONGGOP> getAllKHOANDONGGOP() {
        return khoandonggopDao.selectAll();
    }

    public KHOANDONGGOP getKHOANDONGGOPById(int iddonggop) {
        KHOANDONGGOP khoandonggop = new KHOANDONGGOP();
        khoandonggop.setIddonggop(iddonggop);
        return khoandonggopDao.selectById(khoandonggop);
    }

    public ArrayList<KHOANDONGGOP> getKHOANDONGGOPByCondition(String condition) {
        return khoandonggopDao.selectByCondition(condition);
    }

    public ArrayList<KHOANDONGGOP> addMultipleKHOANDONGGOP(ArrayList<KHOANDONGGOP> khoandonggopList) {
        ArrayList<KHOANDONGGOP> failedInserts = new ArrayList<>();
        for (KHOANDONGGOP khoandonggop : khoandonggopList) {
            try {
                khoandonggopDao.insert(khoandonggop);
            } catch (Exception e) {
                failedInserts.add(khoandonggop);
            }
        }
        return failedInserts;
    }

    public ArrayList<KHOANDONGGOP> updateMultipleKHOANDONGGOP(ArrayList<KHOANDONGGOP> khoandonggopList) {
        ArrayList<KHOANDONGGOP> failedUpdates = new ArrayList<>();
        for (KHOANDONGGOP khoandonggop : khoandonggopList) {
            try {
                khoandonggopDao.update(khoandonggop);
            } catch (Exception e) {
                failedUpdates.add(khoandonggop);
            }
        }
        return failedUpdates;
    }
}
