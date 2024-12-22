<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Quản lý Tạm Trú</title>
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
        background-color: #007bff;
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
    </style>
  </head>
  <body>
    <nav class="navbar navbar-expand-lg fixed-top">
      <div class="container-fluid">
        <a class="navbar-brand" href="#" style="margin-left: 30px">
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
    <div class="hero">
      <div class="container mt-4">
        <h2>Quản lý tạm trú</h2>
        <p class="pp">
          Theo dõi và quản lý thông tin cư dân tạm trú trong khu chung cư
        </p>

        <!-- Tìm kiếm và thêm mới -->
        <div class="row mb-3">
          <div class="col-md-6 col-12">
            <div class="input-group">
              <input
                type="text"
                class="form-control"
                placeholder="Tìm kiếm cư dân"
              />
              <button class="btn btn-search">Tìm kiếm</button>
            </div>
          </div>
          <div class="col-md-6 col-12 text-md-end mt-2 mt-md-0">
            <button
              class="btn btn-primary"
              data-bs-toggle="modal"
              data-bs-target="#addModal"
            >
              Thêm mới tạm trú
            </button>
          </div>
        </div>
        <!-- Bảng danh sách tạm trú -->
        <table class="table table-bordered table-striped">
          <thead>
            <tr>
              <th>Họ tên</th>
              <th>CMND/CCCD</th>
              <th>Căn hộ</th>
              <th>Ngày bắt đầu</th>
              <th>Ngày kết thúc</th>
              <th>Lý do</th>
              <th>Trạng thái</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Nguyễn Văn A</td>
              <td>123456789</td>
              <td>A101</td>
              <td>01/12/2024</td>
              <td>15/12/2024</td>
              <td>Công tác</td>
              <td class="text-success">Đang tạm trú</td>
              <td>
                <button
                  class="btn btn-warning btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#editModal"
                >
                  Chỉnh sửa
                </button>
                <button class="btn btn-danger btn-sm">Xóa</button>
              </td>
            </tr>
            <tr>
              <td>Trần Thị B</td>
              <td>987654321</td>
              <td>B102</td>
              <td>05/12/2024</td>
              <td>20/12/2024</td>
              <td>Thăm người thân</td>
              <td class="text-danger">Hết hạn</td>
              <td>
                <button
                  class="btn btn-warning btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#editModal"
                >
                  Chỉnh sửa
                </button>
                <button class="btn btn-danger btn-sm">Xóa</button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>

      <!-- Modal Thêm mới tạm trú -->
      <div
        class="modal fade"
        id="addModal"
        tabindex="-1"
        aria-labelledby="addModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="addModalLabel">Thêm mới tạm trú</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <form>
                <div class="mb-3">
                  <label for="name" class="form-label">Họ và tên</label>
                  <input type="text" class="form-control" id="name" required />
                </div>
                <div class="mb-3">
                  <label for="idCard" class="form-label">CMND/CCCD</label>
                  <input
                    type="text"
                    class="form-control"
                    id="idCard"
                    required
                  />
                </div>

                <div class="mb-3">
                  <label for="dateBirth" class="form-label">Ngày sinh</label>
                  <input
                    type="date"
                    class="form-control"
                    id="dateBirth"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="sex" class="form-label">Giới tính</label>
                  <input type="text" class="form-control" id="sex" required />
                </div>
                <div class="mb-3">
                  <label for="nation" class="form-label">Dân tộc</label>
                  <input
                    type="text"
                    class="form-control"
                    id="nation"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="religion" class="form-label">Tôn giáo</label>
                  <input
                    type="text"
                    class="form-control"
                    id="religion"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="nationality" class="form-label">Quốc tịch</label>
                  <input
                    type="text"
                    class="form-control"
                    id="nationality"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="address" class="form-label"
                    >Địa chỉ thường trú</label
                  >
                  <!-- địa chỉ chung cư/ địa chỉ khác nếu là tạm trú -->
                  <input
                    type="text"
                    class="form-control"
                    id="address"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="phonenumber" class="form-label"
                    >Số điện thoại</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="phonenumber"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="ralation" class="form-label"
                    >Quan hệ với chủ hộ</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="relation"
                    required
                  />
                </div>

                <div class="mb-3">
                  <label for="startDate" class="form-label">Ngày bắt đầu</label>
                  <input
                    type="date"
                    class="form-control"
                    id="startDate"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="endDate" class="form-label">Ngày kết thúc</label>
                  <input
                    type="date"
                    class="form-control"
                    id="endDate"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="reason" class="form-label">Lý do tạm trú</label>
                  <input
                    type="text"
                    class="form-control"
                    id="reason"
                    required
                  />
                </div>
              </form>
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
          </div>
        </div>
      </div>

      <!-- Modal Chỉnh sửa tạm trú -->
      <div
        class="modal fade"
        id="editModal"
        tabindex="-1"
        aria-labelledby="editModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="editModalLabel">Chỉnh sửa tạm trú</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <form>
                <div class="mb-3">
                  <label for="name" class="form-label">Họ và tên</label>
                  <input type="text" class="form-control" id="name" required />
                </div>
                <div class="mb-3">
                  <label for="idCard" class="form-label">CMND/CCCD</label>
                  <input
                    type="text"
                    class="form-control"
                    id="idCard"
                    required
                  />
                </div>

                <div class="mb-3">
                  <label for="dateBirth" class="form-label">Ngày sinh</label>
                  <input
                    type="date"
                    class="form-control"
                    id="dateBirth"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="sex" class="form-label">Giới tính</label>
                  <input type="text" class="form-control" id="sex" required />
                </div>
                <div class="mb-3">
                  <label for="nation" class="form-label">Dân tộc</label>
                  <input
                    type="text"
                    class="form-control"
                    id="nation"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="religion" class="form-label">Tôn giáo</label>
                  <input
                    type="text"
                    class="form-control"
                    id="religion"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="nationality" class="form-label">Quốc tịch</label>
                  <input
                    type="text"
                    class="form-control"
                    id="nationality"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="address" class="form-label"
                    >Địa chỉ thường trú</label
                  >
                  <!-- địa chỉ chung cư/ địa chỉ khác nếu là tạm trú -->
                  <input
                    type="text"
                    class="form-control"
                    id="address"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="phonenumber" class="form-label"
                    >Số điện thoại</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="phonenumber"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="ralation" class="form-label"
                    >Quan hệ với chủ hộ</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="relation"
                    required
                  />
                </div>

                <div class="mb-3">
                  <label for="startDate" class="form-label">Ngày bắt đầu</label>
                  <input
                    type="date"
                    class="form-control"
                    id="startDate"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="endDate" class="form-label">Ngày kết thúc</label>
                  <input
                    type="date"
                    class="form-control"
                    id="endDate"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="reason" class="form-label">Lý do tạm trú</label>
                  <input
                    type="text"
                    class="form-control"
                    id="reason"
                    required
                  />
                </div>
              </form>
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Đóng
              </button>
              <button type="submit" class="btn btn-primary">Cập nhật</button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
