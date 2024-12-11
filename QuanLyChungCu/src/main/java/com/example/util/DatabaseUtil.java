package com.example.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    // Cấu hình kết nối đến cơ sở dữ liệu
    private static final String URL = "jdbc:mysql://localhost:3306/QuanLyChungCu"; // Đổi tên cơ sở dữ liệu của bạn
    private static final String USER = "root";  // Đổi tên người dùng của bạn
    private static final String PASSWORD = "19102004";  // Đổi mật khẩu của bạn

    // Kết nối tới cơ sở dữ liệu
    public static Connection getConnection() throws SQLException {
        try {
            // Nạp Driver JDBC MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Thiết lập và trả về kết nối
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Không tìm thấy JDBC Driver.");
        }
    }
}
