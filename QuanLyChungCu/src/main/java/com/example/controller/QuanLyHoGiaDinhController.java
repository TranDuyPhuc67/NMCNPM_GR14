package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.service.CANHOService;
import com.example.service.HOGIADINHService;
import com.example.model.HOGIADINH;

@WebServlet("/HoGiaDinh")
public class QuanLyHoGiaDinhController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=UTF-8");

            // Đọc dữ liệu JSON từ yêu cầu
            String contentType = request.getContentType();
            CANHOService canhoService = new CANHOService();
            if (contentType != null && contentType.contains("application/json")) {
                StringBuilder jsonBuffer = new StringBuilder();
                String line;
                try (BufferedReader reader = request.getReader()) {
                    while ((line = reader.readLine()) != null) {
                        jsonBuffer.append(line);
                    }
                }


                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonBuffer.toString());

                String hotenchuho = jsonNode.get("name").asText();
                String cccdChuho = jsonNode.get("idCard").asText();
                String sdt = jsonNode.get("phonenumber").asText();
                String sonha = jsonNode.get("apartment").asText();
                int sothanhvien = jsonNode.get("persons").asInt();
                int soxemay = jsonNode.get("motorNumber").asInt();
                int sooto = jsonNode.get("carNumber").asInt();
                String ngaysinh_String = jsonNode.get("dateBirth").asText();
                Date ngaysinh = Date.valueOf(ngaysinh_String);
                String gioitinh = jsonNode.get("sex").asText();
                // if (!(gioitinh.equals('nam') && gioitinh.equals('nu') && gioitinh.equals('nam')))
                String dantoc = jsonNode.get("nation").asText();
                String tongiao = jsonNode.get("religion").asText();
                String quoctich = jsonNode.get("nationality").asText();
                String diachi = jsonNode.get("address").asText();
                String trangthai = "Thường trú";
                int tang = jsonNode.get("floor").asInt();

                int idCanHo = canhoService.NametoId(sonha);
                HOGIADINH hogiaDinh = new HOGIADINH(cccdChuho, idCanHo, sothanhvien, hotenchuho, gioitinh, ngaysinh, dantoc, tongiao, quoctich, diachi, sdt, trangthai, soxemay, sooto, tang, sonha);
                HOGIADINHService hogiaDinhService = new HOGIADINHService();
                int check = hogiaDinhService.addHOGIADINH(hogiaDinh);
            } else {
                String xuly = request.getParameter("xuly");
                if (xuly.equals("1")) {
                    String hotenchuho = request.getParameter("name");
                    String cccdChuho = request.getParameter("idCard");
                    String sdt = request.getParameter("phoneNum");
                    int tang = Integer.parseInt(request.getParameter("floor"));
                    String sonha = request.getParameter("apartment");
                    int idcanho = canhoService.NametoId(sonha);
                    String sothanhvien = request.getParameter("persons");
                    int soxemay = Integer.parseInt(request.getParameter("motorNum"));
                    int sooto = Integer.parseInt(request.getParameter("carNum"));
                    HOGIADINH hogiadinh = new HOGIADINH(cccdChuho, idcanho, hotenchuho, Integer.parseInt(sothanhvien), sdt, tang, sonha,soxemay,sooto);
                    System.out.println(hogiadinh.getCccdchuho());
                    HOGIADINHService hogiadinhService = new HOGIADINHService();
                    hogiadinhService.updateHOGIADINH(hogiadinh);
                } else if (xuly.equals("2")) {
                    String cccdChuho = request.getParameter("cccd");
                    HOGIADINHService hogiadinhService = new HOGIADINHService();
                    HOGIADINH hogiadinh = new HOGIADINH();
                    hogiadinh.setCCCDchuho(cccdChuho);
                    hogiadinhService.deleteHOGIADINH(hogiadinh);
                }
            } 
            response.sendRedirect("./QuanLyHoGiaDinh");  
        }        
        catch (NumberFormatException e){
            request.setAttribute("notification", "Đã xảy ra lỗi: Vui lòng nhập chính xác hơn" );
            request.getRequestDispatcher("./WEB-INF/QuanLyHoGiaDinh.jsp").forward(request, response);
            // return;
        }
        catch (Exception e) {
            // e.printStackTrace();
            request.setAttribute("notification", "Đã xảy ra lỗi: " + e.getMessage());
            request.getRequestDispatcher("./WEB-INF/QuanLyHoGiaDinh.jsp").forward(request, response);
        } 
    }
}