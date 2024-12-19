package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import com.example.model.TIENICH;
import com.example.util.DatabaseUtil;

public class TIENICHDao implements DAOInterface<TIENICH> {

    @Override
    public int insert(TIENICH t) {
        String sql = "INSERT INTO TIENICH (Idcanho, Sodien, Sonuoc, Internet, Thoihan) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdcanho());
            stmt.setInt(2, t.getSodien());
            stmt.setInt(3, t.getSonuoc());
            stmt.setInt(4, t.getInternet());
            stmt.setDate(5, t.getThoihan());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm TIENICH", e);
        }
    }

    @Override
    public int update(TIENICH t) {
        String sql = "UPDATE TIENICH SET LoaiTienIch = ?, TongTien = ? WHERE Idtienich = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdcanho());
            stmt.setInt(2, t.getSodien());
            stmt.setInt(3, t.getSonuoc());
            stmt.setInt(4, t.getInternet());
            stmt.setDate(5, t.getThoihan());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật TIENICH", e);
        }
    }

    @Override
    public boolean delete(TIENICH t) {
        String sql = "DELETE FROM TIENICH WHERE Idcanho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdcanho());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa TIENICH", e);
        }
    }

    @Override
    public ArrayList<TIENICH> selectAll() {
        String sql = "SELECT * FROM TIENICH";
        ArrayList<TIENICH> list = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TIENICH tienich = new TIENICH();
                tienich.setIdcanho(rs.getInt("Idcanho"));
                tienich.setSodien(rs.getInt("Sodien"));
                tienich.setSonuoc(rs.getInt("Sonuoc"));
                tienich.setInternet(rs.getInt("Internet"));
                tienich.setThoihan(rs.getDate("Thoihan"));
                list.add(tienich);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn tất cả TIENICH", e);
        }

        return list;
    }

    @Override
    public TIENICH selectById(TIENICH t) {
        String sql = "SELECT * FROM TIENICH WHERE Idcanho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdcanho());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    TIENICH tienich = new TIENICH();
                    tienich.setIdcanho(rs.getInt("Idcanho"));
                    tienich.setSodien(rs.getInt("Sodien"));
                    tienich.setSonuoc(rs.getInt("Sonuoc"));
                    tienich.setInternet(rs.getInt("Internet"));
                    tienich.setThoihan(rs.getDate("Thoihan"));
                    return tienich;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn TIENICH theo Idtienich", e);
        }
        return null;
    }

	@Override
	public ArrayList<TIENICH> selectByCondition(String Condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
