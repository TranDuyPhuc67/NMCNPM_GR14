<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    // Kiểm tra xem session có chứa thuộc tính "username" hay không
    if (session.getAttribute("username") == null) {
        // Chuyển hướng về trang index.jsp
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BlueMoon | Mật khẩu mới</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
     <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
     <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
     <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
 
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
    />
    <style>
      body {
        font-family: "montserrat", sans-serif;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        background-color: #f7f7f7;
        margin: 0;

        background-image: linear-gradient(
            180deg,
            rgba(0, 0, 0, 0.5),
            rgba(0, 0, 0, 0)
          ),
          url("./Image/banner.jpg");
        background-size: 100% 100%;
        background-repeat: no-repeat;
        box-sizing: border-box;
        background-origin: content-box;
        background-position: top center;
        background-attachment: fixed;
      }
      .overlay {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5); /* Màu đen với độ trong suốt */
        z-index: 0;
      }
      .container {
        background-color: #efefef;
        width: 360px;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        z-index: 0;
      }
      input[type="text"] {
        width: 100%;
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #d0cece;
        background-color: #f0f0f0;
        border-radius: 7px;
        font-size: 16px;
        box-sizing: border-box;
        outline: none;
      }
      input[type="password"] {
        width: 100%;
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #d0cece;
        background-color: #f0f0f0;
        border-radius: 7px;
        font-size: 16px;
        box-sizing: border-box;
        outline: none;
      }
      input[type="text"]:focus {
        border: 2px solid #4c8eaf;
      }
      input[type="text"]::placeholder {
        font-size: 14px;
        color: #999;
      }
      input[type="password"]:focus {
        border: 2px solid #4c8eaf;
      }
      input[type="password"]::placeholder {
        font-size: 14px;
        color: #999;
      }
      input[type="submit"] {
        width: 100%;
        padding: 9px;
        background-color: #4c8eaf;
        color: #f3f3f3;
        border: none;
        border-radius: 7px;
        font-size: 16px;
        cursor: pointer;
        margin-top: 20px;
      }
      input[type="submit"]:hover {
        background-color: #599cbe;
      }
      a {
        display: block;
        margin-top: 13px;
        font-size: 15px;
        color: #4c8eaf;
        text-decoration: none;
        text-align: center;
      }
      a:hover {
        text-decoration: underline;
      }
    </style>
  </head>
  <body>
    <c:if test="${not empty notification}">
      <!-- Modal -->
    <div class="modal fade" id="notificationModal" tabindex="-1" role="dialog" aria-labelledby="notificationModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="notificationModalLabel">Thông báo hệ thống</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    ${notification}
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
      $(document).ready(function(){
          $('#notificationModal').modal('show');
      });
  </script>
    </c:if>
    <div class="overlay"></div>

    <div class="container">
      <div style="text-align: center">
        <img src="Image/password.png" width="50" height="60" />
      </div>
      <form action="ChangePass" method="post">
        <h1
          style="
            font-size: 27px;
            color: #4c8eaf;
            margin-bottom: 10px;
            text-align: center;
          "
        >
          Đổi mật khẩu
        </h1>
        <p
          style="
            font-size: 15px;
            color: #626262;
            margin-bottom: 25px;
            text-align: center;
          "
        >
          Mật khẩu mạnh bao gồm số, kí tự và dấu chấm câu
        </p>
        <input type="text"       name = "username" placeholder="Tài khoản" required />
        <input type="Password" name = "password" placeholder="Mật khẩu cũ" required />
        <input type="Password" name = "newpassword" placeholder="Nhập mật khẩu mới" required />
        <input type="password" name = "re_newpassword" placeholder="Xác nhận mật khẩu mới" required />
        <input type="submit" value="Gửi yêu cầu" />
      </form>
    </div>
  </body>
</html>