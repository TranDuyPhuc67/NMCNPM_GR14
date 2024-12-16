package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import com.example.model.NHANKHAU;
import com.example.util.DatabaseUtil;

public class NHANKHAUDao implements DAOInterface<NHANKHAU> {

    @Override
    public int insert(NHANKHAU t) {
        String sql = "INSERT INTO NHANKHAU (CCCD, CCCDchuho, Hovaten, Gioitinh, Ngaysinh, Dantoc, Tongiao, Quoctich, Diachi, Sdt, Email, Quanhe, Trangthai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCCCD());
            stmt.setString(2, t.getCCCDchuho());
            stmt.setString(3, t.getHovaten());
            stmt.setString(4, t.getGioitinh());
            stmt.setDate(5, (t.getNgaysinh()));
            stmt.setString(6, t.getDantoc());
            stmt.setString(7, t.getTongiao());
            stmt.setString(8, t.getQuoctich());
            stmt.setString(9, t.getDiachi());
            stmt.setString(10, t.getSdt());
            stmt.setString(11, t.getEmail());
            stmt.setString(12, t.getQuanhe());
            stmt.setString(13, t.getTrangthai());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm NHANKHAU", e);
        }
    }

    @Override
    public int update(NHANKHAU t) {
        String sql = "UPDATE NHANKHAU SET Hovaten = ?, Gioitinh = ?, Ngaysinh = ?, Dantoc = ?, Tongiao = ?, Quoctich = ?, Diachi = ?, Sdt = ?, Email = ?, Quanhe = ?, Trangthai = ? WHERE CCCD = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getCCCD());
            stmt.setString(2, t.getHovaten());
            stmt.setString(3, t.getGioitinh());
            stmt.setDate(4,t.getNgaysinh());
            stmt.setString(5, t.getDantoc());
            stmt.setString(6, t.getTongiao());
            stmt.setString(7, t.getQuoctich());
            stmt.setString(8, t.getDiachi());
            stmt.setString(9, t.getSdt());
            stmt.setString(10, t.getEmail());
            stmt.setString(11, t.getQuanhe());
            stmt.setString(12, t.getTrangthai());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật NHANKHAU", e);
        }
    }

    @Override
    public boolean delete(NHANKHAU t) {
        String sql = "DELETE FROM NHANKHAU WHERE CCCD = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCCCD());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi xóa NHANKHAU", e);
        }
    }

    @Override
    public ArrayList<NHANKHAU> selectAll() {
        String sql = "SELECT * FROM NHANKHAU";
        ArrayList<NHANKHAU> list = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                NHANKHAU nhankhau = new NHANKHAU();
                nhankhau.setCCCD(rs.getString("CCCD"));
                nhankhau.setCCCDchuho(rs.getString("CCCDchuho"));
                nhankhau.setHovaten(rs.getString("Hovaten"));
                nhankhau.setGioitinh(rs.getString("Gioitinh"));
                nhankhau.setNgaysinh(rs.getDate("Ngaysinh"));
                nhankhau.setDantoc(rs.getString("Dantoc"));
                nhankhau.setTongiao(rs.getString("Tongiao"));
                nhankhau.setQuoctich(rs.getString("Quoctich"));
                nhankhau.setDiachi(rs.getString("Diachi"));
                nhankhau.setSdt(rs.getString("Sdt"));
                nhankhau.setEmail(rs.getString("Email"));
                nhankhau.setQuanhe(rs.getString("Quanhe"));
                nhankhau.setTrangthai(rs.getString("Trangthai"));
                list.add(nhankhau);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn tất cả NHANKHAU", e);
        }

        return list;
    }

    @Override
    public NHANKHAU selectById(NHANKHAU t) {
        String sql = "SELECT * FROM NHANKHAU WHERE CCCD = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCCCD());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    NHANKHAU nhankhau = new NHANKHAU();
                    nhankhau.setCCCD(rs.getString("CCCD"));
                    nhankhau.setCCCDchuho(rs.getString("CCCDchuho"));
                    nhankhau.setHovaten(rs.getString("Hovaten"));
                    nhankhau.setGioitinh(rs.getString("Gioitinh"));
                    nhankhau.setNgaysinh(rs.getDate("Ngaysinh"));
                    nhankhau.setDantoc(rs.getString("Dantoc"));
                    nhankhau.setTongiao(rs.getString("Tongiao"));
                    nhankhau.setQuoctich(rs.getString("Quoctich"));
                    nhankhau.setDiachi(rs.getString("Diachi"));
                    nhankhau.setSdt(rs.getString("Sdt"));
                    nhankhau.setEmail(rs.getString("Email"));
                    nhankhau.setQuanhe(rs.getString("Quanhe"));
                    nhankhau.setTrangthai(rs.getString("Trangthai"));
                    return nhankhau;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn NHANKHAU theo CCCD", e);
        }
        return null;
    }

	@Override
	public ArrayList<NHANKHAU> selectByCondition(String Condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
