package com.example.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.model.PHICHUNGCU;
import com.example.util.DatabaseUtil;

public class PHICHUNGCUDao implements DAOInterface<PHICHUNGCU> {

	@Override
	public int insert(PHICHUNGCU t) {
		String sql = "INSERT INTO PHICHUNGCU (Idcanho, Phidichvu, Phiquanly, Tongphichungcu, Hanthu, Thoigianthu) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, t.getIdcanho());
			stmt.setInt(2, t.getPhidichvu());
			stmt.setInt(3, t.getPhiquanly());
			stmt.setInt(4, t.getTongphichungcu());
			stmt.setString(5, t.getHanthu());
			stmt.setDate(6, t.getThoigianthu());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Lỗi khi thêm phí chung cư", e);
		}
	}

	@Override
	public int update(PHICHUNGCU t) {
		String sql = "UPDATE PHICHUNGCU SET Phidichvu = ?, Phiquanly = ?, Tongphichungcu = ? WHERE Idcanho = ? AND Hanthu = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, t.getPhidichvu());
			stmt.setInt(2, t.getPhiquanly());
			stmt.setInt(3, t.getTongphichungcu());
			stmt.setInt(4, t.getIdcanho());
			stmt.setString(5, t.getHanthu());
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Lỗi khi cập nhật phí chung cư", e);
		}
	}

	@Override
	public boolean delete(PHICHUNGCU t) {
		String sql = "DELETE FROM PHICHUNGCU WHERE Idcanho = ? AND Hanthu = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, t.getIdcanho());
			stmt.setString(2, t.getHanthu()); // Sửa lại thành String
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Lỗi khi xóa phí chung cư", e);
		}
	}

	@Override
	public ArrayList<PHICHUNGCU> selectAll() {
		ArrayList<PHICHUNGCU> list = new ArrayList<>();
		String sql = "SELECT Idcanho, Phidichvu, Phiquanly, Tongphichungcu, Hanthu FROM PHICHUNGCU";
		try (Connection conn = DatabaseUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				list.add(new PHICHUNGCU(rs.getInt("Idcanho"), rs.getInt("Phidichvu"), rs.getInt("Phiquanly"),
						rs.getInt("Tongphichungcu"), rs.getString("Hanthu") // Sửa lại thành String
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Lỗi khi lấy danh sách phí chung cư", e);
		}
		return list;
	}

	@Override
	public PHICHUNGCU selectById(PHICHUNGCU t) {
		String sql = "SELECT Idcanho, Phidichvu, Phiquanly, Tongphichungcu, Hanthu FROM PHICHUNGCU WHERE Idcanho = ? AND Hanthu = ?";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, t.getIdcanho());
			stmt.setString(2, t.getHanthu()); // Sửa lại thành String
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new PHICHUNGCU(rs.getInt("Idcanho"), rs.getInt("Phidichvu"), rs.getInt("Phiquanly"),
							rs.getInt("Tongphichungcu"), rs.getString("Hanthu") // Sửa lại thành String
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Lỗi khi lấy thông tin phí chung cư", e);
		}
		return null;
	}

	@Override
	public ArrayList<PHICHUNGCU> selectByCondition(String condition) {
		ArrayList<PHICHUNGCU> list = new ArrayList<>();
		String sql = "SELECT Idcanho, Phidichvu, Phiquanly, Tongphichungcu, Hanthu FROM PHICHUNGCU WHERE " + condition;
		try (Connection conn = DatabaseUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				list.add(new PHICHUNGCU(rs.getInt("Idcanho"), rs.getInt("Phidichvu"), rs.getInt("Phiquanly"),
						rs.getInt("Tongphichungcu"), rs.getString("Hanthu") // Sửa lại thành String
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Lỗi khi lấy danh sách phí chung cư theo điều kiện", e);
		}
		return list;
	}

	public int applyFeeForAll(int phidichvum2, int phiquanlym2, String hanthu, Date Thoigianthu) {
		String sql = """
				INSERT INTO PHICHUNGCU (Idcanho, Phidichvu, Phiquanly, Tongphichungcu, Hanthu, Thoigianthu)
				SELECT CANHO.Idcanho, CANHO.Dientich * ?, CANHO.Dientich * ?, CANHO.Dientich * (? + ?), ?, ?
				FROM CANHO
				ON DUPLICATE KEY UPDATE
				Phidichvu = VALUES(Phidichvu),
				Phiquanly = VALUES(Phiquanly),
				Tongphichungcu = VALUES(Tongphichungcu),
				Hanthu = VALUES(Hanthu),
				Thoigianthu = VALUES(Thoigianthu)
				""";
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, phidichvum2);
			stmt.setInt(2, phiquanlym2);
			stmt.setInt(3, phidichvum2);
			stmt.setInt(4, phiquanlym2);
			stmt.setString(5, hanthu);
			stmt.setDate(6, Thoigianthu);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Lỗi khi áp dụng phí chung cư cho tất cả căn hộ", e);
		}
	}

	public ArrayList<Map<String, Object>> searchPHICHUNGCU(String tenChuHo, String sonha, Integer month, Integer year) {
	    ArrayList<Map<String, Object>> results = new ArrayList<>();
	    StringBuilder query = new StringBuilder(
	        "SELECT c.Idcanho, h.Hotenchuho AS TenChuHo, c.Sonha, p.Phidichvu, p.Phiquanly, p.Tongphichungcu, p.Hanthu, c.Dientich "
	        + "FROM CANHO c "
	        + "JOIN HOGIADINH h ON c.Idcanho = h.Idcanho "
	        + "LEFT JOIN PHICHUNGCU p ON c.Idcanho = p.Idcanho WHERE 1=1");

	    List<Object> params = new ArrayList<>();

	    // Điều kiện tìm theo tên chủ hộ
	    if (tenChuHo != null && !tenChuHo.trim().isEmpty()) {
	        query.append(" AND h.Hotenchuho LIKE ?");
	        params.add("%" + tenChuHo.trim() + "%");
	    }

	    // Điều kiện tìm theo số nhà
	    if (sonha != null && !sonha.trim().isEmpty()) {
	        query.append(" AND c.Sonha LIKE ?");
	        params.add("%" + sonha.trim() + "%");
	    }

	    // Điều kiện tìm theo tháng và năm
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
	    try (Connection conn = DatabaseUtil.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query.toString())) {

	        for (int i = 0; i < params.size(); i++) {
	            stmt.setObject(i + 1, params.get(i));
	        }

	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Map<String, Object> row = new HashMap<>();
	                row.put("idcanho", rs.getInt("Idcanho"));
	                row.put("tenchuho", rs.getString("TenChuHo"));
	                row.put("sonha", rs.getString("Sonha"));
	                row.put("phidichvu", rs.getInt("Phidichvu"));
	                row.put("phiquanly", rs.getInt("Phiquanly"));
	                row.put("tongphichungcu", rs.getInt("Tongphichungcu"));
	                row.put("hanthu", rs.getString("Hanthu"));
	                row.put("dientich", rs.getDouble("Dientich"));
	                results.add(row);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Lỗi khi tìm kiếm phí chung cư", e);
	    }

	    return results;
	}

    public ArrayList<PHICHUNGCU> getPHICHUNGCUByMonthAndYear(Integer month, Integer year) {
        ArrayList<PHICHUNGCU> phichungList = new ArrayList<>();

        String query = "SELECT * FROM PHICHUNGCU WHERE MONTH(Thoigianthu) = ? AND YEAR(Thoigianthu) = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, month);
            stmt.setInt(2, year);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PHICHUNGCU phichungcu = new PHICHUNGCU();
                    phichungcu.setIdcanho(rs.getInt("Idcanho"));
                    phichungcu.setPhidichvu(rs.getInt("Phidichvu"));
                    phichungcu.setPhiquanly(rs.getInt("Phiquanly"));
                    phichungcu.setTongphichungcu(rs.getInt("Tongphichungcu"));
                    phichungcu.setHanthu(rs.getString("Hanthu"));
                    phichungcu.setThoigianthu(rs.getDate("Thoigianthu"));
                    phichungList.add(phichungcu);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return phichungList;
    }
}
