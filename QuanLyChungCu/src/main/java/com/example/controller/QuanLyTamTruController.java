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
import com.example.model.TAMTRU;
import com.example.service.TAMTRUService;
import com.example.service.HOGIADINHService;
import com.example.service.CANHOService;
@WebServlet("/TamTru")
public class QuanLyTamTruController extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        TAMTRUService tamtruService = new TAMTRUService();
        HOGIADINHService hogiadinhService = new HOGIADINHService();
        CANHOService canhoService = new CANHOService();
        String xuly = request.getParameter("xuly");
        String hoten = request.getParameter("name");
        String cccd = request.getParameter("idCard");
        String sonha = request.getParameter("apartment");
        int idcanho = canhoService.NametoId(sonha);
        // String cccdChuho = request.getParameter("cccdChuho");
        String cccdChuho = hogiadinhService.getChuHo(sonha);
        String sdt = request.getParameter("phonenumber");
        String gioitinh = request.getParameter("sex");
        String dantoc = request.getParameter("nation");
        String tongiao = request.getParameter("religion");
        String quoctich = request.getParameter("nationality"); 
        String diachi = request.getParameter("address");
        String lydo = request.getParameter("reason");
        String quanhe = request.getParameter("relation");

        if(xuly.equals("1")){
            String ngaysinh_String = request.getParameter("dateBirth");
            Date ngaysinh = Date.valueOf(ngaysinh_String);
            String ngaybd_String = request.getParameter("startDate");
            Date ngaybd = Date.valueOf(ngaybd_String);
            String ngaykt_String = request.getParameter("endDate");
            Date ngaykt = Date.valueOf(ngaykt_String);
            NHANKHAU nhankhau = new NHANKHAU(cccd, cccdChuho, hoten, gioitinh, ngaysinh, dantoc, tongiao, quoctich, diachi, sdt, "", quanhe, "Tạm trú");
            TAMTRU tamtru = new TAMTRU(cccd, idcanho, hoten, ngaybd, ngaykt, lydo, nhankhau);
            tamtruService.addTAMTRU(tamtru);
        }
        else if(xuly.equals("2")){
            String cccdXoa = request.getParameter("cccdXoa");
            TAMTRU tamtruXoa = new TAMTRU();
            tamtruXoa.setCCCD(cccdXoa);
            tamtruService.deleteTAMTRU(tamtruXoa);
        }
        else if(xuly.equals("3")){
            String ngaysinh_String = request.getParameter("dateBirth");
            Date ngaysinh = Date.valueOf(ngaysinh_String);
            String ngaybd_String = request.getParameter("startDate");
            Date ngaybd = Date.valueOf(ngaybd_String);
            String ngaykt_String = request.getParameter("endDate");
            Date ngaykt = Date.valueOf(ngaykt_String);
            NHANKHAU nhankhau = new NHANKHAU(cccd, cccdChuho, hoten, gioitinh, ngaysinh, dantoc, tongiao, quoctich, diachi, sdt, "", quanhe, "Tạm trú");
            TAMTRU tamtru = new TAMTRU(cccd, idcanho, hoten, ngaybd, ngaykt, lydo, nhankhau);
            tamtruService.updateTAMTRU(tamtru);
            }
            response.sendRedirect("./QuanLyTamTru");
        
    }  
}


