<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Quản lý cư dân</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
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
        vertical-align: middle;
        font-size: 13px;
      }
      .table tbody tr td {
        vertical-align: middle;
        font-size: 13px;
      }
      .small-text {
        font-size: 14px;
        font-style: italic;
      }
    </style>
  </head>
  <body>
    <!-- navbar -->
    <nav class="navbar navbar-expand-lg">
      <div class="container-fluid">
        <a class="navbar-brand" href="HomePage.html" style="margin-left: 30px">
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
              <a
                aria-current="page"
                class="nav-link active"
                href="HomePage.html"
              >
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
                <i class="fas fa-user"></i>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <!-- danh sách quản lý -->
    <div class="hero">
      <div class="container mt-4">
        <h2>Quản lý cư dân</h2>
        <p class="pp">
          Cung cấp công cụ để theo dõi và tổ chức thông tin chi tiết về các cư
          dân trong khu chung cư
        </p>
        <!-- Tìm kiếm cư dân -->
        <div class="row mb-3">
          <div class="col-md-6 col-12">
            <div class="input-group">
              <input
                type="text"
                class="form-control"
                placeholder="Tìm kiếm hộ gia đình theo mã phòng"
              />
              <button type="button" class="btn btn-search">Tìm kiếm</button>
            </div>
          </div>
        </div>
        <!-- Bảng danh sách cư dân -->
        <table class="table table-bordered table-striped">
          <thead>
            <tr>
              <th>Căn hộ</th>
              <th>Họ tên</th>
              <th>CMND/CCCD</th>
              <th>Ngày sinh</th>
              <th>Giới tính</th>
              <th>Số điện thoại</th>
              <th>Trạng thái</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>A101</td>
              <td>Nguyễn Văn Minh</td>
              <td>024205002986</td>
              <td>04/09/1980</td>
              <td>Nam</td>
              <td>0329656868</td>
              <td>Thường trú</td>
            </tr>
            <tr>
              <td>A101</td>
              <td>Nguyễn Thị Khai</td>
              <td>025205008866</td>
              <td>05/10/1982</td>
              <td>Nữ</td>
              <td>0329656868</td>
              <td>Thường trú</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
