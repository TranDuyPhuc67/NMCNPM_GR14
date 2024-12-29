package com.example.service;

import com.example.dao.NHANKHAUDao;
import com.example.model.NHANKHAU;
import java.util.List;

public class NHANKHAUService {

    private NHANKHAUDao nhankhauDao;  

    public NHANKHAUService() {
        this.nhankhauDao = new NHANKHAUDao(); 
    }

    public int addNHANKHAU(NHANKHAU nhankhau) {
        return nhankhauDao.insert(nhankhau); 
    }


    public int updateNHANKHAU(NHANKHAU nhankhau) {
        return nhankhauDao.update(nhankhau);  
    }


    public boolean deleteNHANKHAU(NHANKHAU nhankhau) {
        return nhankhauDao.delete(nhankhau); 
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
