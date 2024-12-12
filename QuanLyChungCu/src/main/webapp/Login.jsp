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
      <%
        // Kiểm tra nếu có tham số 'error' trong URL
        String error = request.getParameter("error");
        if ("true".equals(error)) {
      %>
        <p style="color: red; font-size: 14px;">Tài khoản hoặc mật khẩu không chính xác!</p>
      <% } %>

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

        <a
          class="forget"
          href="./Forgot_Password.html"
          >Quên mật khẩu?
        </a>
        <input type="submit" value="Đăng nhập" />

      </form>
    </div>
  </body>
</html>
