package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import com.example.model.TAMTRU;
import com.example.util.DatabaseUtil;

public class TAMTRUDao implements DAOInterface<TAMTRU>{

    @Override
    public int insert(TAMTRU t){
        String query = "INSERT INTO TAMTRUVANG (CCCD , HOVATEN, Idcanho,Ngaybatdau, Ngayketthuc, Lydo, Trangthai ) VALUES (?, ?, ?, ?, ?, ?, ?)";   
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
	public int update(TAMTRU t){
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
            throw new RuntimeException("Lỗi khi cập nhật TAMTRU", e);
        }
    }

    @Override
	public boolean delete(TAMTRU t){
        String sql = "DELETE FROM BANGTRUVANG WHERE CCCD = ?";
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getCccd());
            System.out.println("delete thanh cong");
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("delete that bai");
            throw new RuntimeException("Lỗi khi xóa TAMTRU", e);
        }
    }

    @Override
	public ArrayList<TAMTRU> selectAll(){
        String sql = "SELECT BANGTRUVANG.*,HOGIADINH.Sonha FROM BANGTRUVANG JOIN HOGIADINH ON HOGIADINH.Idcanho = BANGTRUVANG.Idcanho WHERE BANGTRUVANG.Trangthai = 'TV';";
        ArrayList<TAMTRU> list = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    TAMTRU tamtru = new TAMTRU();
                    tamtru.setSonha(rs.getString("Sonha"));
                    tamtru.setCCCD(rs.getString("CCCD"));
                    tamtru.setHovaten(rs.getString("HOVATEN"));
                    tamtru.setIdCanHo(rs.getInt("Idcanho"));
                    tamtru.setNgaybatdau(rs.getDate("Ngaybatdau"));
                    tamtru.setNgayketthuc(rs.getDate("Ngayketthuc"));
                    tamtru.setLydo(rs.getString("Lydo"));
                    tamtru.setTrangthai(rs.getString("Trangthai"));
                    list.add(tamtru);
                }
            }
        catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn tất cả tamtru", e);
        }
            
        return list;
    }

    @Override
	public TAMTRU selectById(TAMTRU t){
        return null;
    }

    @Override
	public ArrayList<TAMTRU> selectByCondition(String condition){
        String sql = "SELECT BANGTRUVANG.*,HOGIADINH.Sonha FROM BANGTRUVANG JOIN HOGIADINH ON HOGIADINH.Idcanho = BANGTRUVANG.Idcanho WHERE BANGTRUVANG.Trangthai = 'TV' AND (BANGTRUVANG.CCCD = ? OR BANGTRUVANG.HOVATEN = ? OR HOGIDINH.Sonha = ? OR BANGTRUVANG.Ngaybatdau > ? OR BANGTRUVANG.Ngayketthuc < ?);";
		ArrayList<TAMTRU> list = new ArrayList<>();
		if (condition == null || condition.isEmpty()) {
			throw new IllegalArgumentException("Condition cannot be null or empty");
		}
		try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, condition);
			stmt.setString(2, condition);
			stmt.setString(3, condition);
			stmt.setString(4, condition);
			try{
				stmt.setDate(5, Date.valueOf(condition));
				stmt.setDate(6, Date.valueOf(condition));
			}
			catch (NumberFormatException e){
				stmt.setDate(5, null);
				stmt.setDate(6, null);
			}
			try (ResultSet rs = stmt.executeQuery()) {

				while (rs.next()) {
                    TAMTRU tamtru = new TAMTRU();
                    tamtru.setSonha(rs.getString("Sonha"));
                    tamtru.setCCCD(rs.getString("CCCD"));
                    tamtru.setHovaten(rs.getString("HOVATEN"));
                    tamtru.setIdCanHo(rs.getInt("Idcanho"));
                    tamtru.setNgaybatdau(rs.getDate("Ngaybatdau"));
                    tamtru.setNgayketthuc(rs.getDate("Ngayketthuc"));
                    tamtru.setLydo(rs.getString("Lydo"));
                    tamtru.setTrangthai(rs.getString("Trangthai"));
                    list.add(tamtru);
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
