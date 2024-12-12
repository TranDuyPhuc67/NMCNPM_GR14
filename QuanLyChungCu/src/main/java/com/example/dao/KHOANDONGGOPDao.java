package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import com.example.model.KHOANDONGGOP;
import com.example.util.DatabaseUtil;

public class KHOANDONGGOPDao implements DAOInterface<KHOANDONGGOP> {

    @Override
    public int insert(KHOANDONGGOP t) {
        String sql = "INSERT INTO KHOANDONGGOP (Idhogiadinh, Tenquy, Sotien) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdhogiadinh());
            stmt.setString(2, t.getTenquy());
            stmt.setDouble(3, t.getSotien());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm KHOANDONGGOP", e);
        }
    }

    @Override
    public int update(KHOANDONGGOP t) {
        String sql = "UPDATE KHOANDONGGOP SET Tenquy = ?, Sotien = ? WHERE Iddonggop = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getTenquy());
            stmt.setDouble(2, t.getSotien());
            stmt.setInt(3, t.getIddonggop());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật KHOANDONGGOP", e);
        }
    }

    @Override
    public boolean delete(KHOANDONGGOP t) {
        String sql = "DELETE FROM KHOANDONGGOP WHERE Iddonggop = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIddonggop());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa KHOANDONGGOP", e);
        }
    }

    @Override
    public ArrayList<KHOANDONGGOP> selectAll() {
        String sql = "SELECT * FROM KHOANDONGGOP";
        ArrayList<KHOANDONGGOP> list = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                KHOANDONGGOP khoandonggop = new KHOANDONGGOP();
                khoandonggop.setIddonggop(rs.getInt("Iddonggop"));
                khoandonggop.setIdhogiadinh(rs.getInt("Idhogiadinh"));
                khoandonggop.setTenquy(rs.getString("Tenquy"));
                khoandonggop.setSotien(rs.getDouble("Sotien"));
                list.add(khoandonggop);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn tất cả KHOANDONGGOP", e);
        }

        return list;
    }

    @Override
    public KHOANDONGGOP selectById(KHOANDONGGOP t) {
        String sql = "SELECT * FROM KHOANDONGGOP WHERE Iddonggop = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIddonggop());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    KHOANDONGGOP khoandonggop = new KHOANDONGGOP();
                    khoandonggop.setIddonggop(rs.getInt("Iddonggop"));
                    khoandonggop.setIdhogiadinh(rs.getInt("Idhogiadinh"));
                    khoandonggop.setTenquy(rs.getString("Tenquy"));
                    khoandonggop.setSotien(rs.getDouble("Sotien"));
                    return khoandonggop;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn KHOANDONGGOP theo ID", e);
        }
        return null;
    }

	@Override
	public ArrayList<KHOANDONGGOP> selectByCondition(String Condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
