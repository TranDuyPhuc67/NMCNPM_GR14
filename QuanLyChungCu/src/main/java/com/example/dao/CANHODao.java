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
        String sql = "INSERT INTO CANHO (Sonha, Loaicanho, Dientich,Diachi) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            System.out.println(t.getLoaicanho());
            // stmt.setInt(1, t.getIdcanho());
            stmt.setString(1, t.getSonha());
            stmt.setString(2, t.getLoaicanho());
            stmt.setDouble(3, t.getDientich());
            stmt.setString(4, t.getDiachi());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            // Log error and throw an exception
            System.err.println("Error while inserting CANHO: " + e.getMessage());
            throw new RuntimeException("Thêm căn hộ thất bại, vui lòng nhập chính xác dữ liệu", e);
        }
    }

    @Override
    public int update(CANHO t) {
        String sql = "UPDATE CANHO SET Sonha = ?, Loaicanho = ?, Dientich = ?, Diachi = ? WHERE Idcanho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getSonha());
            stmt.setString(2, t.getLoaicanho());
            stmt.setDouble(3, t.getDientich());
            stmt.setString(4, t.getDiachi());
            stmt.setInt(5, t.getIdcanho());
            

            return stmt.executeUpdate();
        } catch (SQLException e) {
            // Log error and throw an exception
            System.err.println("Error while updating CANHO: " + e.getMessage());
            throw new RuntimeException("Chỉnh sửa thất bại, vui lòng nhập chính xác dữ liệu", e);
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
            throw new RuntimeException("Xóa căn hộ thất bại", e);
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
                canho.setSonha(rs.getString("Sonha"));
                canho.setLoaicanho(rs.getString("Loaicanho"));
                canho.setDientich(rs.getDouble("Dientich"));
                canho.setDiachi(rs.getString("Diachi"));
                list.add(canho);
            }
        } catch (SQLException e) {
            // Log error and throw an exception
            System.err.println("Error while selecting all CANHO: " + e.getMessage());
            throw new RuntimeException("Đã có lỗi xảy ra trong quá trình xử lý", e);
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
                    canho.setDiachi(rs.getString("Diachi"));
                    return canho;
                }
            }
        } catch (SQLException e) {
            // Log error and throw an exception
            System.err.println("Error while selecting CANHO by ID: " + e.getMessage());
            throw new RuntimeException("Lỗi tìm kiếm căn hộ", e);
        }

        return null;
    }
    public int NametoId(String t) {
        String sql = "SELECT Idcanho FROM CANHO WHERE Sonha = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("Idcanho");
                }
            }
        } catch (SQLException e) {
            // Log error and throw an exception
            System.err.println("Error while selecting CANHO by ID: " + e.getMessage());
            throw new RuntimeException("Đã có lỗi hệ thống xảy ra", e);
        }

        return 0;
    }
    public String IdtoName(int t) {
        String sql = "SELECT Sonha FROM CANHO WHERE Idcanho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("Sonha");
                }
            }
        } catch (SQLException e) {
            // Log error and throw an exception
            System.err.println("Error while selecting CANHO by ID: " + e.getMessage());
            throw new RuntimeException("Đã có lỗi hệ thống xảy ra", e);
        }

        return null;
    }
    public CANHO selectByName(String t) {
        String sql = "SELECT * FROM CANHO WHERE sonha = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    CANHO canho = new CANHO();
                    canho.setIdcanho(rs.getInt("Idcanho"));
                    canho.setSonha(rs.getString("Sonha"));
                    canho.setLoaicanho(rs.getString("Loaicanho"));
                    canho.setDientich(rs.getDouble("Dientich"));
                    canho.setDiachi(rs.getString("Diachi"));
                    return canho;
                }
            }
        } catch (SQLException e) {
            // Log error and throw an exception
            System.err.println("Error while selecting CANHO by ID: " + e.getMessage());
            throw new RuntimeException("Đã có lỗi hệ thống xảy ra", e);
        }

        return null;
    }

    

	@Override
    public ArrayList<CANHO> selectByCondition(String condition) {
        ArrayList<CANHO> list = new ArrayList<>();
        String sql = "SELECT * FROM CANHO WHERE Idcanho = ? OR Dientich = ? OR Sonha LIKE ? OR Loaicanho LIKE ? OR Diachi LIKE ?";
        String searchKeyword = "%" + condition + "%";
        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement statement = conn.prepareStatement(sql)) {
            try {
                statement.setInt(1, Integer.parseInt(condition));
                statement.setDouble(2, Double.parseDouble(condition));
            } catch (NumberFormatException e) {
                statement.setInt(1, -1);
                statement.setDouble(2, -1);
            }
            for (int i = 3; i <= 5; i++) {
                statement.setString(i, searchKeyword);
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CANHO canho = new CANHO();
                canho.setIdcanho(rs.getInt("Idcanho"));
                canho.setSonha(rs.getString("Sonha"));
                canho.setLoaicanho(rs.getString("Loaicanho"));
                canho.setDientich(rs.getDouble("Dientich"));
                canho.setDiachi(rs.getString("Diachi"));
                list.add(canho);
            }
        } catch (SQLException e) {
            // Log error and throw an exception
            System.err.println("Error while selecting CANHO by condition: " + e.getMessage());
            throw new RuntimeException("Tìm điều ", e);
        }

        return list;
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
