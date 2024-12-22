package com.example.display;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.example.service.CANHOService;
import com.example.model.CANHO;

@WebServlet("/QuanLyCanHo")
public class QuanLyCanHo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idcanho = request.getParameter("IDcanho");
        CANHOService canhoService = new CANHOService();
        List<CANHO> canhos;
        if(idcanho == null || idcanho.isEmpty()){
            canhos = canhoService.getAllCanHos();

        }
        else{
            int num_idcanho = Integer.parseInt(idcanho);
            CANHO canho = canhoService.getCanHoById(num_idcanho); 
            canhos = Collections.singletonList(canho);

        }
        if (canhos.size() == 0) {
                request.setAttribute("canhos", null); 
            } else {
                request.setAttribute("canhos", canhos); 
            }
        // request.setAttribute("canhos", null); 
        request.getRequestDispatcher("./WEB-INF/QuanLyCanHo.jsp").forward(request, response);
    }
}

