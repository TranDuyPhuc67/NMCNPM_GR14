<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Quản lý hộ gia đình</title>
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
      .table {
        border-radius: 5px;
        overflow: hidden;
      }
      .table th,
      .table td {
        text-align: center;
        vertical-align: middle;
      }
      .table td a {
        color: #fff;
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
      .modal-dialog {
        display: flex;
        align-items: center;
        justify-content: center;
        min-height: calc(100vh - 1rem);
      }
    </style>
  </head>
  <body>
    <!-- navbar -->
    <nav class="navbar navbar-expand-lg">
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
              <a
                aria-current="page"
                class="nav-link active"
                href="index.jsp"
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
        <h2>Quản lý hộ gia đình</h2>
        <p class="pp">
          Cung cấp công cụ để theo dõi và tổ chức thông tin chi tiết về từng hộ
          gia đình.
        </p>
        <!-- Tìm kiếm và thêm mới -->
        <div class="row mb-3">
          <div class="col-md-6 col-12">
              <form action="./QuanLyHoGiaDinh" method="get" class="input-group">
              <input
                type="text"
                class="form-control"
                name="search"
                placeholder="Tìm kiếm hộ gia đình theo mã phòng, tên chủ hộ, số điện thoại, ..."
              />
              <button type="submit" class="btn btn-search">Tìm kiếm</button>
            </form>
          </div>
          <div class="col-md-6 col-12 text-md-end mt-2 mt-md-0">
            <button
              class="btn btn-primary"
              data-bs-toggle="modal"
              data-bs-target="#addInfoModal1"
            >
              Thêm mới hộ gia đình
            </button>
          </div>
        </div>
        <!-- Bảng danh sách hộ gia đình -->
        <table class="table table-bordered table-striped">
          <thead>
            <tr>
              <th>Họ tên chủ hộ</th>
              <th>CMND/CCCD</th>
              <th>Số điện thoại</th>
              <th>Căn hộ</th>
              <th>Tầng</th>
              <th>Số nhân khẩu</th>
              <th>Hành động</th>
            </tr>
          </thead>
          <tbody>
            <c:if test="${hogiadinhs != null}">
              <c:forEach var="hogiadinh" items="${hogiadinhs}">
                      <tr>
                        <td>${hogiadinh.hotenchuho}</td>
                        <td>${hogiadinh.cccdchuho}</td>
                        <td>${hogiadinh.sdt}</td>
                        <td>${hogiadinh.sonha}</td>
                        <td>${hogiadinh.tang}</td>
                        <td>${hogiadinh.sothanhvien}</td>     
                          <td>
                            <a href="QuanLyNhanKhau?CCCDchuho=${hogiadinh.cccdchuho}">
                              <button class="btn btn-success btn-sm">Quản lý</button>
                            </a>
                            <button
                              class="btn btn-warning btn-sm"
                              data-bs-toggle="modal"
                              data-bs-target="#editModal"
                            >
                              Chỉnh sửa
                            </button>
                            <button
                              class="btn btn-danger btn-sm"
                              data-bs-toggle="modal"
                              data-bs-target="#deleteModal"
                              onclick="deleteFamily('${hogiadinh.cccdchuho}')"
                            >
                              Xóa
                            </button>
                          </td>
                      </tr>
              </c:forEach>
            </c:if>
          </tbody>
        </table>
      </div>
      <!-- Modal Thêm mới hộ gia đình -->
      <div
        class="modal fade"
        id="addInfoModal1"
        tabindex="-1"
        aria-labelledby="addInfoModal1Label"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div
              class="modal-header"
              style="background-color: #3790e9; color: #fff"
            >
              <h5 class="modal-title" id="addInfoModal1Label">
                Thêm mới hộ gia đình
              </h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <form id="form1">
                <div class="mb-3">
                  <label for="name" class="form-label">Họ và tên chủ hộ</label>
                  <input type="text" class="form-control" id="name" name="name" required />
                </div>
                <div class="mb-3">
                  <label for="idCard" class="form-label">CMND/CCCD</label>
                  <input
                    type="text"
                    class="form-control"
                    id="idCard"
                    name="idCard"
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
                    name="phonenumber"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="apartment" class="form-label">Căn hộ</label>
                  <input
                    type="text"
                    class="form-control"
                    id="apartment"
                    name="apartment"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="floor" class="form-label">Tầng</label>
                  <input type="text" class="form-control" id="floor" name="floor" required />
                </div>
                <div class="mb-3">
                  <label for="persons" class="form-label">Số nhân khẩu</label>
                  <input
                    type="text"
                    class="form-control"
                    id="persons"
                    name="persons"
                    required
                  />
                </div>

                <div class="mb-3">
                  <label for="motorNumber" class="form-label">Số xe máy</label>
                  <input
                    type="text"
                    class="form-control"
                    id="motorNumber"
                    name="motorNumber"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="carNumber" class="form-label">Số ô tô</label>
                  <input
                    type="text"
                    class="form-control"
                    id="carNumber"
                    name="carNumber"
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
              <button class="btn btn-primary" id="nextModal">Tiếp theo</button>
            </div>
          </div>
        </div>
      </div>

      <!-- modal thêm thông tin chủ hộ -->
      <div
        class="modal fade"
        id="addInfoModal2"
        tabindex="-1"
        aria-labelledby="addInfoModal2Label"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div
              class="modal-header"
              style="background-color: #3790e9; color: #fff"
            >
              <h5 class="modal-title" id="addInfoModal2Label">
                Thêm thông tin chủ hộ
              </h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <form id="form2">
                <div class="mb-3">
                  <label for="dateBirth" class="form-label">Ngày sinh</label>
                  <input
                    type="date"
                    class="form-control"
                    id="dateBirth"
                    name="dateBirth"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="sex" class="form-label">Giới tính</label>
                  <input type="text" class="form-control" id="sex" name="sex" required />
                </div>
                <div class="mb-3">
                  <label for="nation" class="form-label">Dân tộc</label>
                  <input
                    type="text"
                    class="form-control"
                    id="nation"
                    name="nation"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="religion" class="form-label">Tôn giáo</label>
                  <input
                    type="text"
                    class="form-control"
                    id="religion"
                    name="religion"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="nationality" class="form-label">Quốc tịch</label>
                  <input
                    type="text"
                    class="form-control"
                    id="nationality"
                    name="nationality"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="address" class="form-label"
                    >Địa chỉ thường trú</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="address"
                    name="address"
                    required
                  />
                </div>

                <div class="mb-3">
                  <label for="status" class="form-label"
                    >Trạng thái
                    <span class="small-text"
                      >(Thường trú hoặc Tạm trú)</span
                    ></label
                  >
                  <input
                    type="text"
                    class="form-control"
                    id="status"
                    name= "status"
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
              <button type="submit" class="btn btn-primary" id="submitForm">
                Lưu
              </button>
            </div>
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
              <h5 class="modal-title" id="addModalLabel">
                Chỉnh sửa thông tin hộ gia đình
              </h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <form method="post" action="./HoGiaDinh">
                <div class="mb-3">
                  <input type="hidden" id="xuly" name="xuly" value="1">
                  <label for="name" class="form-label">Họ tên chủ hộ</label>
                  <input type="text" class="form-control" id="name" name="name" required />
                </div>
                <div class="mb-3">
                  <label for="idCard" class="form-label">CMND/CCCD</label>
                  <input
                    type="text"
                    class="form-control"
                    id="idCard"
                    name="idCard"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="phoneNum" class="form-label">Số điện thoại</label>
                  <input
                    type="text"
                    class="form-control"
                    id="phoneNum"
                    name="phoneNum"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="apartment" class="form-label">Căn hộ</label>
                  <input
                    type="text"
                    class="form-control"
                    id="apartment"
                    name="apartment"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="floor" class="form-label">Tầng</label>
                  <input type="text" class="form-control" id="floor" name="floor" required />
                </div>
                <div class="mb-3">
                  <label for="persons" class="form-label">Số nhân khẩu</label>
                  <input
                    type="text"
                    class="form-control"
                    id="persons"
                    name="persons"
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

      <!-- Modal Xóa hộ gia đình -->
      <div
        class="modal fade"
        id="deleteModal"
        tabindex="-1"
        aria-labelledby="deleteModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div
              class="modal-header"
              style="background-color: #3790e9; color: #fff"
            >
              <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              Bạn có chắc chắn muốn xóa hộ gia đình này không?
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Hủy
              </button>
              <button
                type="button"
                class="btn btn-danger"
                onclick="confirmDelete()"
              >
                Xóa
              </button>
            </div>
          </div>
        </div>
      </div>
      <!-- Modal Thông báo xóa thành công -->
      <div
        class="modal fade"
        id="successModal"
        tabindex="-1"
        aria-labelledby="successModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div
              class="modal-header"
              style="background-color: #3790e9; color: #fff"
            >
              <h5 class="modal-title" id="successModalLabel">Thông báo</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">Hộ gia đình đã được xóa thành công.</div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Đóng
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <form id="deleteForm" action="./HoGiaDinh" method="POST">
      <input type="hidden" id="cccd" name="cccd" value="">
      <input type="hidden" id="xuly" name="xuly" value="2">
    </form>
    <script>
      let cccdToDelete = '';
    
      function deleteFamily(cccd) {
        cccdToDelete = cccd;
      }
    
      function confirmDelete() {
        // Thực hiện hành động xóa ở đây
        document.getElementById('cccd').value = cccdToDelete;
        document.getElementById('deleteForm').submit();
    
        var deleteModal = bootstrap.Modal.getInstance(
          document.getElementById("deleteModal")
        );
        deleteModal.hide();
        
        // Hiển thị modal thông báo xóa thành công
        var successModal = new bootstrap.Modal(
          document.getElementById("successModal")
        );
        successModal.show();
      }
    </script>
    <!-- thêm hộ gia đình -->
    <script>
      document.addEventListener("DOMContentLoaded", () => {
  const modal1 = new bootstrap.Modal(
      document.getElementById("addInfoModal1")
    );
    const modal2 = new bootstrap.Modal(
      document.getElementById("addInfoModal2")
    );

    // Nút "Tiếp tục" để mở modal 2
    document.getElementById("nextModal").addEventListener("click", () => {
      const form1 = document.getElementById("form1");
      if (form1.checkValidity()) {
        modal1.hide();
        modal2.show();
      } else {
        form1.reportValidity();
      }
    });

  // Nút "Gửi" để lấy dữ liệu từ cả hai form
  document.getElementById("submitForm").addEventListener("click", () => {
    const form1 = document.getElementById("form1");
    const form2 = document.getElementById("form2");

    // Kiểm tra tính hợp lệ của cả hai form
    if (form1.checkValidity() && form2.checkValidity()) {
            const form1Data = new FormData(form1);
            const form2Data = new FormData(form2);

            // Gộp dữ liệu từ cả hai form
            const combinedData = {};
            form1Data.forEach((value, key) => {
              combinedData[key] = value;
            });
            form2Data.forEach((value, key) => {
              combinedData[key] = value;
            });

            console.log("Dữ liệu cuối cùng:", combinedData);

            // Gửi dữ liệu qua API
            fetch("./HoGiaDinh", {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify(combinedData),
            })
              .then((response) => {
                if (response.ok) {
                  alert("Dữ liệu đã được gửi thành công!");
                  modal2.hide();
                } else {
                  alert("Có lỗi xảy ra khi gửi dữ liệu!");
                }
              })
              .catch((error) => {
                console.error("Lỗi khi gửi dữ liệu:", error);
                alert("Không thể gửi dữ liệu. Vui lòng thử lại sau!");
              });
          } else {
            // Báo lỗi nếu một trong hai form không hợp lệ
            if (!form1.checkValidity()) form1.reportValidity();
            if (!form2.checkValidity()) form2.reportValidity();
          }
        });
      });

    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>