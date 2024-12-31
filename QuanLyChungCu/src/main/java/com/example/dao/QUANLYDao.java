package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.model.QUANLY;
import com.example.util.DatabaseUtil;

public class QUANLYDao implements DAOInterface<QUANLY> {

	@Override
	public int insert(QUANLY t) {
		String query = "INSERT INTO QUANLY (username, password) VALUES (?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, t.getUsername());
			stmt.setString(2, t.getPassword());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public QUANLY authenticate(String username, String password) {
		String query = "SELECT * FROM QUANLY WHERE username = ? AND password = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new QUANLY(rs.getString("username"), rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<QUANLY> selectAll() {
		String query = "SELECT * FROM QUANLY";
		ArrayList<QUANLY> QUANLYList = new ArrayList<>();
		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				QUANLY QUANLY = new QUANLY(rs.getString("username"), rs.getString("password"));
				QUANLYList.add(QUANLY);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return QUANLYList;
	}

	@Override
	public QUANLY selectById(QUANLY t) {
		return null;
	}

	@Override
	public int update(QUANLY t) {
		String query = "UPDATE QUANLY SET username = ?, password = ? WHERE username = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, t.getUsername());
			stmt.setString(2, t.getPassword());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int update1(String username, String password, String newpassword) {
		String query = "UPDATE QUANLY SET password = ? WHERE username = ? AND password = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, newpassword);
			stmt.setString(2, username);
			stmt.setString(3, password);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean delete(QUANLY t) {
		String query = "DELETE FROM QUANLY WHERE username = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, t.getUsername());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<QUANLY> selectByCondition(String condition) {
		// TODO: Implement phương thức này nếu cần
		return null;
	}
}
