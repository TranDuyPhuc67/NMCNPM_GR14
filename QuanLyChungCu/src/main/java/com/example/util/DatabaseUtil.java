package com.example.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DatabaseUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/QuanLyChungCu";
    private static final String USER = "root";
    private static final String PASSWORD = "19102004";

    // Cấu hình DataSource
    private static DataSource dataSource;

    static {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(URL);
        mysqlDataSource.setUser(USER);
        mysqlDataSource.setPassword(PASSWORD);
        dataSource = mysqlDataSource;
    }

    // Kết nối đến cơ sở dữ liệu
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
