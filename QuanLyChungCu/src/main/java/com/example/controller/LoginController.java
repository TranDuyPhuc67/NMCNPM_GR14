package com.example.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.service.QUANLYService;

@WebServlet("/Login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Tạo đối tượng service và xác thực tài khoản
        QUANLYService quanlyService = new QUANLYService();
        boolean isAuthenticated = quanlyService.authenticateQUANLY(username, password);

        // Kiểm tra xác thực
        if (isAuthenticated) {
            // Lưu thông tin vào session nếu đăng nhập thành công
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            
            // Chuyển hướng đến trang chính (home)
            response.sendRedirect("index.jsp");
        } else {
            // Nếu đăng nhập thất bại, thêm tham số error vào URL và chuyển hướng lại trang login
            response.sendRedirect("Login.jsp?error=true");
        }
    }
}
