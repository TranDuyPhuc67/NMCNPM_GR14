package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import com.example.model.TIENICH;
import com.example.util.DatabaseUtil;

public class TIENICHDao implements DAOInterface<TIENICH> {

    @Override
    public int insert(TIENICH t) {
        String sql = "INSERT INTO TIENICH (CCCDchuho, LoaiTienIch, TongTien, ThangThu) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCCCDchuho());
            stmt.setString(2, t.getLoaiTienIch());
            stmt.setBigDecimal(3, t.getTongTien());
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

            stmt.setString(1, t.getLoaiTienIch());
            stmt.setBigDecimal(2, t.getTongTien());
            stmt.setInt(3, t.getIdtienich());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật TIENICH", e);
        }
    }

    @Override
    public boolean delete(TIENICH t) {
        String sql = "DELETE FROM TIENICH WHERE Idtienich = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdtienich());
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
                tienich.setIdtienich(rs.getInt("Idtienich"));
                tienich.setCCCDchuho(rs.getString("CCCDchuho"));
                tienich.setLoaiTienIch(rs.getString("LoaiTienIch"));
                tienich.setTongTien(rs.getBigDecimal("TongTien"));
                list.add(tienich);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn tất cả TIENICH", e);
        }

        return list;
    }

    @Override
    public TIENICH selectById(TIENICH t) {
        String sql = "SELECT * FROM TIENICH WHERE Idtienich = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdtienich());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    TIENICH tienich = new TIENICH();
                    tienich.setIdtienich(rs.getInt("Idtienich"));
                    tienich.setCCCDchuho(rs.getString("CCCDchuho"));
                    tienich.setLoaiTienIch(rs.getString("LoaiTienIch"));
                    tienich.setTongTien(rs.getBigDecimal("TongTien"));
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
