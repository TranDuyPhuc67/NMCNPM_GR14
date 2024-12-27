package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.util.DatabaseUtil;

public class TONGTHANHTOANDao {

	public List<Map<String, Object>> getAll() {
	    List<Map<String, Object>> list = new ArrayList<>();
	    String sql = """
	            SELECT
	                ttt.Idcanho,
	                COALESCE(h.Hotenchuho, 'Không rõ') AS Hotenchuho,
	                c.Sonha,
	                ttt.Tongphichungcu,
	                ttt.Tongtienich,
	                ttt.Tongguixe,
	                ttt.Tongphi,
	                ttt.Sotiendanop,
	                ttt.Sodu,
	                ttt.Trangthai,
	                ttt.Hanthu,
	                ttt.Thoigianthu,
	                COALESCE((
	                    SELECT Sodu 
	                    FROM TONGTHANHTOAN 
	                    WHERE Idcanho = ttt.Idcanho 
	                    AND Hanthu = DATE_FORMAT(DATE_ADD(CONCAT(ttt.Hanthu, '-01'), INTERVAL -1 MONTH), '%Y-%m')
	                ), 0) AS sodu_thang_truoc
	            FROM TONGTHANHTOAN ttt
	            LEFT JOIN CANHO c ON ttt.Idcanho = c.Idcanho
	            LEFT JOIN HOGIADINH h ON c.Idcanho = h.Idcanho;
	            """;

	    try (Connection conn = DatabaseUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            Map<String, Object> row = new HashMap<>();
	            row.put("idcanho", rs.getInt("Idcanho"));
	            row.put("hotenchuho", rs.getString("Hotenchuho"));
	            row.put("sonha", rs.getString("Sonha"));
	            row.put("tongphichungcu", rs.getInt("Tongphichungcu"));
	            row.put("tongtienich", rs.getInt("Tongtienich"));
	            row.put("tongguixe", rs.getInt("Tongguixe"));
	            row.put("tongphi", rs.getInt("Tongphi"));
	            row.put("sotiendanop", rs.getInt("Sotiendanop"));
	            row.put("sodu", rs.getInt("Sodu"));
	            row.put("trangthai", rs.getString("Trangthai"));
	            row.put("hanthu", rs.getString("Hanthu"));
	            row.put("thoigianthu", rs.getDate("Thoigianthu"));
	            row.put("sodu_thang_truoc", rs.getInt("sodu_thang_truoc")); // Thêm số dư tháng trước
	            list.add(row);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error in getAll: " + e.getMessage());
	    }
	    return list;
	}


