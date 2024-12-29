package com.example.display;

import java.util.Collections;
import java.util.List;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.model.*;
import com.example.service.*;

@WebServlet("/QuanLyTamVang")
public class QuanLyTamVang extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        // CANHOService canhoService = new CANHOService();
        TAMVANGService tamvangService = new TAMVANGService();
        LocalDate todayLocalDate = LocalDate.now(); 
        Date today = Date.valueOf(todayLocalDate);
    
        // Truyền vào request
        request.setAttribute("today", today);
        List<TAMVANG> tamvangs;
        if(search == null || search.isEmpty()){
            tamvangs = tamvangService.getAllTAMVANG();
        } else {
            tamvangs = tamvangService.getByCondition(search);
        }
        
        if (tamvangs == null || tamvangs.isEmpty() || tamvangs.get(0) == null) {
            request.setAttribute("errorMessage", "Không tìm thấy căn hộ nào phù hợp.");
            request.setAttribute("tamvangs", null);
        } else {
            request.setAttribute("tamvangs", tamvangs);
        }
        
        request.getRequestDispatcher("./WEB-INF/QuanLyTamVang.jsp").forward(request, response);
    }
}