package com.example.service;

import com.example.dao.CANHODao;
import com.example.model.CANHO;
import java.util.ArrayList;

public class CANHOService {

    private CANHODao canHoDao;

    public CANHOService() {
        this.canHoDao = new CANHODao(); 
    }

    public int addCanHo(CANHO canHo) {
        return canHoDao.insert(canHo);  
    }

    public int updateCanHo(CANHO canHo) {
        return canHoDao.update(canHo);  
    }

    public boolean deleteCanHo(CANHO canHo) {
        return canHoDao.delete(canHo);  
    }

    public ArrayList<CANHO> getAllCanHos() {
        return canHoDao.selectAll();  
    }

    public CANHO getCanHoById(int idCanHo) {
        CANHO temp = new CANHO();
        temp.setIdcanho(idCanHo);
        return canHoDao.selectById(temp); 
    }
}
