package com.example.service;

import com.example.dao.HOGIADINHDao;
import com.example.model.HOGIADINH;
import java.util.List;

public class HOGIADINHService {

    private HOGIADINHDao hogiadinhDao;

    public HOGIADINHService() {
        this.hogiadinhDao = new HOGIADINHDao(); 
    }

    public int addHOGIADINH(HOGIADINH hogiadinh) {
        return hogiadinhDao.insert(hogiadinh); 
    }

    public int updateHOGIADINH(HOGIADINH hogiadinh) {
        return hogiadinhDao.update(hogiadinh); 
    }

    public boolean deleteHOGIADINH(HOGIADINH hogiadinh) {
        return hogiadinhDao.delete(hogiadinh); 
    }

    public List<HOGIADINH> getAllHOGIADINH() {
        return hogiadinhDao.selectAll();
    }

    public HOGIADINH getHOGIADINHById(String cccdChuHo) {
        HOGIADINH hogiadinh = new HOGIADINH();
        hogiadinh.setCCCDchuho(cccdChuHo);
        return hogiadinhDao.selectById(hogiadinh); 
    }

    public List<HOGIADINH> getHOGIADINHByCondition(String condition) {
        return hogiadinhDao.selectByCondition(condition); 
    }
    public int updateCHUHO(HOGIADINH hogiadinh) {
        return hogiadinhDao.updateChuHo(hogiadinh); 
    }
}
