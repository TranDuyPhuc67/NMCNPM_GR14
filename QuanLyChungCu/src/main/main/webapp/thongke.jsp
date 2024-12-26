<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Thống kê tổng khoản thu</title>
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
        font-size: 35px;
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
      .small-text {
        font-size: 13px; /* Kích thước chữ nhỏ hơn */
      }
      .dropdown-toggle {
        border: none; /* Bỏ viền cho nút dropdown */
      }
    </style>
  </head>
  <body>
    <!-- navbar -->
    <nav class="navbar navbar-expand-lg">
      <div class="container-fluid">
        <a class="navbar-brand" href="HomePage.html" style="margin-left: 30px">
          <img
            src="../Image/Remove-bg.ai_1730519657240.png"
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
        <h2>Tổng khoản thu của các hộ gia đình</h2>
        <p class="pp">Thống kê tổng các loại phí sẽ thu của từng căn hộ</p>

        <!-- Form tìm kiếm -->
        <div class="row mb-3">
          <div class="col-md-10 col-12">
            <form action="TIENICH" method="GET" class="row mb-3">
              <input type="hidden" name="action" value="search" />
              <div class="col-md-3">
                <input
                  type="text"
                  name="sonha"
                  class="form-control"
                  placeholder="Tìm theo số nhà"
                  value="${param.sonha}"
                />
              </div>
              <div class="col-md-3">
                <input
                  type="text"
                  name="tenChuHo"
                  class="form-control"
                  placeholder="Tìm theo tên chủ hộ"
                  value="${param.tenChuHo}"
                />
              </div>

              <!-- Tìm theo tháng -->
              <div class="col-md-2">
                <select name="month" class="form-control">
                  <option value="">--Chọn tháng--</option>
                  <c:forEach var="i" begin="1" end="12">
                    <option value="${i}" ${param.month}>Tháng ${i}</option>
                  </c:forEach>
                </select>
              </div>

              <!-- Tìm theo năm -->
              <div class="col-md-2">
                <input
                  type="number"
                  name="year"
                  class="form-control"
                  placeholder="Năm"
                  value="${param.year}"
                  min="1900"
                  max="2100"
                />
              </div>
              <div class="col-md-2">
                <button type="submit" class="btn btn-primary">Tìm kiếm</button>
              </div>
            </form>
          </div>
        </div>

        <!-- Hiển thị thông báo -->
        <c:if test="${not empty message}">
          <div class="alert alert-info">${message}</div>
        </c:if>

        <!-- Bảng danh sách hộ gia đình -->
        <table class="table table-bordered table-striped">
          <thead>
            <tr>
              <th>Họ tên chủ hộ</th>
              <th>Số điện thoại</th>
              <th>Căn hộ</th>
              <th>Phí chung cư</th>
              <th>Phí gửi xe</th>
              <th>Phí tiện ích</th>
              <th>Tổng khoản thu</th>
              <th>Hạn thu</th>
              <th>Trạng thái</th>
              <th>Nộp phí</th>
              <th>Thống kê</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Nguyễn Văn A</td>
              <td>123456789</td>
              <td>A101</td>
              <td>100.000</td>
              <td>100.000</td>
              <td>100.000</td>
              <td><b>400.000</b></td>
              <td>31/12/2024</td>
              <td>
                <span class="text-danger small-text" data-value="submitted"
                  >Hết hạn</span
                >
              </td>
              <!-- tổng phí thu bằng tổng phí dịch vụ + phí quản lý + phí đóng góp  -->
              <td>
                <span class="text-success small-text" data-value="submitted"
                  >Đã nộp</span
                >
              </td>
              <td>
                <a href="ThongKeChiTiet.html">
                  <button class="btn btn-primary btn-sm">Chi tiết</button>
                </a>
              </td>
            </tr>
            <tr>
              <td>Trần Thị B</td>
              <td>987654321</td>
              <td>B102</td>
              <td>150.000</td>
              <td>150.000</td>
              <td>200.000</td>
              <td><b>900.000</b></td>
              <td>31/12/2024</td>
              <td>
                <span class="text-success small-text" data-value="submitted"
                  >Còn hạn</span
                >
              </td>
              <td>
                <span class="text-warning small-text" data-value="not_submitted"
                  >Chưa nộp</span
                >
              </td>
              <td>
                <a href="ThongKeChiTiet.html">
                  <button class="btn btn-primary btn-sm">Chi tiết</button>
                </a>
              </td>
            </tr>
          </tbody>
        </table>

        <!-- modal xác nhận tháng của thống kê-->
        <div
          class="modal fade"
          id="thongKeModal"
          tabindex="-1"
          aria-labelledby="thongKeModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog">
            <div class="modal-content modal-thangNam">
              <div class="modal-header">
                <h5 class="modal-title" id="thongKeModalLabel">
                  Nhập tháng năm bạn muốn thống kê
                </h5>
                <button
                  type="button"
                  class="btn-close"
                  data-bs-dismiss="modal"
                  aria-label="Close"
                ></button>
              </div>
              <div class="modal-body">
                <form action="TIENICH" method="POST">
                  <input type="hidden" name="action" value="update" />
                  <input type="hidden" id="month" name="idcanho" />
                  <label for="month">Tháng:</label>
                  <input
                    type="number"
                    id="update-sodien"
                    name="sodien"
                    class="form-control"
                    required
                    min="0"
                  /><br />
                  <label for="update-sonuoc">Năm:</label>
                  <input
                    type="number"
                    id="update-sonuoc"
                    name="sonuoc"
                    class="form-control"
                    required
                    min="0"
                  /><br />

                  <a href="CacLoaiPhi.html">
                    <button type="" class="btn btn-primary">Xác nhận</button>
                  </a>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script>
      document.querySelectorAll(".dropdown-item").forEach((item) => {
        item.addEventListener("click", function () {
          const statusValue = this.getAttribute("data-value");
          const button =
            this.closest(".dropdown").querySelector(".dropdown-toggle"); // Tìm nút dropdown trong cùng hàng
          button.textContent = this.textContent; // Cập nhật văn bản của nút
          button.classList.add("small-text"); // Thêm lớp nhỏ cho nút

          // Xóa các lớp màu nền cũ
          button.classList.remove("bg-success", "bg-warning");

          // Thêm lớp màu nền mới dựa trên trạng thái
          if (statusValue === "submitted") {
            button.classList.add("bg-success");
          } else if (statusValue === "not_submitted") {
            button.classList.add("bg-warning");
          }
        });
      });
    </script>
    <!-- JavaScript để hiển thị modal khi tải trang -->
    <script>
      document.addEventListener("DOMContentLoaded", () => {
        // Kiểm tra trạng thái trong sessionStorage
        const fromLinkA = sessionStorage.getItem("fromLinkA");

        if (fromLinkA === "true") {
          // Hiển thị modal
          const modal = new bootstrap.Modal(
            document.getElementById("thongKeModal")
          );
          modal.show();

          // Xóa trạng thái sau khi hiển thị để tránh hiển thị lại khi reload
          sessionStorage.removeItem("fromLinkA");
        }
      });
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>