package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import com.example.model.TAMVANG;
import com.example.util.DatabaseUtil;

public class TAMVANGDao implements DAOInterface<TAMVANG>{

    @Override
    public int insert(TAMVANG t){
        String query = "INSERT INTO BANGTRUVANG (CCCD , HOVATEN, Idcanho,Ngaybatdau, Ngayketthuc, Lydo, Trangthai ) VALUES (?, ?, ?, ?, ?, ?, ?)";   
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, t.getCccd());
            stmt.setString(2, t.getHovaten());
            stmt.setInt(3, t.getIdCanHo());
            stmt.setDate(4, t.getNgaybatdau());
            stmt.setDate(5, t.getNgayketthuc());
            stmt.setString(6, t.getLydo());
            stmt.setString(7, t.getTrangthai());
            return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
     return 0;
    }

    @Override
	public int update(TAMVANG t){
        String sql = "UPDATE BANGTRUVANG SET HOVATEN = ?, Idcanho = ?, Ngaybatdau = ?, Ngayketthuc = ?, Lydo  = ? WHERE CCCD = ?";
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getHovaten());
            stmt.setInt(2, t.getIdCanHo());
            stmt.setDate(3,t.getNgaybatdau());
            stmt.setDate(4, t.getNgayketthuc());
            stmt.setString(5, t.getLydo());
            stmt.setString(6, t.getCccd());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật TAMVANG", e);
        }
    }

    @Override
	public boolean delete(TAMVANG t){
        String sql = "DELETE FROM BANGTRUVANG WHERE CCCD = ?";
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getCccd());
            System.out.println("delete thanh cong");
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("delete that bai");
            throw new RuntimeException("Lỗi khi xóa TAMVANG", e);
        }
    }

    @Override
	public ArrayList<TAMVANG> selectAll(){
        String sql = "SELECT BANGTRUVANG.*,HOGIADINH.Sonha FROM BANGTRUVANG JOIN HOGIADINH ON HOGIADINH.Idcanho = BANGTRUVANG.Idcanho WHERE BANGTRUVANG.Trangthai = 'TV';";
        ArrayList<TAMVANG> list = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    TAMVANG tamvang = new TAMVANG();
                    tamvang.setSonha(rs.getString("Sonha"));
                    tamvang.setCCCD(rs.getString("CCCD"));
                    tamvang.setHovaten(rs.getString("HOVATEN"));
                    tamvang.setIdCanHo(rs.getInt("Idcanho"));
                    tamvang.setNgaybatdau(rs.getDate("Ngaybatdau"));
                    tamvang.setNgayketthuc(rs.getDate("Ngayketthuc"));
                    tamvang.setLydo(rs.getString("Lydo"));
                    tamvang.setTrangthai(rs.getString("Trangthai"));
                    list.add(tamvang);
                }
            }
        catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn tất cả tamvang", e);
        }
            
        return list;
    }

    @Override
	public TAMVANG selectById(TAMVANG t){
        return null;
    }

    @Override
	public ArrayList<TAMVANG> selectByCondition(String condition){
        String sql = "SELECT BANGTRUVANG.*,HOGIADINH.Sonha FROM BANGTRUVANG JOIN HOGIADINH ON HOGIADINH.Idcanho = BANGTRUVANG.Idcanho WHERE BANGTRUVANG.Trangthai = 'TV' AND (BANGTRUVANG.CCCD = ? OR BANGTRUVANG.HOVATEN = ? OR HOGIADINH.Sonha = ? OR BANGTRUVANG.Ngaybatdau > ? OR BANGTRUVANG.Ngayketthuc < ?);";
		ArrayList<TAMVANG> list = new ArrayList<>();
		if (condition == null || condition.isEmpty()) {
			throw new IllegalArgumentException("Condition cannot be null or empty");
		}
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, condition);
			stmt.setString(2, condition);
			stmt.setString(3, condition);
            String dateRegex = "\\d{4}-\\d{2}-\\d{2}";
            boolean check = condition != null && condition.matches(dateRegex);
			if (check){
				stmt.setDate(4, Date.valueOf(condition));
				stmt.setDate(5, Date.valueOf(condition));
			}
			else{
				stmt.setDate(4, null);
				stmt.setDate(5, null);
			}
			try (ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
                    TAMVANG tamvang = new TAMVANG();
                    tamvang.setSonha(rs.getString("Sonha"));
                    tamvang.setCCCD(rs.getString("CCCD"));
                    tamvang.setHovaten(rs.getString("HOVATEN"));
                    tamvang.setIdCanHo(rs.getInt("Idcanho"));
                    tamvang.setNgaybatdau(rs.getDate("Ngaybatdau"));
                    tamvang.setNgayketthuc(rs.getDate("Ngayketthuc"));
                    tamvang.setLydo(rs.getString("Lydo"));
                    tamvang.setTrangthai(rs.getString("Trangthai"));
                    list.add(tamvang);
				}
			} 
		}
		catch (SQLException e) {
				System.err.println("Error while selecting TAMVANG by condition: " + e.getMessage());
				throw new RuntimeException("Error while selecting TAMVANG by condition", e);
		}
		
		return list;
    }
    
}
