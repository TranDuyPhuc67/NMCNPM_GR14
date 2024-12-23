package com.example.service;

import java.util.ArrayList;
import java.util.Map;

import com.example.dao.PHIGUIXEDao;
import com.example.model.PHIGUIXE;

public class PHIGUIXEService {

    private PHIGUIXEDao phiguixeDao;

    public PHIGUIXEService() {
        this.phiguixeDao = new PHIGUIXEDao();
    }

    public int addPHIGUIXE(PHIGUIXE phiguixe) {
        return phiguixeDao.insert(phiguixe);
    }

    public int updatePHIGUIXE(PHIGUIXE phiguixe) {
        return phiguixeDao.update(phiguixe);
    }

    public boolean deletePHIGUIXE(String cccdChuHo) {
        PHIGUIXE phiguixe = new PHIGUIXE();
        phiguixe.setCCCDchuho(cccdChuHo);
        return phiguixeDao.delete(phiguixe);
    }

    public ArrayList<PHIGUIXE> getAllPHIGUIXE() {
        return phiguixeDao.selectAll();
    }

    public PHIGUIXE getPHIGUIXEByCCCD(String cccdChuHo) {
        PHIGUIXE phiguixe = new PHIGUIXE();
        phiguixe.setCCCDchuho(cccdChuHo);
        return phiguixeDao.selectById(phiguixe);
    }
	public ArrayList<Map<String, Object>> selectAllWithDetails() {
		return phiguixeDao.selectAllWithDetails();
	}
}
