package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import com.example.model.PHIGUIXE;
import com.example.util.DatabaseUtil;

public class PHIGUIXEDao implements DAOInterface<PHIGUIXE> {

    @Override
    public int insert(PHIGUIXE t) {
        String sql = "INSERT INTO PHIGUIXE (Idhogiadinh, Phixemay, Phioto) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdhogiadinh());
            stmt.setBigDecimal(2, t.getPhixemay());
            stmt.setBigDecimal(3, t.getPhioto());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm PHIGUIXE", e);
        }
    }

    @Override
    public int update(PHIGUIXE t) {
        String sql = "UPDATE PHIGUIXE SET Phixemay = ?, Phioto = ? WHERE Idhogiadinh = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, t.getPhixemay());
            stmt.setBigDecimal(2, t.getPhioto());
            stmt.setInt(3, t.getIdhogiadinh());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật PHIGUIXE", e);
        }
    }

    @Override
    public boolean delete(PHIGUIXE t) {
        String sql = "DELETE FROM PHIGUIXE WHERE Idhogiadinh = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdhogiadinh());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa PHIGUIXE", e);
        }
    }

    @Override
    public ArrayList<PHIGUIXE> selectAll() {
        String sql = "SELECT * FROM PHIGUIXE";
        ArrayList<PHIGUIXE> list = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PHIGUIXE phiguixe = new PHIGUIXE();
                phiguixe.setIdhogiadinh(rs.getInt("Idhogiadinh"));
                phiguixe.setPhixemay(rs.getBigDecimal("Phixemay"));
                phiguixe.setPhioto(rs.getBigDecimal("Phioto"));
                list.add(phiguixe);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn tất cả PHIGUIXE", e);
        }

        return list;
    }

    @Override
    public PHIGUIXE selectById(PHIGUIXE t) {
        String sql = "SELECT * FROM PHIGUIXE WHERE Idhogiadinh = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdhogiadinh());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PHIGUIXE phiguixe = new PHIGUIXE();
                    phiguixe.setIdhogiadinh(rs.getInt("Idhogiadinh"));
                    phiguixe.setPhixemay(rs.getBigDecimal("Phixemay"));
                    phiguixe.setPhioto(rs.getBigDecimal("Phioto"));
                    return phiguixe;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn PHIGUIXE theo Idhogiadinh", e);
        }
        return null;
    }

	@Override
	public ArrayList<PHIGUIXE> selectByCondition(String Condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
