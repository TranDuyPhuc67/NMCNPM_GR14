<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #ffffff;
            color: #000000;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .error-container {
            text-align: center;
            padding: 20px;
            border: 1px solid #cccccc;
            border-radius: 5px;
            background-color: #ffffff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }
        .error-title {
            font-size: 24px;
            font-weight: bold;
        }
        .error-message {
            margin-top: 10px;
            font-size: 16px;
        }
        .error-action {
            margin-top: 20px;
        }
        .error-action a {
            text-decoration: none;
            color: #000000;
            font-weight: bold;
            padding: 10px 20px;
            border: 1px solid #000000;
            border-radius: 5px;
            background-color: #f9f9f9;
            transition: background-color 0.3s;
        }
        .error-action a:hover {
            background-color: #e9e9e9;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <div class="error-title">Oops! Đã xảy ra lỗi.</div>
        <div class="error-message">Chúng tôi rất tiếc về sự cố này. Vui lòng thử lại sau hoặc liên hệ với quản trị viên hệ thống.</div>
        <div class="error-action">
            <a href="index.jsp">Quay lại trang chủ</a>
        </div>
    </div>
</body>
</html>
