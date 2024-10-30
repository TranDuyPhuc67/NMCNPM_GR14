package com.code;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Kiểm tra thông tin đăng nhập (nên sử dụng một cơ sở dữ liệu trong thực tế)
        if ("admin".equals(username) && "admin".equals(password)) {
            response.getWriter().println("<h1>Login Successful!</h1>");
        } else {
            response.getWriter().println("<h1>Login Failed!</h1>");
        }
    }
}