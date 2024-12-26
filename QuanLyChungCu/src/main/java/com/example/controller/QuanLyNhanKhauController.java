package com.example.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import java.sql.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import com.example.service.NHANKHAUService;
import com.example.model.NHANKHAU;
import com.example.model.HOGIADINH;
import com.example.service.HOGIADINHService;

@WebServlet("/NhanKhau")
public class QuanLyNhanKhauController extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        NHANKHAUService nhankhauService = new NHANKHAUService();
        String xuly = request.getParameter("xuly");
        String hoten = request.getParameter("name");
        String cccd = request.getParameter("idCard");
        String cccdChuho = request.getParameter("cccdChuho");
        String sdt = request.getParameter("phonenumber");
        String gioitinh = request.getParameter("sex");
        String dantoc = request.getParameter("nation");
        String tongiao = request.getParameter("religion");
        String quoctich = request.getParameter("nationality"); 
        String diachi = request.getParameter("address");
        String trangthai = request.getParameter("status");
        String quanhe = request.getParameter("relation");
         if(xuly.equals("1")){
            String ngaysinh_String = request.getParameter("dateBirth");
            Date ngaysinh = Date.valueOf(ngaysinh_String);
            NHANKHAU nhankhau = new NHANKHAU(cccd, cccdChuho, hoten, gioitinh, ngaysinh, dantoc, tongiao, quoctich, diachi, sdt, "", quanhe, trangthai);
            nhankhauService.addNHANKHAU(nhankhau);
        }
        else if(xuly.equals("2")){
            String cccdXoa = request.getParameter("cccdXoa");
            NHANKHAU nhankhauXoa = new NHANKHAU();
            if (!cccdChuho.equals(cccdXoa)) {
                nhankhauXoa.setCCCD(cccdXoa);
                nhankhauService.deleteNHANKHAU(nhankhauXoa);
            }
            else {
                HOGIADINHService hogiadinhService = new HOGIADINHService();
                HOGIADINH hogiadinh = new HOGIADINH();
                hogiadinh.setCCCDchuho(cccdXoa);
                hogiadinhService.deleteHOGIADINH(hogiadinh);
            }
        }
        else if(xuly.equals("3")){
            String ngaysinh_String = request.getParameter("dateBirth");
            Date ngaysinh = Date.valueOf(ngaysinh_String);
            NHANKHAU nhankhau = new NHANKHAU(cccd, cccdChuho, hoten, gioitinh, ngaysinh, dantoc, tongiao, quoctich, diachi, sdt, "", quanhe, trangthai);
            if (!cccdChuho.equals(cccd)) {
                nhankhauService.updateNHANKHAU(nhankhau);
            }
            else{
                HOGIADINHService hogiadinhService = new HOGIADINHService();
                HOGIADINH hogiadinh = new HOGIADINH(cccd,hoten, gioitinh, ngaysinh, dantoc, tongiao, quoctich, diachi,sdt,trangthai);
                hogiadinhService.updateCHUHO(hogiadinh);
            }
        }
        
        response.sendRedirect("./QuanLyNhanKhau?CCCDchuho=" + cccdChuho);
    }
}

