package com.example.service;

import com.example.dao.*;
import com.example.model.NHANKHAU;

import java.util.List;

public class NHANKHAUService {

    private NHANKHAUDao nhankhauDao;  
    private HOGIADINHDao hogiadinhDao;
    public NHANKHAUService() {
        this.nhankhauDao = new NHANKHAUDao(); 
    }

    public int addNHANKHAU(NHANKHAU nhankhau) {
        hogiadinhDao = new HOGIADINHDao();
        nhankhauDao.insert(nhankhau);
        return hogiadinhDao.updateNK(nhankhau.getCCCDchuho());
    }


    public int updateNHANKHAU(NHANKHAU nhankhau) {
        return nhankhauDao.update(nhankhau);  
    }


    public boolean deleteNHANKHAU(NHANKHAU nhankhau) {
        hogiadinhDao = new HOGIADINHDao();
        nhankhauDao.delete(nhankhau);
        hogiadinhDao.updateNK(nhankhau.getCCCDchuho());
        return true;
    }
    public NHANKHAU getNHANKHAU(NHANKHAU nhankhau) {
        return nhankhauDao.selectById(nhankhau);  
    }

    public List<NHANKHAU> getAllNHANKHAU() {
        return nhankhauDao.selectAll();  
    }

    public List<NHANKHAU> getAllNHANKHAU(String cccdChuHo) {
        return nhankhauDao.selectAll(cccdChuHo);  
    }
    public List<NHANKHAU> getAllNHANKHAUMCH(String macanho) {
        return nhankhauDao.selectAllMCH(macanho);  
    }

}
