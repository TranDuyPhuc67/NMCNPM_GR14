package com.example.display;

import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.model.NHANKHAU;
import com.example.service.NHANKHAUService;

@WebServlet("/QuanLyNhanKhau")
public class QuanLyNhanKhau extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cccdChuho = request.getParameter("CCCDchuho");
        // CANHOService canhoService = new CANHOService();
        NHANKHAUService nhankhauService = new NHANKHAUService();
        List<NHANKHAU> nhankhaus;
        if(cccdChuho == null || cccdChuho.isEmpty()){
            nhankhaus = null;
        } else {
            nhankhaus = nhankhauService.getAllNHANKHAU(cccdChuho);
        }
        
        if (nhankhaus == null || nhankhaus.isEmpty() || nhankhaus.get(0) == null) {
            request.setAttribute("errorMessage", "Không tìm thấy căn hộ nào phù hợp.");
            request.setAttribute("nhankhaus", null);
        } else {
            request.setAttribute("nhankhaus", nhankhaus);
        }
        
        request.getRequestDispatcher("./WEB-INF/QuanLyNhanKhau.jsp").forward(request, response);
    }
}