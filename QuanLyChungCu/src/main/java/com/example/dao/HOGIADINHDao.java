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
		String sql = "UPDATE HOGIADINH SET Hotenchuho = ?, Sdt = ?,  Idcanho = ?, Sothanhvien = ?, Tang = ?, Sonha = ?,Sooto = ?, Soxemay = ? WHERE CCCDchuho = ? ";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			// stmt.setString(1, t.getCCCDchuho());
			stmt.setString(1, t.getHotenchuho());
			stmt.setString(2, t.getSdt());
			stmt.setInt(3, t.getIdcanho());
			stmt.setInt(4, t.getSothanhvien());
			stmt.setInt(5, t.getTang());
			stmt.setString(6, t.getSonha());
			stmt.setInt(7,t.getSooto());
			stmt.setInt(8,t.getSoxemay());
			stmt.setString(9, t.getCccdchuho());
			
			return stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error while updating HOGIADINH: " + e.getMessage());
			throw new RuntimeException("Error while updating HOGIADINH", e);
		}
	}

	@Override
	public boolean delete(HOGIADINH t) {
		String sql1 = "DELETE FROM KHOANDONGGOP WHERE CCCDchuho = ?";
		String sql2 = "DELETE FROM NHANKHAU WHERE CCCDchuho = ?";
		String sql3 = "DELETE FROM HOGIADINH WHERE CCCDchuho = ?";
		try (Connection conn = DatabaseUtil.getConnection()) {
			conn.setAutoCommit(false); // Bắt đầu transaction
	
			try (PreparedStatement stmt1 = conn.prepareStatement(sql1);
				 PreparedStatement stmt2 = conn.prepareStatement(sql2);
				 PreparedStatement stmt3 = conn.prepareStatement(sql3)) {
	
				// Xóa từ bảng KHOADONGGOP
				stmt1.setString(1, t.getCccdchuho());
				int rowsDeleted1 = stmt1.executeUpdate();
	
				// Xóa từ bảng NHANKHAU
				stmt2.setString(1, t.getCccdchuho());
				int rowsDeleted2 = stmt2.executeUpdate();
	
				// Xóa từ bảng HOGIADINH
				stmt3.setString(1, t.getCccdchuho());
				int rowsDeleted3 = stmt3.executeUpdate();
	
				conn.commit(); // Commit transaction
	
				return rowsDeleted1 > 0 && rowsDeleted2 > 0 && rowsDeleted3 > 0;
			} catch (SQLException e) {
				conn.rollback(); // Rollback transaction nếu có lỗi
				System.err.println("Error while deleting HOGIADINH: " + e.getMessage());
				throw new RuntimeException("Error while deleting HOGIADINH", e);
			}
		} catch (SQLException e) {
			System.err.println("Error while connecting to the database: " + e.getMessage());
			throw new RuntimeException("Error while connecting to the database", e);
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
	public String selectCCCD(String t) {
		String sql = "SELECT CCCDchuho FROM HOGIADINH WHERE Sonha = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, t);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					HOGIADINH hogiadinh = new HOGIADINH();
					return rs.getString("CCCDchuho");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error while selecting HOGIADINH by ID: " + e.getMessage());
			throw new RuntimeException("Error while selecting HOGIADINH by ID", e);
		}
		return null;
	}
	public int updateNK(String cccd) {
        String sql = "UPDATE HOGIADINH SET Sothanhvien = (SELECT COUNT(*) FROM NHANKHAU WHERE CCCDchuho = ?)+1 WHERE CCCDchuho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cccd);
				stmt.setString(2, cccd);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật HOGIADINH", e);
        }
    }
}
