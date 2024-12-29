package com.example.dao;

import java.sql.*;
import java.util.ArrayList;
import com.example.model.NHANKHAU;
import com.example.util.DatabaseUtil;

public class NHANKHAUDao implements DAOInterface<NHANKHAU> {

    @Override
    public int insert(NHANKHAU t) {
        String sql = "INSERT INTO NHANKHAU (CCCD, CCCDchuho, Hovaten, Gioitinh, Ngaysinh, Dantoc, Tongiao, Quoctich, Diachi, Sdt, Email, Quanhe, Trangthai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCccd());
            stmt.setString(2, t.getCCCDchuho());
            stmt.setString(3, t.getHovaten());
            stmt.setString(4, t.getGioitinh());
            stmt.setDate(5, (t.getNgaysinh()));
            stmt.setString(6, t.getDantoc());
            stmt.setString(7, t.getTongiao());
            stmt.setString(8, t.getQuoctich());
            stmt.setString(9, t.getDiachi());
            stmt.setString(10, t.getSdt());
            stmt.setString(11, t.getEmail());
            stmt.setString(12, t.getQuanhe());
            stmt.setString(13, t.getTrangthai());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thêm NHANKHAU", e);
        }
    }

    @Override
    public int update(NHANKHAU t) {
        String sql = "UPDATE NHANKHAU SET Hovaten = ?, Gioitinh = ?, Ngaysinh = ?, Dantoc = ?, Tongiao = ?, Quoctich = ?, Diachi = ?, Sdt = ?, Email = ?, Quanhe = ?, Trangthai = ? WHERE CCCD = ?";
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, t.getHovaten());
            stmt.setString(2, t.getGioitinh());
            stmt.setDate(3,t.getNgaysinh());
            stmt.setString(4, t.getDantoc());
            stmt.setString(5, t.getTongiao());
            stmt.setString(6, t.getQuoctich());
            stmt.setString(7, t.getDiachi());
            stmt.setString(8, t.getSdt());
            stmt.setString(9, t.getEmail());
            stmt.setString(10, t.getQuanhe());
            stmt.setString(11, t.getTrangthai());
            stmt.setString(12, t.getCccd());
            System.out.println("update thanh cong");
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật NHANKHAU", e);
        }
    }

    @Override
    public boolean delete(NHANKHAU t) {
        String sql = "DELETE FROM NHANKHAU WHERE CCCD = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCccd());
            System.out.println("delete thanh cong");
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("delete that bai");
            throw new RuntimeException("Lỗi khi xóa NHANKHAU", e);
        }
    }

    @Override
    public ArrayList<NHANKHAU> selectAll(){
        String sql = "select HGD.Sonha,NK.Hovaten,NK.CCCD,NK.Ngaysinh,NK.Gioitinh,NK.Sdt,NK.Trangthai FROM NHANKHAU NK JOIN HOGIADINH HGD ON NK.CCCDchuho = HGD.CCCDchuho UNION select Sonha,Hotenchuho, CCCDchuho,Ngaysinh,Gioitinh,Sdt,Trangthai FROM HOGIADINH ORDER BY Sonha;";
        ArrayList<NHANKHAU> list = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    NHANKHAU nhankhau = new NHANKHAU();
                    nhankhau.setSonha(rs.getString("Sonha"));
                    nhankhau.setCCCD(rs.getString("CCCD"));
                    nhankhau.setHovaten(rs.getString("Hovaten"));
                    nhankhau.setGioitinh(rs.getString("Gioitinh"));
                    nhankhau.setNgaysinh(rs.getDate("Ngaysinh"));
                    nhankhau.setSdt(rs.getString("Sdt"));
                    nhankhau.setTrangthai(rs.getString("Trangthai"));
                    list.add(nhankhau);
                }
            }
        catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn tất cả NHANKHAU", e);
        }
            
        return list;
    }
    public ArrayList<NHANKHAU> selectAll(String cccdChuHo) {
        String sql = "SELECT * FROM NHANKHAU WHERE CCCDchuho = ? UNION SELECT CCCDchuho,CCCDchuho,Hotenchuho,Gioitinh,Ngaysinh,Dantoc,Tongiao,Quoctich,Diachi,Sdt,NULL,'Chủ hộ',Trangthai FROM HOGIADINH WHERE CCCDchuho = ? order by Quanhe";
        ArrayList<NHANKHAU> list = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);) {
                stmt.setString(1, cccdChuHo);
                stmt.setString(2, cccdChuHo);
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    NHANKHAU nhankhau = new NHANKHAU();
                    nhankhau.setCCCD(rs.getString("CCCD"));
                    nhankhau.setCCCDchuho(rs.getString("CCCDchuho"));
                    nhankhau.setHovaten(rs.getString("Hovaten"));
                    nhankhau.setGioitinh(rs.getString("Gioitinh"));
                    nhankhau.setNgaysinh(rs.getDate("Ngaysinh"));
                    nhankhau.setDantoc(rs.getString("Dantoc"));
                    nhankhau.setTongiao(rs.getString("Tongiao"));
                    nhankhau.setQuoctich(rs.getString("Quoctich"));
                    nhankhau.setDiachi(rs.getString("Diachi"));
                    nhankhau.setSdt(rs.getString("Sdt"));
                    nhankhau.setEmail(rs.getString("Email"));
                    nhankhau.setQuanhe(rs.getString("Quanhe"));
                    nhankhau.setTrangthai(rs.getString("Trangthai"));
                    list.add(nhankhau);
                }
            }
            catch (SQLException e) {
                throw new RuntimeException("Lỗi khi chọn tất cả NHANKHAU", e);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn tất cả NHANKHAU", e);
        }

        return list;
    }
    public ArrayList<NHANKHAU> selectAllMCH(String macanho) {
        String sql = "SELECT HGD.Sonha, NK.Hovaten, NK.CCCD, NK.Ngaysinh, NK.Gioitinh, NK.Sdt, NK.Trangthai FROM NHANKHAU NK JOIN HOGIADINH HGD ON NK.CCCDchuho = HGD.CCCDchuho WHERE HGD.Sonha = ? UNION SELECT Sonha, Hotenchuho, CCCDchuho, Ngaysinh, Gioitinh, Sdt, Trangthai FROM HOGIADINH WHERE Sonha = ? ORDER BY Sonha;";
        ArrayList<NHANKHAU> list = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);) {
                stmt.setString(1, macanho);
                stmt.setString(2, macanho);
            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()) {
                    NHANKHAU nhankhau = new NHANKHAU();
                    nhankhau.setSonha(rs.getString("Sonha"));
                    nhankhau.setCCCD(rs.getString("CCCD"));
                    nhankhau.setHovaten(rs.getString("Hovaten"));
                    nhankhau.setGioitinh(rs.getString("Gioitinh"));
                    nhankhau.setNgaysinh(rs.getDate("Ngaysinh"));
                    nhankhau.setSdt(rs.getString("Sdt"));
                    nhankhau.setTrangthai(rs.getString("Trangthai"));
                    list.add(nhankhau);
                }
            }
            catch (SQLException e) {
                throw new RuntimeException("Lỗi khi chọn tất cả NHANKHAU", e);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn tất cả NHANKHAU", e);
        }

        return list;
    }

    @Override
    public NHANKHAU selectById(NHANKHAU t) {
        String sql = "SELECT * FROM NHANKHAU WHERE CCCD = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, t.getCccd());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    NHANKHAU nhankhau = new NHANKHAU();
                    nhankhau.setCCCD(rs.getString("CCCD"));
                    nhankhau.setCCCDchuho(rs.getString("CCCDchuho"));
                    nhankhau.setHovaten(rs.getString("Hovaten"));
                    nhankhau.setGioitinh(rs.getString("Gioitinh"));
                    nhankhau.setNgaysinh(rs.getDate("Ngaysinh"));
                    nhankhau.setDantoc(rs.getString("Dantoc"));
                    nhankhau.setTongiao(rs.getString("Tongiao"));
                    nhankhau.setQuoctich(rs.getString("Quoctich"));
                    nhankhau.setDiachi(rs.getString("Diachi"));
                    nhankhau.setSdt(rs.getString("Sdt"));
                    nhankhau.setEmail(rs.getString("Email"));
                    nhankhau.setQuanhe(rs.getString("Quanhe"));
                    nhankhau.setTrangthai(rs.getString("Trangthai"));
                    return nhankhau;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi chọn NHANKHAU theo CCCD", e);
        }
        return null;
    }
    public int updateTT(String cccd,String trangthai) {
        String sql = "UPDATE NHANKHAU SET Trangthai = ? WHERE CCCD = ?; UPDATE HOGIADINH SET Trangthai = ? WHERE CCCDchuho = ?";
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, trangthai);
                stmt.setString(2, cccd);
                stmt.setString(3, trangthai);
                stmt.setString(4, cccd);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi cập nhật NHANKHAU", e);
        }
    }

	@Override
	public ArrayList<NHANKHAU> selectByCondition(String Condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
