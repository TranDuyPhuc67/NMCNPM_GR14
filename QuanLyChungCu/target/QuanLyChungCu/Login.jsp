<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>BlueMoon | Đăng nhập</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="Login.css" />
  </head>
  <body>
    <div class="login-container">
      <h2>BlueMoon</h2>

      <!-- Hiển thị thông báo lỗi -->


      <form action="/QuanLyChungCu/Login" method="post">
        <p
          style="
            font-size: 14px;
            color: #575757;
            font-family: Arial, sans-serif;
            margin-bottom: 4px;
          "
        >
          Tài khoản
        </p>
        <input type="text" name="username" required />
        <p
          style="
            font-size: 14px;
            color: #575757;
            font-family: Arial, sans-serif;
            margin-bottom: 4px;
            margin-top: 5px;
          "
        >
          Mật khẩu
        </p>

        <input type="password" name="password" required />


        <input type="submit" value="Đăng nhập" />

      </form>
    </div>
  </body>
</html>
