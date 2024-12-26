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
		String sql = "INSERT INTO HOGIADINH (CCCDchuho, Idcanho, Sothanhvien, Hotenchuho, Gioitinh, Ngaysinh, Dantoc, Tongiao, Quoctich, Diachi, Sdt, Trangthai, Soxemay, Sooto, Tang, Sonha) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, t.getCccdchuho());
			stmt.setInt(2, t.getIdcanho());
			stmt.setInt(3, t.getSothanhvien());
			stmt.setString(4, t.getHotenchuho());
			stmt.setString(5, t.getGioitinh());
			stmt.setDate(6, t.getNgaysinh());
			stmt.setString(7, t.getDantoc());
			stmt.setString(8, t.getTongiao());
			stmt.setString(9, t.getQuoctich());
			stmt.setString(10, t.getDiachi());
			stmt.setString(11, t.getSdt());
			stmt.setString(12, t.getTrangthai());
			stmt.setInt(13, t.getSoxemay());
			stmt.setInt(14, t.getSooto());
			stmt.setInt(15, t.getTang());
			stmt.setString(16, t.getSonha());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error while inserting HOGIADINH: " + e.getMessage());
			throw new RuntimeException("Error while inserting HOGIADINH", e);
		}
	}

	@Override
	public int update(HOGIADINH t) {
		String sql = "UPDATE HOGIADINH SET Sdt = ?,  Idcanho = ?, Sothanhvien = ?, Tang = ?, Sonha = ? WHERE CCCDchuho = ? AND Hotenchuho = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			// stmt.setString(1, t.getCCCDchuho());
			stmt.setString(1, t.getSdt());
			stmt.setInt(2, t.getIdcanho());
			stmt.setInt(3, t.getSothanhvien());
			stmt.setInt(4, t.getTang());
			stmt.setString(5, t.getSonha());
			stmt.setString(6, t.getCccdchuho());
			stmt.setString(7, t.getHotenchuho());
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

			stmt.setString(1, t.getCccdchuho());
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
				hogiadinh.setIdcanho(rs.getInt("Idcanho"));
				hogiadinh.setSothanhvien(rs.getInt("Sothanhvien"));
				hogiadinh.setHotenchuho(rs.getString("Hotenchuho"));
				hogiadinh.setGioitinh(rs.getString("Gioitinh"));
				hogiadinh.setNgaysinh(rs.getDate("Ngaysinh"));
				hogiadinh.setDantoc(rs.getString("Dantoc"));
				hogiadinh.setTongiao(rs.getString("Tongiao"));
				hogiadinh.setQuoctich(rs.getString("Quoctich"));
				hogiadinh.setDiachi(rs.getString("Diachi"));
				hogiadinh.setSdt(rs.getString("Sdt"));
				hogiadinh.setTrangthai(rs.getString("Trangthai"));
				hogiadinh.setSoxemay(rs.getInt("Soxemay"));
				hogiadinh.setSooto(rs.getInt("Sooto"));
				hogiadinh.setTang(rs.getInt("Tang"));
				hogiadinh.setSonha(rs.getString("Sonha"));
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

			stmt.setString(1, t.getCccdchuho());
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					HOGIADINH hogiadinh = new HOGIADINH();
					hogiadinh.setCCCDchuho(rs.getString("CCCDchuho"));
					hogiadinh.setIdcanho(rs.getInt("Idcanho"));
					hogiadinh.setSothanhvien(rs.getInt("Sothanhvien"));
					hogiadinh.setHotenchuho(rs.getString("Hotenchuho"));
					hogiadinh.setGioitinh(rs.getString("Gioitinh"));
					hogiadinh.setNgaysinh(rs.getDate("Ngaysinh"));
					hogiadinh.setDantoc(rs.getString("Dantoc"));
					hogiadinh.setTongiao(rs.getString("Tongiao"));
					hogiadinh.setQuoctich(rs.getString("Quoctich"));
					hogiadinh.setDiachi(rs.getString("Diachi"));
					hogiadinh.setSdt(rs.getString("Sdt"));
					hogiadinh.setTrangthai(rs.getString("Trangthai"));
					hogiadinh.setSoxemay(rs.getInt("Soxemay"));
					hogiadinh.setSooto(rs.getInt("Sooto"));
					hogiadinh.setTang(rs.getInt("Tang"));
					hogiadinh.setSonha(rs.getString("Sonha"));
					return hogiadinh;
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while selecting HOGIADINH by ID: " + e.getMessage());
			throw new RuntimeException("Error while selecting HOGIADINH by ID", e);
		}
		return null;
	}
	public int updateChuHo(HOGIADINH t) {
		String sql = "UPDATE HOGIADINH SET Hotenchuho = ?,CCCDchuho = ?, Gioitinh = ?, Ngaysinh = ?, Dantoc = ?, Tongiao = ?, Quoctich = ?, Diachi = ?, Sdt = ?, Trangthai = ? "
				+ "WHERE CCCDchuho = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, t.getHotenchuho());
			stmt.setString(2, t.getCccdchuho());
			stmt.setString(3, t.getGioitinh());
			stmt.setDate(4, t.getNgaysinh());
			stmt.setString(5, t.getDantoc());
			stmt.setString(6, t.getTongiao());
			stmt.setString(7, t.getQuoctich());
			stmt.setString(8, t.getDiachi());
			stmt.setString(9, t.getSdt());
			stmt.setString(10, t.getTrangthai());
			stmt.setString(11, t.getCccdchuho());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error while updating HOGIADINH: " + e.getMessage());
			throw new RuntimeException("Error while updating HOGIADINH", e);
		}
	}
	@Override
	public ArrayList<HOGIADINH> selectByCondition(String condition) {
		String sql = "SELECT * FROM HOGIADINH WHERE Hotenchuho = ? OR Sdt = ? OR CCCDchuho = ? OR Sonha = ? OR Sothanhvien = ? OR Tang = ?";
		ArrayList<HOGIADINH> list = new ArrayList<>();

		if (condition == null || condition.isEmpty()) {
			throw new IllegalArgumentException("Condition cannot be null or empty");
		}
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, condition);
			stmt.setString(2, condition);
			stmt.setString(3, condition);
			stmt.setString(4, condition);
			try{
				stmt.setInt(5, Integer.parseInt(condition));
				stmt.setInt(6, Integer.parseInt(condition));
			}
			catch (NumberFormatException e){
				stmt.setInt(5, -1);
				stmt.setInt(6, -1);
			}
			try (ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
					HOGIADINH hogiadinh = new HOGIADINH();
					hogiadinh.setCCCDchuho(rs.getString("CCCDchuho"));
					hogiadinh.setIdcanho(rs.getInt("Idcanho"));
					hogiadinh.setSothanhvien(rs.getInt("Sothanhvien"));
					hogiadinh.setHotenchuho(rs.getString("Hotenchuho"));
					hogiadinh.setGioitinh(rs.getString("Gioitinh"));
					hogiadinh.setNgaysinh(rs.getDate("Ngaysinh"));
					hogiadinh.setDantoc(rs.getString("Dantoc"));
					hogiadinh.setTongiao(rs.getString("Tongiao"));
					hogiadinh.setQuoctich(rs.getString("Quoctich"));
					hogiadinh.setDiachi(rs.getString("Diachi"));
					hogiadinh.setSdt(rs.getString("Sdt"));
					hogiadinh.setTrangthai(rs.getString("Trangthai"));
					hogiadinh.setSoxemay(rs.getInt("Soxemay"));
					hogiadinh.setSooto(rs.getInt("Sooto"));
					hogiadinh.setTang(rs.getInt("Tang"));
					hogiadinh.setSonha(rs.getString("Sonha"));
					list.add(hogiadinh);
				}
			} 
		}
		catch (SQLException e) {
				System.err.println("Error while selecting HOGIADINH by condition: " + e.getMessage());
				throw new RuntimeException("Error while selecting HOGIADINH by condition", e);
		}
		
		return list;
	}
}
