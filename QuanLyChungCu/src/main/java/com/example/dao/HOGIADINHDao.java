package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.model.HOGIADINH;
import com.example.util.DatabaseUtil;

public class HOGIADINHDao implements DAOInterface<HOGIADINH> {

	@Override
	public int insert(HOGIADINH t) {
		String sql = "INSERT INTO HOGIADINH (CCCDchuho, Sothanhvien, Hotenchuho, Gioitinh, Ngaysinh, Dantoc, Tongiao, Quoctich, Diachi, Sdt, Email, Trangthai) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, t.getCCCDchuho());
			stmt.setInt(2, t.getSothanhvien());
			stmt.setString(3, t.getHotenchuho());
			stmt.setString(4, t.getGioitinh());
			stmt.setDate(5, t.getNgaysinh());
			stmt.setString(6, t.getDantoc());
			stmt.setString(7, t.getTongiao());
			stmt.setString(8, t.getQuoctich());
			stmt.setString(9, t.getDiachi());
			stmt.setString(10, t.getSdt());
			stmt.setString(11, t.getEmail());
			stmt.setString(12, t.getTrangthai());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error while inserting HOGIADINH: " + e.getMessage());
			throw new RuntimeException("Error while inserting HOGIADINH", e);
		}
	}

	@Override
	public int update(HOGIADINH t) {
		String sql = "UPDATE HOGIADINH SET Sothanhvien = ?, Hotenchuho = ?, Gioitinh = ?, Ngaysinh = ?, Dantoc = ?, Tongiao = ?, Quoctich = ?, Diachi = ?, Sdt = ?, Email = ?, Trangthai = ? "
				+ "WHERE CCCDchuho = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, t.getSothanhvien());
			stmt.setString(2, t.getHotenchuho());
			stmt.setString(3, t.getGioitinh());
			stmt.setDate(4, t.getNgaysinh());
			stmt.setString(5, t.getDantoc());
			stmt.setString(6, t.getTongiao());
			stmt.setString(7, t.getQuoctich());
			stmt.setString(8, t.getDiachi());
			stmt.setString(9, t.getSdt());
			stmt.setString(10, t.getEmail());
			stmt.setString(11, t.getTrangthai());
			stmt.setString(12, t.getCCCDchuho());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error while updating HOGIADINH: " + e.getMessage());
			throw new RuntimeException("Error while updating HOGIADINH", e);
		}
	}

	@Override
	public boolean delete(HOGIADINH t) {
		String sql = "DELETE FROM HOGIADINH WHERE CCCDchuho = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, t.getCCCDchuho());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
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
				hogiadinh.setCCCDchuho(rs.getString("CCCDchuho"));
				hogiadinh.setSothanhvien(rs.getInt("Sothanhvien"));
				hogiadinh.setHotenchuho(rs.getString("Hotenchuho"));
				hogiadinh.setGioitinh(rs.getString("Gioitinh"));
				hogiadinh.setNgaysinh(rs.getDate("Ngaysinh"));
				hogiadinh.setDantoc(rs.getString("Dantoc"));
				hogiadinh.setTongiao(rs.getString("Tongiao"));
				hogiadinh.setQuoctich(rs.getString("Quoctich"));
				hogiadinh.setDiachi(rs.getString("Diachi"));
				hogiadinh.setSdt(rs.getString("Sdt"));
				hogiadinh.setEmail(rs.getString("Email"));
				hogiadinh.setTrangthai(rs.getString("Trangthai"));
				list.add(hogiadinh);
			}
		} catch (SQLException e) {
			System.err.println("Error while selecting all HOGIADINH: " + e.getMessage());
			throw new RuntimeException("Error while selecting all HOGIADINH", e);
		}

		return list;
	}

	@Override
	public HOGIADINH selectById(HOGIADINH t) {
		String sql = "SELECT * FROM HOGIADINH WHERE CCCDchuho = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, t.getCCCDchuho());
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					HOGIADINH hogiadinh = new HOGIADINH();
					hogiadinh.setCCCDchuho(rs.getString("CCCDchuho"));
					hogiadinh.setSothanhvien(rs.getInt("Sothanhvien"));
					hogiadinh.setHotenchuho(rs.getString("Hotenchuho"));
					hogiadinh.setGioitinh(rs.getString("Gioitinh"));
					hogiadinh.setNgaysinh(rs.getDate("Ngaysinh"));
					hogiadinh.setDantoc(rs.getString("Dantoc"));
					hogiadinh.setTongiao(rs.getString("Tongiao"));
					hogiadinh.setQuoctich(rs.getString("Quoctich"));
					hogiadinh.setDiachi(rs.getString("Diachi"));
					hogiadinh.setSdt(rs.getString("Sdt"));
					hogiadinh.setEmail(rs.getString("Email"));
					hogiadinh.setTrangthai(rs.getString("Trangthai"));
					return hogiadinh;
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while selecting HOGIADINH by ID: " + e.getMessage());
			throw new RuntimeException("Error while selecting HOGIADINH by ID", e);
		}
		return null;
	}

	@Override
	public ArrayList<HOGIADINH> selectByCondition(String condition) {
		String sql = "SELECT * FROM HOGIADINH WHERE " + condition;
		ArrayList<HOGIADINH> list = new ArrayList<>();

		if (condition == null || condition.isEmpty()) {
			throw new IllegalArgumentException("Condition cannot be null or empty");
		}

		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				HOGIADINH hogiadinh = new HOGIADINH();
				hogiadinh.setCCCDchuho(rs.getString("CCCDchuho"));
				hogiadinh.setSothanhvien(rs.getInt("Sothanhvien"));
				hogiadinh.setHotenchuho(rs.getString("Hotenchuho"));
				hogiadinh.setGioitinh(rs.getString("Gioitinh"));
				hogiadinh.setNgaysinh(rs.getDate("Ngaysinh"));
				hogiadinh.setDantoc(rs.getString("Dantoc"));
				hogiadinh.setTongiao(rs.getString("Tongiao"));
				hogiadinh.setQuoctich(rs.getString("Quoctich"));
				hogiadinh.setDiachi(rs.getString("Diachi"));
				hogiadinh.setSdt(rs.getString("Sdt"));
				hogiadinh.setEmail(rs.getString("Email"));
				hogiadinh.setTrangthai(rs.getString("Trangthai"));
				list.add(hogiadinh);
			}
		} catch (SQLException e) {
			System.err.println("Error while selecting HOGIADINH by condition: " + e.getMessage());
			throw new RuntimeException("Error while selecting HOGIADINH by condition", e);
		}

		return list;
	}
}
