package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.example.model.PHICHUNGCU;
import com.example.util.DatabaseUtil;

public class PHICHUNGCUDao implements DAOInterface<PHICHUNGCU> {

    @Override
    public int insert(PHICHUNGCU t) {
        String sql = "INSERT INTO PHICHUNGCU (Idcanho, Hanthu, Tongphichungcu) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, t.getIdcanho());
            stmt.setDate(2, t.getHanthu());
            stmt.setInt(3, t.getTongphichungcu());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(PHICHUNGCU t) {
        String sql = "UPDATE PHICHUNGCU SET Hanthu = ?, Tongphichungcu = ? WHERE Idcanho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, t.getHanthu());
            stmt.setInt(2, t.getTongphichungcu());
            stmt.setInt(3, t.getIdcanho());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean delete(PHICHUNGCU t) {
        String sql = "DELETE FROM PHICHUNGCU WHERE Idcanho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, t.getIdcanho());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<PHICHUNGCU> selectAll() {
        ArrayList<PHICHUNGCU> list = new ArrayList<>();
        String sql = "SELECT * FROM PHICHUNGCU";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new PHICHUNGCU(
                        rs.getInt("Idcanho"),
                        rs.getDate("Hanthu"),
                        rs.getInt("Tongphichungcu")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public PHICHUNGCU selectById(PHICHUNGCU t) {
        String sql = "SELECT * FROM PHICHUNGCU WHERE Idcanho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, t.getIdcanho());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PHICHUNGCU(
                            rs.getInt("Idcanho"),
                            rs.getDate("Hanthu"),
                            rs.getInt("Tongphichungcu")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<PHICHUNGCU> selectByCondition(String condition) {
        ArrayList<PHICHUNGCU> list = new ArrayList<>();
        String sql = "SELECT * FROM PHICHUNGCU WHERE " + condition;
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new PHICHUNGCU(
                        rs.getInt("Idcanho"),
                        rs.getDate("Hanthu"),
                        rs.getInt("Tongphichungcu")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
