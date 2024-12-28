package com.example.service;

import com.example.dao.TAMVANGDao;
import com.example.model.TAMVANG;
import com.example.dao.NHANKHAUDao;

import java.util.List;

public class TAMVANGService {

    private TAMVANGDao tamvangDao;  
    private NHANKHAUDao nhankhauDao;
    public TAMVANGService() {
        this.tamvangDao = new TAMVANGDao(); 
    }

    public int addTAMVANG(TAMVANG tamvang) {
        nhankhauDao = new NHANKHAUDao();
        nhankhauDao.updateTT(tamvang.getCccd(),"Tạm Vắng");
        return tamvangDao.insert(tamvang); 
    }


    public int updateTAMVANG(TAMVANG tamvang) {
        return tamvangDao.update(tamvang);  
    }


    public boolean deleteTAMVANG(TAMVANG tamvang) {
        nhankhauDao = new NHANKHAUDao();
        nhankhauDao.updateTT(tamvang.getCccd(),"Thường Trú");
        return tamvangDao.delete(tamvang); 
    }

    public List<TAMVANG> getAllTAMVANG() {
        return tamvangDao.selectAll();  
    }
    public List<TAMVANG> getByCondition(String dk) {
        return tamvangDao.selectByCondition(dk);  
    }


}
