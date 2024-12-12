package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import com.example.model.HOGIADINH;
import com.example.util.DatabaseUtil;

public class HOGIADINHDao implements DAOInterface<HOGIADINH> {

    @Override
    public int insert(HOGIADINH t) {
        String sql = "INSERT INTO HOGIADINH (Hotenchuho) VALUES (?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getHotenchuho());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            // Log error and rethrow as RuntimeException
            System.err.println("Error while inserting HOGIADINH: " + e.getMessage());
            throw new RuntimeException("Error while inserting HOGIADINH", e);
        }
    }

    @Override
    public int update(HOGIADINH t) {
        String sql = "UPDATE HOGIADINH SET Hotenchuho = ? WHERE Idhogiadinh = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getHotenchuho());
            stmt.setInt(2, t.getIdhogiadinh());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            // Log error and rethrow as RuntimeException
            System.err.println("Error while updating HOGIADINH: " + e.getMessage());
            throw new RuntimeException("Error while updating HOGIADINH", e);
        }
    }

    @Override
    public boolean delete(HOGIADINH t) {
        String sql = "DELETE FROM HOGIADINH WHERE Idhogiadinh = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdhogiadinh());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Log error and rethrow as RuntimeException
            System.err.println("Error while deleting HOGIADINH: " + e.getMessage());
            throw new RuntimeException("Error while deleting HOGIADINH", e);
        }
    }

    @Override
    public ArrayList<HOGIADINH> selectAll() {
        String sql = "SELECT * FROM HOGIADINH";
        ArrayList<HOGIADINH> list = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                HOGIADINH hogiadinh = new HOGIADINH();
                hogiadinh.setIdhogiadinh(rs.getInt("Idhogiadinh"));
                hogiadinh.setHotenchuho(rs.getString("Hotenchuho"));
                list.add(hogiadinh);
            }
        } catch (SQLException e) {
            // Log error and rethrow as RuntimeException
            System.err.println("Error while selecting all HOGIADINH: " + e.getMessage());
            throw new RuntimeException("Error while selecting all HOGIADINH", e);
        }

        return list;
    }

    @Override
    public HOGIADINH selectById(HOGIADINH t) {
        String sql = "SELECT * FROM HOGIADINH WHERE Idhogiadinh = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdhogiadinh());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    HOGIADINH hogiadinh = new HOGIADINH();
                    hogiadinh.setIdhogiadinh(rs.getInt("Idhogiadinh"));
                    hogiadinh.setHotenchuho(rs.getString("Hotenchuho"));
                    return hogiadinh;
                }
            }
        } catch (SQLException e) {
            // Log error and rethrow as RuntimeException
            System.err.println("Error while selecting HOGIADINH by ID: " + e.getMessage());
            throw new RuntimeException("Error while selecting HOGIADINH by ID", e);
        }
        return null;
    }

    @Override
    public ArrayList<HOGIADINH> selectByCondition(String condition) {
        String sql = "SELECT * FROM HOGIADINH WHERE " + condition;
        ArrayList<HOGIADINH> list = new ArrayList<>();

        // Ensure that the condition is sanitized to avoid SQL Injection (use parameterized query if possible)
        if (condition == null || condition.isEmpty()) {
            throw new IllegalArgumentException("Condition cannot be null or empty");
        }

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    HOGIADINH hogiadinh = new HOGIADINH();
                    hogiadinh.setIdhogiadinh(rs.getInt("Idhogiadinh"));
                    hogiadinh.setHotenchuho(rs.getString("Hotenchuho"));
                    list.add(hogiadinh);
                }
            }
        } catch (SQLException e) {
            // Log error and rethrow as RuntimeException
            System.err.println("Error while selecting HOGIADINH by condition: " + e.getMessage());
            throw new RuntimeException("Error while selecting HOGIADINH by condition", e);
        }

        return list;
    }
}
