package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.model.CANHO;
import com.example.util.DatabaseUtil;

public class CANHODao implements DAOInterface<CANHO> {

    @Override
    public int insert(CANHO t) {
        String sql = "INSERT INTO CANHO (Idcanho, Sonha, Loaicanho, Dientich) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
        	stmt.setInt(1, t.getIdcanho());
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
        ArrayList<CANHO> list = new ArrayList<>();
        String sql = "SELECT * FROM CANHO";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { // Không cần set tham số

            while (rs.next()) {
                list.add(new CANHO(
                        rs.getInt("Idcanho"),
                        rs.getString("Sonha"),
                        rs.getString("Loaicanho"),
                        rs.getDouble("Dientich")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy danh sách CANHO", e);
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
	
	public ArrayList<Map<String, Object>> getAllChuHoWithCanHo() {
	    String sql = """
	        SELECT c.Idcanho, c.Sonha, c.Dientich, h.Hotenchuho, h.Soxemay, h.Sooto
	        FROM CANHO c
	        LEFT JOIN HOGIADINH h ON c.Idcanho = h.Idcanho
	    """;

	    ArrayList<Map<String, Object>> resultList = new ArrayList<>();

	    try (Connection conn = DatabaseUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Map<String, Object> row = new HashMap<>();
	            row.put("idcanho", rs.getInt("Idcanho"));
	            row.put("sonha", rs.getString("Sonha"));
	            row.put("dientich", rs.getDouble("Dientich"));
	            row.put("hotenchuho", rs.getString("Hotenchuho"));
	            row.put("soxemay", rs.getInt("Soxemay")); // Lấy số xe máy
	            row.put("sooto", rs.getInt("Sooto"));     // Lấy số ô tô
	            resultList.add(row);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Lỗi khi lấy danh sách chủ hộ với căn hộ", e);
	    }

	    return resultList;
	}
}

