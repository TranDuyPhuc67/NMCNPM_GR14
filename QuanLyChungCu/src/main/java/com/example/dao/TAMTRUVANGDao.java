package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import com.example.model.TAMTRUVANG;
import com.example.util.DatabaseUtil;

public class TAMTRUVANGDao implements DAOInterface<TAMTRUVANG>{


    @Override
    public int insert(TAMTRUVANG t){
        String query = "INSERT INTO TAMTRUVANG (CCCD , HOVATEN, Idcanho,Ngaybatdau, Ngayketthuc, Lydo, Trangthai ) VALUES (?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			// stmt.setString(1, t.getUsername());
			// stmt.setString(2, t.getPassword());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
     return 0;
    }

    @Override
	public int update(TAMTRUVANG t){
        return 1;
    }

    @Override
	public boolean delete(TAMTRUVANG t){
        return true;
    }

    @Override
	public ArrayList<TAMTRUVANG> selectAll(){
        return null;
    }

    @Override
	public TAMTRUVANG selectById(TAMTRUVANG t){
        return null;
    }

    @Override
	public ArrayList<TAMTRUVANG> selectByCondition(String Condition){
        return null;
    }
}
