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

import com.example.model.*;
import com.example.service.*;

@WebServlet("/TamVang")
public class QuanLyTamVangController extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        CANHOService canhoService = new CANHOService();
        TAMVANGService tamvangService = new TAMVANGService();
        HOGIADINHService hogiadinhService = new HOGIADINHService();
        NHANKHAUService nhankhauService = new NHANKHAUService();
        String hoten = request.getParameter("name");
        String cccd = request.getParameter("idCard");
        String sonha = request.getParameter("apartment");
        int idcanho = canhoService.NametoId(sonha);
        // String cccdChuho = request.getParameter("cccdChuho");
        String cccdChuho = hogiadinhService.getChuHo(sonha);
        String lydo = request.getParameter("reason");
        String xuly = request.getParameter("xuly");

        if(xuly.equals("1")){
            String ngaybd_String = request.getParameter("startDate");
            Date ngaybd = Date.valueOf(ngaybd_String);
            String ngaykt_String = request.getParameter("endDate");
            Date ngaykt = Date.valueOf(ngaykt_String);
            NHANKHAU nhankhau_tmp = new NHANKHAU();
            nhankhau_tmp.setCCCD(cccd);
            NHANKHAU nhankhau = nhankhauService.getNHANKHAU(nhankhau_tmp);
            if(nhankhau == null){
                request.setAttribute("notification", "Không tìm thấy nhân khẩu, vui lòng thử lại");
                request.getRequestDispatcher("./WEB-INF/QuanLyTamVang.jsp").forward(request, response);
                return;
            }
            TAMVANG tamvang = new TAMVANG(cccd, idcanho, hoten, ngaybd, ngaykt, lydo);
            tamvangService.addTAMVANG(tamvang);
        }
        else if(xuly.equals("2")){
            String cccdXoa = request.getParameter("cccdXoa");
            TAMVANG tamvangXoa = new TAMVANG();
            tamvangXoa.setCCCD(cccdXoa);
            tamvangService.deleteTAMVANG(tamvangXoa);
        }
        else if(xuly.equals("3")){
            String ngaybd_String = request.getParameter("startDate");
            Date ngaybd = Date.valueOf(ngaybd_String);
            String ngaykt_String = request.getParameter("endDate");
            Date ngaykt = Date.valueOf(ngaykt_String);
            TAMVANG tamvang = new TAMVANG(cccd, idcanho, hoten, ngaybd, ngaykt, lydo);
            tamvangService.updateTAMVANG(tamvang);
            }
            response.sendRedirect("./QuanLyTamVang");
    }
    catch(Exception e){
        e.printStackTrace();
        request.setAttribute("notification", "Đã xảy ra lỗi: " + e.getMessage());
        request.getRequestDispatcher("./WEB-INF/QuanLyTamVang.jsp").forward(request, response);
    }      
    }  
}


