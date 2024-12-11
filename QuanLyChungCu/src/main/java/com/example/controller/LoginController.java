package com.example.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.util.DatabaseUtil;

@WebServlet("/LoginServlet")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin đăng nhập từ form
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Băm mật khẩu bằng SHA-256
        String hashedPassword = hashPassword(password);

        // Kiểm tra thông tin đăng nhập
        boolean isAuthenticated = authenticate(username, hashedPassword);

        // Tạo phản hồi cho người dùng
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (isAuthenticated) {
            out.println("<html><body>");
            out.println("<h2>Đăng nhập thành công!</h2>");
            out.println("<p>Chào mừng bạn, " + username + "!</p>");
            out.println("<p><a href='home'>Đi đến trang chủ</a></p>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h2>Sai tài khoản hoặc mật khẩu</h2>");
            out.println("<p>Vui lòng kiểm tra lại thông tin đăng nhập.</p>");
            out.println("<p><a href='/QuanLyChungCu/'>Quay lại trang đăng nhập</a></p>");
            out.println("</body></html>");
        }
    }

    // Phương thức băm mật khẩu bằng SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            
            // Chuyển byte array thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Phương thức kiểm tra thông tin đăng nhập từ cơ sở dữ liệu
    private boolean authenticate(String username, String hashedPassword) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();
            String query = "SELECT * FROM TKQUANLY WHERE Username = ? AND PasswordHash = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, hashedPassword);
            resultSet = statement.executeQuery();

            // Kiểm tra xem có dòng dữ liệu trả về không (tức là thông tin đăng nhập hợp lệ)
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Đảm bảo đóng kết nối và các đối tượng
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
