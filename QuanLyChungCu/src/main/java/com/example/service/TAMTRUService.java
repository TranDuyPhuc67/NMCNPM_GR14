package com.example.service;

import com.example.dao.TAMTRUDao;
import com.example.model.TAMTRU;
import com.example.dao.NHANKHAUDao;

import java.util.List;

public class TAMTRUService {

    private TAMTRUDao tamtruDao;  
    private NHANKHAUDao nhankhauDao;

    public TAMTRUService() {
        this.tamtruDao = new TAMTRUDao(); 
    }

    public int addTAMTRU(TAMTRU tamtru) {
        nhankhauDao = new NHANKHAUDao();
        nhankhauDao.insert(tamtru.getNhankhau());
        return tamtruDao.insert(tamtru); 
    }


    public int updateTAMTRU(TAMTRU tamtru) {
        return tamtruDao.update(tamtru);  
    }


    public boolean deleteTAMTRU(TAMTRU tamtru) {
        return tamtruDao.delete(tamtru); 
    }

    public List<TAMTRU> getAllTAMTRU() {
        return tamtruDao.selectAll();  
    }
    public List<TAMTRU> getByCondition(String dk) {
        return tamtruDao.selectByCondition(dk);  
    }


}
