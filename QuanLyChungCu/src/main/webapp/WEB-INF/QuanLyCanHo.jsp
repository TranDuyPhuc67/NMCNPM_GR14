<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <title>Quản lý căn hộ</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <style>
      .navbar {
        background-color: rgba(
          21,
          35,
          45,
          0.9
        ) !important; /* Màu nền khi cuộn */
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      }
      .navbar-nav .nav-link {
        color: #eeeeee;
      }
      .navbar-nav .nav-link.active {
        font-weight: bold;
        color: #ffffff;
      }
      .navbar-nav .btn {
        margin-left: 10px;
      }
      .header {
        position: relative;
        z-index: 2;
        text-align: center;
        margin-top: 50px;
      }
      .header h1 {
        color: #ffffff;
        font-weight: 800;
      }
      .header p {
        color: #ffffff;
        margin-bottom: 40px;
      }
      body {
        font-family: Arial, sans-serif;
      }
      .btn-search {
        background-color: #3790e9;
        color: white;
      }
      .btn-primary {
        background-color: #3790e9;
        color: white;
      }
      .table th,
      .table td {
        text-align: center;
        vertical-align: middle;
      }
      .table .text-success {
        color: green;
      }
      .table .text-danger {
        color: red;
      }
      .container h2 {
        text-align: center;
        font-weight: 700;
        color: rgba(10, 87, 138, 0.9);
        font-size: 40px;
        margin-top: 60px;
      }
      .container .pp {
        text-align: center;
        color: rgba(10, 87, 138, 0.9);
        margin-bottom: 50px;
      }
      .hero {
        margin-top: 100px;
      }
      .table thead tr th {
        background-color: #3790e9 !important; /* Thay đổi màu nền của thead */
        color: #eee;
      }
    </style>
  </head>
  <body>
    <!-- navbar -->
    <nav class="navbar navbar-expand-lg fixed-top">
      <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp" style="margin-left: 30px">
          <img
            src="./Image/Remove-bg.ai_1730519657240.png"
            alt="Logo"
            height="50"
          />
        </a>
        <button
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
          class="navbar-toggler"
          data-bs-target="#navbarNav"
          data-bs-toggle="collapse"
          type="button"
        >
          <span class="navbar-toggler-icon"> </span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <a aria-current="page" class="nav-link active" href="#">
                Trang chủ
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Tiện ích </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"> Dịch vụ khác</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"> Liên hệ hỗ trợ</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#"> </a>
            </li>
            <li class="nav-item">
              <a
                class="btn btn-outline-secondary"
                href="#"
                style="color: rgb(216, 216, 216); margin-right: 10px"
              >
                Tài khoản
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <!-- danh sách quản lý -->
    <div class="hero">
      <div class="container mt-4">
        <h2>Quản lý căn hộ</h2>
        <p class="pp">
          Quản lý căn hộ cung cấp công cụ để theo dõi và tổ chức thông tin chi
          tiết về từng căn hộ.
        </p>

        <!-- Tìm kiếm và thêm mới -->
        <div class="row mb-3">
          <div class="col-md-6 col-12">
            <form action="./QuanLyCanHo" method="GET" class="input-group">
              <input
                type="text"
                class="form-control"
                placeholder="Tìm kiếm căn hộ"
                name="sonha"
              />
              <button type="submit" class="btn btn-search">Tìm kiếm</button>
            </form>
          </div>
          
          <div class="col-md-6 col-12 text-md-end mt-2 mt-md-0">
            <button
              class="btn btn-primary"
              data-bs-toggle="modal"
              data-bs-target="#addModal"
            >
              Thêm căn hộ mới
            </button>
          </div>
        </div>
        <!-- Bảng danh sách hộ gia đình -->
        <table class="table table-bordered table-striped">
          <thead>
            <tr>
              <th>Mã Căn hộ</th>
              <th>Số nhà</th>
              <th>Loại căn hộ</th>
              <th>Diện tích</th>
              <th>Địa chỉ</th>
              <!-- <th>Đỉịa ch</th> -->
              <!-- <th>Căn cước công dân</th>
              <th>Số điện thoại</th> -->
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            <c:if test="${canhos != null}">
              <c:forEach var="canho" items="${canhos}">
                      <tr>
                          <td>${canho.idcanho}</td>
                          <td>${canho.sonha}</td>
                          <td>${canho.loaicanho}</td>
                          <td>${canho.dientich} m²</td>
                          <td>${canho.diachi}</td>
                          <td>
                              <button class="btn btn-warning btn-sm" data-bs-toggle="modal" data-bs-target="#editModal">Chỉnh sửa</button>
                              <button class="btn btn-danger btn-sm" onclick="Xoa('${canho.sonha}')">Xóa</button>

                          </td>
                      </tr>
              </c:forEach>
            </c:if>
          </tbody>
        </table>
      </div>

      <form id="deleteForm" action="./Canho" method="POST">
        <input type="hidden" id="sonha" name="sonha" value="">
        <input type="hidden" id="xuly" name="xuly" value="2">
      </form>

      <script>
        function Xoa(canhoId) {
            if (confirm("Bạn có chắc chắn muốn xóa phần tử này?")) {
                document.getElementById('sonha').value = canhoId;
                document.getElementById('deleteForm').submit();
            }
        }
    </script>
    
      <!-- Modal Thêm mới hộ gia đình -->
      <div
        class="modal fade"
        id="addModal"
        tabindex="-1"
        aria-labelledby="addModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div
              class="modal-header"
              style="background-color: #3790e9; color: #fff"
            >
              <h5 class="modal-title" id="addModalLabel">Thêm căn hộ mới</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <form method="post" action="./Canho">
                <div class="mb-3">
                  <input type="hidden" name="xuly" value="0">
                  <label for="apartment" class="form-label">Căn hộ</label>
                  <input
                    type="text"
                    name="sonha"
                    class="form-control"
                    id="apartment"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="apartmentType" class="form-label"
                    >Loại căn hộ</label
                  >
                  <input
                    type="text"
                    name="loaicanho"
                    class="form-control"
                    id="apartmentType"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="square" class="form-label">Diện tích</label>
                  <input
                    type="text"
                    name="dientich"
                    class="form-control"
                    id="square"
                    required
                  />
                </div>

                <div class="mb-3">
                  <label for="idCard" class="form-label"
                    >Địa chỉ</label
                  >
                  <input
                    type="text"
                    name="diachi"
                    class="form-control"
                    id="idCard"
                    required
                  />
                </div>
                <div class="modal-footer">
                  <button
                    type="button"
                    class="btn btn-secondary"
                    data-bs-dismiss="modal"
                  >
                    Đóng
                  </button>
                  <button type="submit" class="btn btn-primary">Lưu</button>
                </div>
              </form>
            </div>
            <!-- <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Đóng
              </button>
              <button type="submit" class="btn btn-primary">Lưu</button>
            </div> -->
          </div>
        </div>
      </div>

      <!-- Modal Chỉnh sửa thông tin hộ gia đình -->
      <div
        class="modal fade"
        id="editModal"
        tabindex="-1"
        aria-labelledby="editModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div
              class="modal-header"
              style="background-color: #3790e9; color: #fff"
            >
              <h5 class="modal-title" id="addModalLabel">Chỉnh sửa căn hộ</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <form method="post" action="./Canho">
                <input type="hidden" name="xuly" value="1">
                <div class="mb-3">
                  <label for="apartment" class="form-label">Căn hộ</label>
                  <input
                    type="text"
                    name="sonha"
                    class="form-control"
                    id="apartment"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="apartmentType" class="form-label"
                    >Loại căn hộ</label
                  >
                  <input
                    type="text"
                    name="loaicanho"
                    class="form-control"
                    id="apartmentType"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="square" class="form-label">Diện tích</label>
                  <input
                    type="text"
                    name="dientich"
                    class="form-control"
                    id="square"
                    required
                  />
                </div>
 
                <div class="mb-3">
                  <label for="idCard" class="form-label"
                    >Địa chỉ</label
                  >
                  <input
                    type="text"
                    name="diachi"
                    class="form-control"
                    id="idCard"
                    required
                  />
                </div>
                <div class="modal-footer">
                  <button
                    type="button"
                    class="btn btn-secondary"
                    data-bs-dismiss="modal"
                  >
                    Đóng
                  </button>
                  <button type="submit" class="btn btn-primary">Lưu</button>
                </div>
              </form>
            </div>
            
          </div>
        </div>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
