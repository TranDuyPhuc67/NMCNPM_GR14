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
import com.example.model.NHANKHAU;
import com.example.service.NHANKHAUService;
import com.example.model.TAMTRU;
import com.example.service.TAMTRUService;

@WebServlet("/QuanLyTamTru")
public class QuanLyTamTru extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        // CANHOService canhoService = new CANHOService();
        TAMTRUService tamtruService = new TAMTRUService();
        LocalDate todayLocalDate = LocalDate.now(); 
        Date today = Date.valueOf(todayLocalDate);
    
        // Truyền vào request
        request.setAttribute("today", today);
        List<TAMTRU> tamtrus;
        if(search == null || search.isEmpty()){
            tamtrus = tamtruService.getAllTAMTRU();
        } else {
            tamtrus = tamtruService.getByCondition(search);
        }
        
        if (tamtrus == null || tamtrus.isEmpty() || tamtrus.get(0) == null) {
            request.setAttribute("errorMessage", "Không tìm thấy căn hộ nào phù hợp.");
            request.setAttribute("tamtrus", null);
        } else {
            request.setAttribute("tamtrus", tamtrus);
        }
        
        request.getRequestDispatcher("./WEB-INF/QuanLyTamTru.jsp").forward(request, response);
    }
}