package com.example.service;

import com.example.dao.CANHODao;
import com.example.model.CANHO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public ArrayList<CANHO> getByCondition(String t) {
        return canHoDao.selectByCondition(t);  
    }
    public CANHO getCanHoById(int idCanHo) {
        CANHO temp = new CANHO();
        temp.setIdcanho(idCanHo);
        return canHoDao.selectById(temp); 
    }
    public CANHO getCanHoByName(String sonha) {
        return canHoDao.selectByName(sonha); 
    }
    public int NametoId(String sonha) {       
        return canHoDao.NametoId(sonha);
    }
    public String IdtoName(int id) {       
        return canHoDao.IdtoName(id);
    }
    public ArrayList<Map<String, Object>> getAllChuHoWithCanHo() {
		return canHoDao.getAllChuHoWithCanHo();
	}

}
