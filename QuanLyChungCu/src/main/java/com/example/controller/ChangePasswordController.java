package com.example.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.service.QUANLYService;

@WebServlet("/ChangePass")
public class ChangePasswordController extends HttpServlet {
    // private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String newpassword = request.getParameter("newpassword");
        String re_newpassword = request.getParameter("re_newpassword");

        // Tạo đối tượng service và xác thực tài khoản
        QUANLYService quanlyService = new QUANLYService();
        boolean check = quanlyService.changeQUANLY(username,password,newpassword);
        // Kiểm tra xác thực
        if (check && newpassword.equals(re_newpassword)) {
            // Chuyển hướng đến trang chính (home)
            response.sendRedirect("Login.jsp");
        } else {
            // Nếu đăng nhập thất bại, thêm tham số error vào URL và chuyển hướng lại trang login
            request.setAttribute("notification", "Đã xảy ra lỗi: Đổi mật khẩu" );
            request.getRequestDispatcher("DoiMatKhau.jsp").forward(request, response);
        }
    }
}
