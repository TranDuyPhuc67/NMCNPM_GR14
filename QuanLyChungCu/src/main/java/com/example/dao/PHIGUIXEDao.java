package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import com.example.model.PHIGUIXE;
import com.example.util.DatabaseUtil;

public class PHIGUIXEDao implements DAOInterface<PHIGUIXE> {

    @Override
    public int insert(PHIGUIXE t) {
        String sql = "INSERT INTO PHIGUIXE (CCCDchuho, Soxemay, Sooto, Thoihan) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCCCDchuho());
            stmt.setInt(2, t.getSoxemay());
            stmt.setInt(3, t.getSooto());
            stmt.setDate(4, t.getThoihan());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm PHIGUIXE", e);
        }
    }

    @Override
    public int update(PHIGUIXE t) {
        String sql = "UPDATE PHIGUIXE SET Phixemay = ?, Phioto = ?, Thoihan = ? WHERE CCCDchuho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getSoxemay());
            stmt.setInt(2, t.getSooto());
            stmt.setDate(3, t.getThoihan());
            stmt.setString(4, t.getCCCDchuho());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật PHIGUIXE", e);
        }
    }

    @Override
    public boolean delete(PHIGUIXE t) {
        String sql = "DELETE FROM PHIGUIXE WHERE CCCDchuho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCCCDchuho());
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
                phiguixe.setCCCDchuho(rs.getString("CCCDchuho"));
                phiguixe.setSoxemay(rs.getInt("Phixemay"));
                phiguixe.setSooto(rs.getInt("Phioto"));
                phiguixe.setThoihan(rs.getDate("Thoihan"));
                list.add(phiguixe);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn tất cả PHIGUIXE", e);
        }

        return list;
    }

    @Override
    public PHIGUIXE selectById(PHIGUIXE t) {
        String sql = "SELECT * FROM PHIGUIXE WHERE CCCDchuho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCCCDchuho());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    PHIGUIXE phiguixe = new PHIGUIXE();
                    phiguixe.setCCCDchuho(rs.getString("CCCDchuho"));
                    phiguixe.setSoxemay(rs.getInt("Phixemay"));
                    phiguixe.setSooto(rs.getInt("Phioto"));
                    phiguixe.setThoihan(rs.getDate("Thoihan"));
                    return phiguixe;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn PHIGUIXE theo CCCDchuho", e);
        }
        return null;
    }

	@Override
	public ArrayList<PHIGUIXE> selectByCondition(String Condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
