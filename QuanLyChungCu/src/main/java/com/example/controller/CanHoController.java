package com.example.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import com.example.service.CANHOService;
import com.example.model.CANHO;

@WebServlet("/Canho")
public class CanHoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        CANHOService canhoService = new CANHOService();
        String xuly = request.getParameter("xuly");
        String sonha = request.getParameter("sonha");
        int check = -1; 
        if (xuly.equals("2")){
            CANHO canho = new CANHO();
            canho.setSonha(sonha);
            CANHO canho1 = canhoService.getCanHoByName(sonha);
            canhoService.deleteCanHo(canho1);
        }
        else{
            String loaicanho = request.getParameter("loaicanho");
            loaicanho = URLDecoder.decode(loaicanho, StandardCharsets.UTF_8.name());
            String dientich = request.getParameter("dientich");
            double num_dientich  = Double.parseDouble(dientich);
            String diachi = request.getParameter("diachi");
            diachi = URLDecoder.decode(diachi, StandardCharsets.UTF_8.name());
    
            CANHO canho = new CANHO(sonha, loaicanho, num_dientich, diachi);
            // List<CANHO> canhos = canhoService.getAllCanHos();
            if (xuly.equals("0")){
                check = canhoService.addCanHo(canho);         
            }
            else if (xuly.equals("1")){
                CANHO canho1 = canhoService.getCanHoByName(sonha);
                canho.setIdcanho(canho1.getIdcanho());
                check = canhoService.updateCanHo(canho);
            } 
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("success", check);
        response.sendRedirect("./QuanLyCanHo");
    }
}

