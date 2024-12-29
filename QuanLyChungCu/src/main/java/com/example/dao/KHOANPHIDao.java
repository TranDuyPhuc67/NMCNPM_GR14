package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import com.example.model.KHOANPHI;
import com.example.util.DatabaseUtil;

public class KHOANPHIDao implements DAOInterface<KHOANPHI> {

    @Override
    public int insert(KHOANPHI t) {
        String sql = "INSERT INTO KHOANPHI (CCCDchuho, Idcanho, Phidichvu, Phiquanly) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCCCDchuho());
            stmt.setInt(2, t.getIdcanho());
            stmt.setDouble(3, t.getPhidichvu());
            stmt.setDouble(4, t.getPhiquanly());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm KHOANPHI", e);
        }
    }

    @Override
    public int update(KHOANPHI t) {
        String sql = "UPDATE KHOANPHI SET Phidichvu = ?, Phiquanly = ? WHERE CCCDchuho = ? AND Idcanho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, t.getPhidichvu());
            stmt.setDouble(2, t.getPhiquanly());
            stmt.setString(3, t.getCCCDchuho());
            stmt.setInt(4, t.getIdcanho());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật KHOANPHI", e);
        }
    }

    @Override
    public boolean delete(KHOANPHI t) {
        String sql = "DELETE FROM KHOANPHI WHERE CCCDchuho = ? AND Idcanho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCCCDchuho());
            stmt.setInt(2, t.getIdcanho());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa KHOANPHI", e);
        }
    }

    @Override
    public ArrayList<KHOANPHI> selectAll() {
        String sql = "SELECT * FROM KHOANPHI";
        ArrayList<KHOANPHI> list = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                KHOANPHI khoanphi = new KHOANPHI();
                khoanphi.setCCCDchuho(rs.getString("CCCDchuho"));
                khoanphi.setIdcanho(rs.getInt("Idcanho"));
                khoanphi.setPhidichvu(rs.getDouble("Phidichvu"));
                khoanphi.setPhiquanly(rs.getDouble("Phiqnaly"));
                list.add(khoanphi);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn tất cả KHOANPHI", e);
        }

        return list;
    }

    @Override
    public KHOANPHI selectById(KHOANPHI t) {
        String sql = "SELECT * FROM KHOANPHI WHERE CCCDchuho = ? AND Idcanho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCCCDchuho());
            stmt.setInt(2, t.getIdcanho());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    KHOANPHI khoanphi = new KHOANPHI();
                    khoanphi.setCCCDchuho(rs.getString("CCCDchuho"));
                    khoanphi.setIdcanho(rs.getInt("Idcanho"));
                    khoanphi.setPhidichvu(rs.getDouble("Phidichvu"));
                    khoanphi.setPhiquanly(rs.getDouble("Phiqnaly"));
                    return khoanphi;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn KHOANPHI theo ID", e);
        }
        return null;
    }

	@Override
	public ArrayList<KHOANPHI> selectByCondition(String Condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
