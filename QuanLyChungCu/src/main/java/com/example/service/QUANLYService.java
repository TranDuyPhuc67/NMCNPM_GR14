package com.example.service;

import com.example.dao.QUANLYDao;
import com.example.model.QUANLY;


public class QUANLYService {
	    private QUANLYDao QUANLYDao = new QUANLYDao();

	    // Đăng nhập
	    public boolean authenticateQUANLY(String username, String password) {
	    	QUANLY QUANLY = QUANLYDao.authenticate(username, password);
	        return QUANLY != null;
	    }
		public boolean changeQUANLY(String username, String password, String newpassword) {
	    	int check = QUANLYDao.update1(username, password,newpassword);
	        return check != 0;
	    }
}
