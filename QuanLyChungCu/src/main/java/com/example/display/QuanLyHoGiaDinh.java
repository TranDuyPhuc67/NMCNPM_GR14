package com.example.display;

import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.model.HOGIADINH;
import com.example.service.HOGIADINHService;
import com.example.service.CANHOService;

@WebServlet("/QuanLyHoGiaDinh")
public class QuanLyHoGiaDinh extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        HOGIADINHService hogiadinhService = new HOGIADINHService();
        List<HOGIADINH> hogiadinhs;
        if (search == null || search.isEmpty()) {
            hogiadinhs = hogiadinhService.getAllHOGIADINH();
            // System.out.println(1);
        } else {
            hogiadinhs = hogiadinhService.getHOGIADINHByCondition(search);
        }

        if (hogiadinhs == null || hogiadinhs.isEmpty() || hogiadinhs.get(0) == null) {
            request.setAttribute("errorMessage", "Không tìm thấy hộ gia đình nào phù hợp.");
            request.setAttribute("hogiadinhs", null);
        } else {
            request.setAttribute("hogiadinhs", hogiadinhs);
        }
        
        request.getRequestDispatcher("./WEB-INF/QuanLyHoGiaDinh.jsp").forward(request, response);
        
       
    }
}