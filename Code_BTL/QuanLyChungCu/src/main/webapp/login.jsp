<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng Nhập</title>
</head>
<body>
    <h1>Đăng Nhập</h1>
    <form action="login" method="post">
        Tên đăng nhập: <input type="text" name="username" required/><br/>
        Mật khẩu: <input type="password" name="password" required/><br/>
        <input type="submit" value="Đăng Nhập"/>
    </form>
    <c:if test="${param.error != null}">
        <p style="color:red;">Thông tin đăng nhập không chính xác!</p>
    </c:if>
    <a href="register.jsp">Đăng Ký</a>
</body>
</html>
<!-- đây là front end  -->