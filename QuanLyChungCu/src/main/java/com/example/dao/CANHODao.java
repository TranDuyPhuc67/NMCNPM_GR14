package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.model.CANHO;
import com.example.util.DatabaseUtil;

public class CANHODao implements DAOInterface<CANHO> {

    @Override
    public int insert(CANHO t) {
        String sql = "INSERT INTO CANHO (CCCDchuho, Sonha, Loaicanho, Dientich) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCCCDchuho());
            stmt.setString(2, t.getSonha());
            stmt.setString(3, t.getLoaicanho());
            stmt.setDouble(4, t.getDientich());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            // Log error and throw an exception
            System.err.println("Error while inserting CANHO: " + e.getMessage());
            throw new RuntimeException("Error while inserting CANHO", e);
        }
    }

    @Override
    public int update(CANHO t) {
        String sql = "UPDATE CANHO SET Sonha = ?, Loaicanho = ?, Dientich = ? WHERE Idcanho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getSonha());
            stmt.setString(2, t.getLoaicanho());
            stmt.setDouble(3, t.getDientich());
            stmt.setInt(4, t.getIdcanho());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            // Log error and throw an exception
            System.err.println("Error while updating CANHO: " + e.getMessage());
            throw new RuntimeException("Error while updating CANHO", e);
        }
    }

    @Override
    public boolean delete(CANHO t) {
        String sql = "DELETE FROM CANHO WHERE Idcanho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdcanho());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Log error and throw an exception
            System.err.println("Error while deleting CANHO: " + e.getMessage());
            throw new RuntimeException("Error while deleting CANHO", e);
        }
    }

    @Override
    public ArrayList<CANHO> selectAll() {
        String sql = "SELECT * FROM CANHO";
        ArrayList<CANHO> list = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CANHO canho = new CANHO();
                canho.setIdcanho(rs.getInt("Idcanho"));
                canho.setCCCDchuho(rs.getString("CCCDchuho"));
                canho.setSonha(rs.getString("Sonha"));
                canho.setLoaicanho(rs.getString("Loaicanho"));
                canho.setDientich(rs.getDouble("Dientich"));
                list.add(canho);
            }
        } catch (SQLException e) {
            // Log error and throw an exception
            System.err.println("Error while selecting all CANHO: " + e.getMessage());
            throw new RuntimeException("Error while selecting all CANHO", e);
        }

        return list;
    }

    @Override
    public CANHO selectById(CANHO t) {
        String sql = "SELECT * FROM CANHO WHERE Idcanho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdcanho());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CANHO canho = new CANHO();
                    canho.setIdcanho(rs.getInt("Idcanho"));
                    canho.setCCCDchuho(rs.getString("CCCDchuho"));
                    canho.setSonha(rs.getString("Sonha"));
                    canho.setLoaicanho(rs.getString("Loaicanho"));
                    canho.setDientich(rs.getDouble("Dientich"));
                    return canho;
                }
            }
        } catch (SQLException e) {
            // Log error and throw an exception
            System.err.println("Error while selecting CANHO by ID: " + e.getMessage());
            throw new RuntimeException("Error while selecting CANHO by ID", e);
        }

        return null;
    }

	@Override
	public ArrayList<CANHO> selectByCondition(String Condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
