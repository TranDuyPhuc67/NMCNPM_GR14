package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.model.PHIGUIXE;
import com.example.util.DatabaseUtil;

public class PHIGUIXEDao implements DAOInterface<PHIGUIXE> {

	@Override
	public int insert(PHIGUIXE t) {
		String sql = "INSERT INTO PHIGUIXE (Idcanho, Tienxemay, Tienxeoto, Hanthu, Tongguixe) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, t.getIdcanho());
			stmt.setInt(2, t.getTienxemay());
			stmt.setInt(3, t.getTienxeoto());
			stmt.setString(4, t.getHanthu());
			stmt.setInt(5, t.getTongguixe());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Lỗi khi thêm phí gửi xe", e);
		}
	}

	@Override
	public int update(PHIGUIXE t) {
		String sql = "UPDATE PHIGUIXE SET Tienxemay = ?, Tienxeoto = ?, Tongguixe = ? WHERE Idcanho = ? AND Hanthu = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, t.getTienxemay());
			stmt.setInt(2, t.getTienxeoto());
			stmt.setInt(3, t.getTongguixe());
			stmt.setInt(4, t.getIdcanho());
			stmt.setString(5, t.getHanthu());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Lỗi khi cập nhật phí gửi xe", e);
		}
	}

	@Override
	public boolean delete(PHIGUIXE t) {
		String sql = "DELETE FROM PHIGUIXE WHERE Idcanho = ? AND Hanthu = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, t.getIdcanho());
			stmt.setString(2, t.getHanthu());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Lỗi khi xóa phí gửi xe", e);
		}
	}

	@Override
	public ArrayList<PHIGUIXE> selectAll() {
		ArrayList<PHIGUIXE> list = new ArrayList<>();
		String sql = "SELECT * FROM PHIGUIXE";
		try (Connection conn = DatabaseUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				PHIGUIXE phiguixe = new PHIGUIXE(rs.getInt("Idcanho"), rs.getInt("Tienxemay"), rs.getInt("Tienxeoto"),
						rs.getString("Hanthu"), rs.getInt("Tongguixe"));
				list.add(phiguixe);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Lỗi khi lấy danh sách phí gửi xe", e);
		}
		return list;
	}

	@Override
	public PHIGUIXE selectById(PHIGUIXE t) {
		String sql = "SELECT * FROM PHIGUIXE WHERE Idcanho = ? AND Hanthu = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, t.getIdcanho());
			stmt.setString(2, t.getHanthu());
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new PHIGUIXE(rs.getInt("Idcanho"), rs.getInt("Tienxemay"), rs.getInt("Tienxeoto"),
							rs.getString("Hanthu"), rs.getInt("Tongguixe"));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Lỗi khi lấy thông tin phí gửi xe", e);
		}
		return null;
	}

	@Override
	public ArrayList<PHIGUIXE> selectByCondition(String Condition) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Map<String, Object>> searchPHIGUIXE(String tenChuHo, String sonha, Integer month, Integer year) {
		ArrayList<Map<String, Object>> results = new ArrayList<>();
		StringBuilder query = new StringBuilder(
				"SELECT c.Idcanho, h.Hotenchuho AS TenChuHo, c.Sonha, h.Soxemay, h.Sooto, p.Tienxemay, p.Tienxeoto, p.Tongguixe, p.Hanthu "
						+ "FROM CANHO c " + "JOIN HOGIADINH h ON c.Idcanho = h.Idcanho "
						+ "LEFT JOIN PHIGUIXE p ON c.Idcanho = p.Idcanho " + "WHERE 1=1");

		ArrayList<Object> params = new ArrayList<>();

		// Thêm điều kiện tìm kiếm nếu có
		if (tenChuHo != null && !tenChuHo.trim().isEmpty()) {
			query.append(" AND h.Hotenchuho LIKE ?");
			params.add("%" + tenChuHo.trim() + "%");
		}
		if (sonha != null && !sonha.trim().isEmpty()) {
			query.append(" AND c.Sonha LIKE ?");
			params.add("%" + sonha.trim() + "%");
		}
		if (month != null && year != null) {
			query.append(" AND p.Hanthu = ?");
			params.add(String.format("%04d-%02d", year, month));
		} else if (year != null) {
			query.append(" AND YEAR(p.Hanthu) = ?");
			params.add(year);
		} else if (month != null) {
			query.append(" AND MONTH(p.Hanthu) = ?");
			params.add(month);
		}

		query.append(" ORDER BY p.Hanthu ASC");

		// Thực thi truy vấn
		try (Connection conn = DatabaseUtil.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query.toString())) {

			// Gán tham số vào truy vấn
			for (int i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Map<String, Object> row = new HashMap<>();
					row.put("idcanho", rs.getInt("Idcanho"));
					row.put("hotenchuho", rs.getString("TenChuHo"));
					row.put("sonha", rs.getString("Sonha"));
					row.put("soxemay", rs.getInt("Soxemay"));
					row.put("sooto", rs.getInt("Sooto"));
					row.put("tienxemay", rs.getInt("Tienxemay"));
					row.put("tienxeoto", rs.getInt("Tienxeoto"));
					row.put("tongguixe", rs.getInt("Tongguixe"));
					row.put("hanthu", rs.getString("Hanthu"));
					results.add(row);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Lỗi khi tìm kiếm phí gửi xe", e);
		}

		return results;
	}
	
    public ArrayList<PHIGUIXE> selectByMonthAndYear(Integer month, Integer year) {
        ArrayList<PHIGUIXE> list = new ArrayList<>();
        String sql = """
            SELECT Idcanho, Tienxemay, Tienxeoto, Tongguixe, Hanthu
            FROM PHIGUIXE
            WHERE MONTH(Hanthu) = ? AND YEAR(Hanthu) = ?
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, month);
            stmt.setInt(2, year);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PHIGUIXE phiguixe = new PHIGUIXE(
                        rs.getInt("Idcanho"),
                        rs.getInt("Tienxemay"),
                        rs.getInt("Tienxeoto"),
                        rs.getString("Hanthu"),
                        rs.getInt("Tongguixe")
                    );
                    list.add(phiguixe);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy danh sách phí gửi xe theo tháng và năm", e);
        }

        return list;
    }

	public int applyFeeForAll(int giasoxemay, int giaoto, String hanthu) {
		String sql = """
				INSERT INTO PHIGUIXE (Idcanho, Tienxemay, Tienxeoto, Tongguixe, Hanthu)
				SELECT h.Idcanho,
				       (h.Soxemay * ?),
				       (h.Sooto * ?),
				       (h.Soxemay * ? + h.Sooto * ?),
				       ?
				FROM HOGIADINH h
				ON DUPLICATE KEY UPDATE
				    Tienxemay = VALUES(Tienxemay),
				    Tienxeoto = VALUES(Tienxeoto),
				    Tongguixe = VALUES(Tongguixe),
				    Hanthu = VALUES(Hanthu)
				""";

		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			// Gán giá trị cho các tham số
			stmt.setInt(1, giasoxemay); // Giá xe máy
			stmt.setInt(2, giaoto); // Giá ô tô
			stmt.setInt(3, giasoxemay); // Giá xe máy (lặp lại để tính tổng)
			stmt.setInt(4, giaoto); // Giá ô tô (lặp lại để tính tổng)
			stmt.setString(5, hanthu); // Hạn thu

			// Thực thi truy vấn
			return stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Lỗi khi áp dụng phí gửi xe cho tất cả căn hộ", e);
		}
	}
}