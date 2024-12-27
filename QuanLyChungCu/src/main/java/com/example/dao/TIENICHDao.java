package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.model.TIENICH;
import com.example.util.DatabaseUtil;

public class TIENICHDao implements DAOInterface<TIENICH> {

    @Override
    public int insert(TIENICH t) {
        int tongTienIch = t.getSodien() + t.getSonuoc() + t.getInternet();

        String sql = "INSERT INTO TIENICH (Idcanho, Sodien, Sonuoc, Internet, Hanthu, Tongtienich) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdcanho());
            stmt.setInt(2, t.getSodien());
            stmt.setInt(3, t.getSonuoc());
            stmt.setInt(4, t.getInternet());
            stmt.setString(5, t.getHanthu());
            stmt.setInt(6, tongTienIch);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi thêm TIENICH", e);
        }
    }

    @Override
    public int update(TIENICH t) {
        int tongTienIch = t.getSodien() + t.getSonuoc() + t.getInternet();

        String sql = "UPDATE TIENICH SET Sodien = ?, Sonuoc = ?, Internet = ?, Tongtienich = ? " +
                     "WHERE Idcanho = ? AND Hanthu = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getSodien());
            stmt.setInt(2, t.getSonuoc());
            stmt.setInt(3, t.getInternet());
            stmt.setInt(4, tongTienIch);
            stmt.setInt(5, t.getIdcanho());
            stmt.setString(6, t.getHanthu());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi cập nhật TIENICH", e);
        }
    }

    @Override
    public boolean delete(TIENICH t) {
        String sql = "DELETE FROM TIENICH WHERE Idcanho = ? AND Hanthu = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdcanho());
            stmt.setString(2, t.getHanthu());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
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
                tienich.setIdcanho(rs.getInt("Idcanho"));
                tienich.setSodien(rs.getInt("Sodien"));
                tienich.setSonuoc(rs.getInt("Sonuoc"));
                tienich.setInternet(rs.getInt("Internet"));
                tienich.setHanthu(rs.getString("Hanthu"));
                tienich.setTongtienich(rs.getInt("Tongtienich"));
                list.add(tienich);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi chọn tất cả TIENICH", e);
        }

        return list;
    }

    @Override
    public TIENICH selectById(TIENICH t) {
        String sql = "SELECT * FROM TIENICH WHERE Idcanho = ? AND Hanthu = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, t.getIdcanho());
            stmt.setString(2, t.getHanthu());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    TIENICH tienich = new TIENICH();
                    tienich.setIdcanho(rs.getInt("Idcanho"));
                    tienich.setSodien(rs.getInt("Sodien"));
                    tienich.setSonuoc(rs.getInt("Sonuoc"));
                    tienich.setInternet(rs.getInt("Internet"));
                    tienich.setHanthu(rs.getString("Hanthu"));
                    tienich.setTongtienich(rs.getInt("Tongtienich"));
                    return tienich;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi chọn TIENICH theo Idcanho và Hanthu", e);
        }
        return null;
    }

    @Override
    public ArrayList<TIENICH> selectByCondition(String condition) {
        String sql = "SELECT * FROM TIENICH WHERE " + condition;
        ArrayList<TIENICH> list = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TIENICH tienich = new TIENICH();
                tienich.setIdcanho(rs.getInt("Idcanho"));
                tienich.setSodien(rs.getInt("Sodien"));
                tienich.setSonuoc(rs.getInt("Sonuoc"));
                tienich.setInternet(rs.getInt("Internet"));
                tienich.setHanthu(rs.getString("Hanthu"));
                tienich.setTongtienich(rs.getInt("Tongtienich"));
                list.add(tienich);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi chọn TIENICH với điều kiện", e);
        }

        return list;
    }

    public List<Map<String, Object>> getAllTienIchWithChuHo() {
        String query = "SELECT t.Idcanho AS Idcanho, h.Hotenchuho AS TenChuHo, c.Sonha, t.Sodien, t.Sonuoc, t.Internet, t.Hanthu AS Hanthu, t.Tongtienich " +
                       "FROM HOGIADINH h " +
                       "JOIN CANHO c ON h.Idcanho = c.Idcanho " +
                       "LEFT JOIN TIENICH t ON c.Idcanho = t.Idcanho";

        List<Map<String, Object>> result = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("Idcanho", rs.getObject("Idcanho") != null ? rs.getInt("Idcanho") : 0);
                row.put("TenChuHo", rs.getString("TenChuHo"));
                row.put("Sonha", rs.getString("Sonha"));
                row.put("Sodien", rs.getObject("Sodien") != null ? rs.getInt("Sodien") : 0);
                row.put("Sonuoc", rs.getObject("Sonuoc") != null ? rs.getInt("Sonuoc") : 0);
                row.put("Internet", rs.getObject("Internet") != null ? rs.getInt("Internet") : 0);
                row.put("Hanthu", rs.getString("Hanthu"));
                row.put("Tongtienich", rs.getObject("Tongtienich") != null ? rs.getInt("Tongtienich") : 0);
                result.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy danh sách tiện ích với chủ hộ", e);
        }
        return result;
    }

    public boolean checkExists(int idCanHo, String hanThu) {
        String sql = "SELECT 1 FROM TIENICH WHERE Idcanho = ? AND Hanthu = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCanHo);
            stmt.setString(2, hanThu);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi kiểm tra tồn tại TIENICH", e);
        }
    }

    public List<Map<String, Object>> searchTienIch(String sonha, String tenChuHo, Integer month, Integer year) {
        List<Map<String, Object>> result = new ArrayList<>();

        StringBuilder query = new StringBuilder(
            "SELECT t.Idcanho AS Idcanho, h.Hotenchuho AS TenChuHo, c.Sonha, t.Sodien, t.Sonuoc, t.Internet, t.Hanthu AS Hanthu, t.Tongtienich " +
            "FROM HOGIADINH h " +
            "JOIN CANHO c ON h.Idcanho = c.Idcanho " +
            "LEFT JOIN TIENICH t ON c.Idcanho = t.Idcanho WHERE 1=1"
        );

        List<Object> parameters = new ArrayList<>();

        if (sonha != null && !sonha.trim().isEmpty()) {
            query.append(" AND c.Sonha LIKE ?");
            parameters.add("%" + sonha.trim() + "%");
        }
        if (tenChuHo != null && !tenChuHo.trim().isEmpty()) {
            query.append(" AND h.Hotenchuho LIKE ?");
            parameters.add("%" + tenChuHo.trim() + "%");
        }
        if (month != null && year != null) {
            query.append(" AND t.Hanthu = ?");
            parameters.add(String.format("%04d-%02d", year, month));
        } else if (year != null) {
            query.append(" AND YEAR(t.Hanthu) = ?");
            parameters.add(year);
        }

        query.append(" ORDER BY t.Hanthu ASC");

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                stmt.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("Idcanho", rs.getObject("Idcanho") != null ? rs.getInt("Idcanho") : 0);
                    row.put("TenChuHo", rs.getString("TenChuHo"));
                    row.put("Sonha", rs.getString("Sonha"));
                    row.put("Sodien", rs.getObject("Sodien") != null ? rs.getInt("Sodien") : 0);
                    row.put("Sonuoc", rs.getObject("Sonuoc") != null ? rs.getInt("Sonuoc") : 0);
                    row.put("Internet", rs.getObject("Internet") != null ? rs.getInt("Internet") : 0);
                    row.put("Hanthu", rs.getString("Hanthu"));
                    row.put("Tongtienich", rs.getObject("Tongtienich") != null ? rs.getInt("Tongtienich") : 0);
                    result.add(row);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Đã xảy ra lỗi khi tìm kiếm tiện ích", e);
        }

        return result;
    }
}