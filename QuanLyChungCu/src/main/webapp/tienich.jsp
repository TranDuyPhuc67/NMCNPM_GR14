<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Quản lý phí chung cư</title>
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
        background-color: rgba(21, 35, 45, 0.9) !important;
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      }

      .navbar-nav .nav-link {
        color: #eeeeee;
      }

      .navbar-nav .nav-link.active {
        font-weight: bold;
        color: #ffffff;
      }

      .btn-search {
        background-color: #3790e9;
        color: white;
      }

      .table th,
      .table td {
        text-align: center;
        vertical-align: middle;
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
        background-color: #3790e9 !important;
        color: #eee;
      }
    </style>
  </head>
  <body>
    <!-- navbar -->
    <nav class="navbar navbar-expand-lg">
      <div class="container-fluid">
        <a class="navbar-brand" href="#" style="margin-left: 30px">
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

    <div class="hero">
      <div class="container mt-4">
        <h2>Quản lý phí chung cư</h2>
        <p class="pp">
          Thêm các khoản tiện ích bao gồm phí quản lý và phí dịch vụ
        </p>

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
          <div class="col-md-2 col-12 text-md-end mt-2 mt-md-0">
            <button
              class="btn btn-primary"
              data-bs-toggle="modal"
              data-bs-target="#updateModal"
            >
              Cập nhật phí
            </button>
          </div>
        </div>

        <!-- Hiển thị thông báo -->
        <c:if test="${not empty message}">
          <div class="alert alert-info">${message}</div>
        </c:if>

        <!-- Bảng danh sách tiện ích -->
        <table class="table table-bordered table-striped">
          <thead>
            <tr>
              <th>Tên Chủ Hộ</th>
              <th>Số Nhà</th>
              <th>Diện tích</th>
              <th>Phí dịch vụ</th>
              <th>Phí quản lý</th>
              <th>Tổng Phí</th>
              <th>Tháng thu</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="item" items="${tienIchList}">
              <tr>
                <td>
                  ${item.TenChuHo != null ? item.TenChuHo : 'Không xác định'}
                </td>
                <td>${item.Sonha != null ? item.Sonha : 'Không xác định'}</td>
                <td>${item.Dientich}</td>
                <td>${item.Dichvu}</td>
                <td>${item.Quanly}</td>
                <td>${item.Tongphichungcu}</td>
                <td>${item.Hanthu}</td>
              </tr>
            </c:forEach>
            <c:if test="${empty tienIchList}">
              <tr>
                <td colspan="8" class="text-center">
                  Không có tiện ích nào để hiển thị.
                </td>
              </tr>
            </c:if>
          </tbody>
        </table>
      </div>
    </div>

    <!-- Modal Cập nhật tiện ích -->
    <div
      class="modal fade"
      id="updateModal"
      tabindex="-1"
      aria-labelledby="updateModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="updateModalLabel">
              Cập nhật phí chung cư
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
              <input type="hidden" id="update-idcanho" name="idcanho" />
              <label for="update-sodien">Phí quản lý (trên 1m2):</label>
              <input
                type="number"
                id="update-sodien"
                name="sodien"
                class="form-control"
                required
                min="0"
              /><br />
              <label for="update-sonuoc">Phí dịch vụ (trên 1m2)</label>
              <input
                type="number"
                id="update-sonuoc"
                name="sonuoc"
                class="form-control"
                required
                min="0"
              /><br />
              <label for="update-hanthu">Thời gian thu</label>
              <input
                type="date"
                id="update-hanthu"
                name="hanthu"
                class="form-control"
                required
              /><br />

              <button type="submit" class="btn btn-primary">Cập nhật</button>
            </form>
          </div>
        </div>
      </div>
    </div>

    <!-- modal xác nhận tháng phí chung cư-->
    <div
      class="modal fade"
      id="chungCuModal"
      tabindex="-1"
      aria-labelledby="chungCuModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content modal-thangNam">
          <div class="modal-header">
            <h5 class="modal-title" id="chungCuModalLabel">
              Nhập tháng năm thu phí chung cư
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

              <a href="PhiChungCu.html">
                <button type="" class="btn btn-primary">Xác nhận</button>
              </a>
            </form>
          </div>
        </div>
      </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
      // Gắn dữ liệu vào modal cập nhật
      const updateModal = document.getElementById("updateModal");
      updateModal.addEventListener("show.bs.modal", (event) => {
        const button = event.relatedTarget;

        document.getElementById("update-idcanho").value =
          button.getAttribute("data-id") || "";
        document.getElementById("update-sodien").value =
          button.getAttribute("data-sodien") || 0;
        document.getElementById("update-sonuoc").value =
          button.getAttribute("data-sonuoc") || 0;
        document.getElementById("update-internet").value =
          button.getAttribute("data-internet") || 0;
        document.getElementById("update-hanthu").value =
          button.getAttribute("data-hanthu") || "";
      });
    </script>

    <!-- JavaScript để hiển thị modal khi tải trang -->
    <script>
      document.addEventListener("DOMContentLoaded", function () {
        var myModal = new bootstrap.Modal(
          document.getElementById("chungCuModal"),
          {}
        );
        myModal.show();
      });
    </script>
  </body>
</html>
