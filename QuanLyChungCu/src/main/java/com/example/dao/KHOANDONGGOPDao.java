package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.model.KHOANDONGGOP;
import com.example.util.DatabaseUtil;

public class KHOANDONGGOPDao {

	// Thêm một khoản đóng góp vào bảng KHOANDONGGOP
	public int insert(KHOANDONGGOP t) {
		String sql = """
				    INSERT INTO KHOANDONGGOP (CCCDchuho, QuyVNN, QuyVBD, QuyTDP, QuyVTT, QuyVNDTT, QuyTN, QuyKH, QuyNCT, Tongthu, Ngaythu)
				    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, t.getCCCDchuho());
			stmt.setInt(2, t.getQuyVNN());
			stmt.setInt(3, t.getQuyVBD());
			stmt.setInt(4, t.getQuyTDP());
			stmt.setInt(5, t.getQuyVTT());
			stmt.setInt(6, t.getQuyVNDTT());
			stmt.setInt(7, t.getQuyTN());
			stmt.setInt(8, t.getQuyKH());
			stmt.setInt(9, t.getQuyNCT());
			stmt.setInt(10, t.getTongthu());
			stmt.setDate(11, t.getNgaythu());

			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Cập nhật thông tin khoản đóng góp
	public int update(KHOANDONGGOP t) {
		String sql = """
				    UPDATE KHOANDONGGOP
				    SET QuyVNN = ?, QuyVBD = ?, QuyTDP = ?, QuyVTT = ?, QuyVNDTT = ?, QuyTN = ?, QuyKH = ?, QuyNCT = ?, Tongthu = ?, Ngaythu = ?
				    WHERE CCCDchuho = ?
				""";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, t.getQuyVNN());
			stmt.setInt(2, t.getQuyVBD());
			stmt.setInt(3, t.getQuyTDP());
			stmt.setInt(4, t.getQuyVTT());
			stmt.setInt(5, t.getQuyVNDTT());
			stmt.setInt(6, t.getQuyTN());
			stmt.setInt(7, t.getQuyKH());
			stmt.setInt(8, t.getQuyNCT());
			stmt.setInt(9, t.getTongthu());
			stmt.setDate(10, t.getNgaythu());
			stmt.setString(11, t.getCCCDchuho());

			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Lấy danh sách chi tiết từ bảng HOGIADINH, CANHO và KHOANDONGGOP
	public ArrayList<Map<String, Object>> getAllWithDetails() {
		ArrayList<Map<String, Object>> list = new ArrayList<>();
		String sql = """
				    SELECT
				        h.CCCDchuho,
				        h.Hotenchuho AS TenChuHo,
				        c.Sonha AS SoNha,
				        IFNULL(k.QuyVNN, 0) AS QuyVNN,
				        IFNULL(k.QuyVBD, 0) AS QuyVBD,
				        IFNULL(k.QuyTDP, 0) AS QuyTDP,
				        IFNULL(k.QuyVTT, 0) AS QuyVTT,
				        IFNULL(k.QuyVNDTT, 0) AS QuyVNDTT,
				        IFNULL(k.QuyTN, 0) AS QuyTN,
				        IFNULL(k.QuyKH, 0) AS QuyKH,
				        IFNULL(k.QuyNCT, 0) AS QuyNCT,
				        IFNULL(k.Tongthu, 0) AS TongThu,
				        k.Ngaythu AS NgayThu
				    FROM
				        HOGIADINH h
				    JOIN
				        CANHO c ON h.Idcanho = c.Idcanho
				    LEFT JOIN
				        KHOANDONGGOP k ON h.CCCDchuho = k.CCCDchuho
				    ORDER BY
				        h.Hotenchuho;
				""";

		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Map<String, Object> item = new HashMap<>();
				item.put("CCCDchuho", rs.getString("CCCDchuho"));
				item.put("TenChuHo", rs.getString("TenChuHo"));
				item.put("SoNha", rs.getString("SoNha"));
				item.put("QuyVNN", rs.getInt("QuyVNN"));
				item.put("QuyVBD", rs.getInt("QuyVBD"));
				item.put("QuyTDP", rs.getInt("QuyTDP"));
				item.put("QuyVTT", rs.getInt("QuyVTT"));
				item.put("QuyVNDTT", rs.getInt("QuyVNDTT"));
				item.put("QuyTN", rs.getInt("QuyTN"));
				item.put("QuyKH", rs.getInt("QuyKH"));
				item.put("QuyNCT", rs.getInt("QuyNCT"));
				item.put("TongThu", rs.getInt("TongThu"));
				item.put("NgayThu", rs.getDate("NgayThu"));

				list.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// Đồng bộ hóa dữ liệu từ bảng HOGIADINH vào KHOANDONGGOP
	public void synchronizeKHOANDONGGOP() {
		String sql = """
				    INSERT INTO KHOANDONGGOP (CCCDchuho, QuyVNN, QuyVBD, QuyTDP, QuyVTT, QuyVNDTT, QuyTN, QuyKH, QuyNCT, Tongthu, Ngaythu)
				    SELECT CCCDchuho, 0, 0, 0, 0, 0, 0, 0, 0, 0, NULL
				    FROM HOGIADINH
				    WHERE CCCDchuho NOT IN (SELECT CCCDchuho FROM KHOANDONGGOP)
				""";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Kiểm tra xem CCCD chủ hộ đã tồn tại trong bảng KHOANDONGGOP hay chưa
	public boolean existsInKHOANDONGGOP(String cccdchuho) {
		String sql = "SELECT COUNT(*) FROM KHOANDONGGOP WHERE CCCDchuho = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setString(1, cccdchuho);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0; // Nếu số lượng lớn hơn 0, tức là tồn tại
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Map<String, Object>> searchKHOANDONGGOP(String sonha, String tenChuHo) {
		ArrayList<Map<String, Object>> list = new ArrayList<>();

		StringBuilder sqlBuilder = new StringBuilder("""
				    SELECT
				        h.CCCDchuho,
				        h.Hotenchuho AS TenChuHo,
				        c.Sonha AS SoNha,
				        IFNULL(k.QuyVNN, 0) AS QuyVNN,
				        IFNULL(k.QuyVBD, 0) AS QuyVBD,
				        IFNULL(k.QuyTDP, 0) AS QuyTDP,
				        IFNULL(k.QuyVTT, 0) AS QuyVTT,
				        IFNULL(k.QuyVNDTT, 0) AS QuyVNDTT,
				        IFNULL(k.QuyTN, 0) AS QuyTN,
				        IFNULL(k.QuyKH, 0) AS QuyKH,
				        IFNULL(k.QuyNCT, 0) AS QuyNCT,
				        IFNULL(k.Tongthu, 0) AS TongThu,
				        k.Ngaythu AS NgayThu
				    FROM
				        HOGIADINH h
				    JOIN
				        CANHO c ON h.Idcanho = c.Idcanho
				    LEFT JOIN
				        KHOANDONGGOP k ON h.CCCDchuho = k.CCCDchuho
				    WHERE 1=1
				""");

		// Thêm các điều kiện tìm kiếm nếu có
		if (sonha != null && !sonha.trim().isEmpty()) {
			sqlBuilder.append(" AND c.Sonha LIKE ? ");
		}
		if (tenChuHo != null && !tenChuHo.trim().isEmpty()) {
			sqlBuilder.append(" AND h.Hotenchuho LIKE ? ");
		}

		sqlBuilder.append(" ORDER BY h.Hotenchuho;");

		String sql = sqlBuilder.toString();

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			int paramIndex = 1;
			if (sonha != null && !sonha.trim().isEmpty()) {
				stmt.setString(paramIndex++, "%" + sonha + "%");
			}
			if (tenChuHo != null && !tenChuHo.trim().isEmpty()) {
				stmt.setString(paramIndex++, "%" + tenChuHo + "%");
			}

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Map<String, Object> item = new HashMap<>();
					item.put("CCCDchuho", rs.getString("CCCDchuho"));
					item.put("TenChuHo", rs.getString("TenChuHo"));
					item.put("SoNha", rs.getString("SoNha"));
					item.put("QuyVNN", rs.getInt("QuyVNN"));
					item.put("QuyVBD", rs.getInt("QuyVBD"));
					item.put("QuyTDP", rs.getInt("QuyTDP"));
					item.put("QuyVTT", rs.getInt("QuyVTT"));
					item.put("QuyVNDTT", rs.getInt("QuyVNDTT"));
					item.put("QuyTN", rs.getInt("QuyTN"));
					item.put("QuyKH", rs.getInt("QuyKH"));
					item.put("QuyNCT", rs.getInt("QuyNCT"));
					item.put("TongThu", rs.getInt("TongThu"));
					item.put("NgayThu", rs.getDate("NgayThu"));

					list.add(item);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}