	public void insertOrUpdateTongThanhToan() {
		String sql = """
				INSERT INTO TONGTHANHTOAN (Idcanho, Tongphichungcu, Tongtienich, Tongguixe, Tongphi, Thoigianthu, Hanthu)
				SELECT
				    pcc.Idcanho,
				    COALESCE(pcc.Tongphichungcu, 0) AS Tongphichungcu,
				    COALESCE(ti.Tongtienich, 0) AS Tongtienich,
				    COALESCE(pgx.Tongguixe, 0) AS Tongguixe,
				    (COALESCE(pcc.Tongphichungcu, 0) + COALESCE(ti.Tongtienich, 0) + COALESCE(pgx.Tongguixe, 0)) AS Tongphi,
				    pcc.Thoigianthu,
				    pcc.Hanthu
				FROM PHICHUNGCU pcc
				LEFT JOIN TIENICH ti ON pcc.Idcanho = ti.Idcanho AND pcc.Hanthu = ti.Hanthu
				LEFT JOIN PHIGUIXE pgx ON pcc.Idcanho = pgx.Idcanho AND pcc.Hanthu = pgx.Hanthu
				ON DUPLICATE KEY UPDATE
				    Tongphichungcu = VALUES(Tongphichungcu),
				    Tongtienich = VALUES(Tongtienich),
				    Tongguixe = VALUES(Tongguixe),
				    Tongphi = VALUES(Tongphi),
				    Thoigianthu = VALUES(Thoigianthu),
				    Hanthu = VALUES(Hanthu);
				""";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			int affectedRows = stmt.executeUpdate();
			System.out.println("Rows affected in insertOrUpdateTongThanhToan: " + affectedRows);
		} catch (SQLException e) {
			System.out.println("Error in insertOrUpdateTongThanhToan: " + e.getMessage());
		}
	}

	public int getPreviousSodu(int idCanHo, String hanThu) {
		String sql = """
				SELECT Sodu
				FROM TONGTHANHTOAN
				WHERE Idcanho = ?
				  AND Hanthu = DATE_FORMAT(DATE_ADD(CONCAT(?, '-01'), INTERVAL -1 MONTH), '%Y-%m');
				""";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idCanHo);
			stmt.setString(2, hanThu);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("Sodu");
			}
		} catch (SQLException e) {
			System.out.println("Error in getPreviousSodu: " + e.getMessage());
		}
		return 0; // Mặc định trả về 0 nếu không có số dư tháng trước
	}

	public void updateSoTienDaNopForCurrentMonth(int idCanHo, String hanThu, int soTienDaNop) {
	    String getPreviousSoduSql = """
	            SELECT Sodu
	            FROM TONGTHANHTOAN
	            WHERE Idcanho = ? AND Hanthu = DATE_FORMAT(DATE_ADD(CONCAT(?, '-01'), INTERVAL -1 MONTH), '%Y-%m');
	            """;

	    String updateSql = """
	            UPDATE TONGTHANHTOAN
	            SET
	                Sotiendanop = Sotiendanop + ?, -- Cộng số tiền vừa nộp
	                Sodu = (? + ? + Sotiendanop - Tongphi), -- Tính số dư mới
	                Trangthai = CASE
	                    WHEN (? + ? + Sotiendanop - Tongphi) = 0 THEN 'Đã thanh toán'
	                    WHEN (? + ? + Sotiendanop - Tongphi) > 0 THEN 'Còn dư'
	                    ELSE 'Còn thiếu'
	                END
	            WHERE Idcanho = ? AND Hanthu = ?;
	            """;

	    try (Connection conn = DatabaseUtil.getConnection()) {
	        // Lấy số dư tháng trước
	        int previousSodu = 0;
	        try (PreparedStatement stmt = conn.prepareStatement(getPreviousSoduSql)) {
	            stmt.setInt(1, idCanHo);
	            stmt.setString(2, hanThu);
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                previousSodu = rs.getInt("Sodu");
	            }
	        }

	        // Cập nhật dữ liệu
	        try (PreparedStatement stmt = conn.prepareStatement(updateSql)) {
	            stmt.setInt(1, soTienDaNop); // Số tiền vừa nộp
	            stmt.setInt(2, previousSodu); // Số dư tháng trước
	            stmt.setInt(3, 0); // Số tiền vừa nộp
	            stmt.setInt(4, previousSodu); // Số dư tháng trước
	            stmt.setInt(5, soTienDaNop); // Số tiền vừa nộp
	            stmt.setInt(6, previousSodu); // Số dư tháng trước
	            stmt.setInt(7, soTienDaNop); // Số tiền vừa nộp
	            stmt.setInt(8, idCanHo); // ID căn hộ
	            stmt.setString(9, hanThu); // Hạn thu (YYYY-MM)

	            int rowsAffected = stmt.executeUpdate();
	            System.out.println("Rows updated in updateSoTienDaNopForCurrentMonth: " + rowsAffected);
	            System.out.println("Updating IdCanHo: " + idCanHo + ", HanThu: " + hanThu + ", SoTienDaNop: " + soTienDaNop);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error in updateSoTienDaNopForCurrentMonth: " + e.getMessage());
	    }
	}

	public String getTrangThai(int idCanHo, String hanThu) {
		String sql = """
				SELECT Trangthai
				FROM TONGTHANHTOAN
				WHERE Idcanho = ? AND Hanthu = ?;
				""";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, idCanHo);
			stmt.setString(2, hanThu);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("Trangthai");
			}
		} catch (SQLException e) {
			System.out.println("Error in getTrangThai: " + e.getMessage());
		}
		return null;
	}

	public List<Map<String, Object>> search(String sonha, String hotenchuho, String month, String year) {
		List<Map<String, Object>> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder("""
				SELECT
				    ttt.Idcanho,
				    COALESCE(h.Hotenchuho, 'Không rõ') AS Hotenchuho,
				    c.Sonha,
				    ttt.Tongphichungcu,
				    ttt.Tongtienich,
				    ttt.Tongguixe,
				    ttt.Tongphi,
				    ttt.Sotiendanop,
				    ttt.Sodu,
				    ttt.Trangthai,
				    ttt.Hanthu,
				    ttt.Thoigianthu,
				    COALESCE((
	                    SELECT Sodu 
	                    FROM TONGTHANHTOAN 
	                    WHERE Idcanho = ttt.Idcanho 
	                    AND Hanthu = DATE_FORMAT(DATE_ADD(CONCAT(ttt.Hanthu, '-01'), INTERVAL -1 MONTH), '%Y-%m')
	                ), 0) AS sodu_thang_truoc
				FROM TONGTHANHTOAN ttt
				LEFT JOIN CANHO c ON ttt.Idcanho = c.Idcanho
				LEFT JOIN HOGIADINH h ON c.Idcanho = h.Idcanho
				WHERE 1=1
				""");

		if (sonha != null && !sonha.isEmpty()) {
			sql.append(" AND c.Sonha LIKE ?");
		}
		if (hotenchuho != null && !hotenchuho.isEmpty()) {
			sql.append(" AND h.Hotenchuho LIKE ?");
		}
		if (month != null && !month.isEmpty() && year != null && !year.isEmpty()) {
			sql.append(" AND ttt.Hanthu = ?");
		}

		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

			int paramIndex = 1;

			if (sonha != null && !sonha.isEmpty()) {
				stmt.setString(paramIndex++, "%" + sonha + "%");
			}
			if (hotenchuho != null && !hotenchuho.isEmpty()) {
				stmt.setString(paramIndex++, "%" + hotenchuho + "%");
			}
			if (month != null && !month.isEmpty() && year != null && !year.isEmpty()) {
				String hanthu = year + "-" + String.format("%02d", Integer.parseInt(month));
				stmt.setString(paramIndex++, hanthu);
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Map<String, Object> row = new HashMap<>();
				row.put("idcanho", rs.getInt("Idcanho"));
				row.put("hotenchuho", rs.getString("Hotenchuho"));
				row.put("sonha", rs.getString("Sonha"));
				row.put("tongphichungcu", rs.getInt("Tongphichungcu"));
				row.put("tongtienich", rs.getInt("Tongtienich"));
				row.put("tongguixe", rs.getInt("Tongguixe"));
				row.put("tongphi", rs.getInt("Tongphi"));
				row.put("sotiendanop", rs.getInt("Sotiendanop"));
				row.put("sodu", rs.getInt("Sodu"));
				row.put("trangthai", rs.getString("Trangthai"));
				row.put("hanthu", rs.getString("Hanthu"));
				row.put("thoigianthu", rs.getDate("Thoigianthu"));
	            row.put("sodu_thang_truoc", rs.getInt("sodu_thang_truoc")); // Thêm số dư tháng trước
				list.add(row);
			}
		} catch (SQLException e) {
			System.out.println("Error in search: " + e.getMessage());
		}
		return list;
	}
}
