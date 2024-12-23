package com.example.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.PHIGUIXE;
import com.example.service.PHIGUIXEService;

@WebServlet("/PHIGUIXE")
public class PHIGUIXEController extends HttpServlet {
    private static final long serialVersionUID = -4076849351186660951L;
    private PHIGUIXEService phiguixeService;

    @Override
    public void init() throws ServletException {
        phiguixeService = new PHIGUIXEService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cccdChuHo = request.getParameter("CCCDchuho");
        int soXeMay = Integer.parseInt(request.getParameter("Soxemay"));
        int soOTo = Integer.parseInt(request.getParameter("Sooto"));
        String thoihanStr = request.getParameter("Thoihan");

        Date thoihan = Date.valueOf(thoihanStr);

        PHIGUIXE phiguixe = new PHIGUIXE();
        phiguixe.setCCCDchuho(cccdChuHo);
        phiguixe.setSoxemay(soXeMay);
        phiguixe.setSooto(soOTo);
        phiguixe.setThoihan(thoihan);

        int result = phiguixeService.addPHIGUIXE(phiguixe);

        if (result > 0) {
            request.setAttribute("message", "Thêm tiện ích thành công!");
        } else {
            request.setAttribute("message", "Đã xảy ra lỗi khi thêm tiện ích.");
        }

        List<Map<String, Object>> phiguixeList = phiguixeService.selectAllWithDetails();
        request.setAttribute("phiguixeList", phiguixeList);

        request.getRequestDispatcher("phiguixe.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, Object>> phiguixeList = phiguixeService.selectAllWithDetails();
        request.setAttribute("phiguixeList", phiguixeList);
        request.getRequestDispatcher("phiguixe.jsp").forward(request, response);
    }
}
