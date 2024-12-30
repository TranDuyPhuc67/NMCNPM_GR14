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
    <title>Quản lý Tạm Vắng</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
 
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
      .modal-dialog {
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: calc(100vh - 1rem);
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

      <!-- Hiển thị Modal khi có thông báo -->
      <script type="text/javascript">
          $(document).ready(function(){
              $('#notificationModal').modal('show');
          });
      </script>
  </c:if>
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
    <div class="hero">
      <div class="container mt-4">
        <h2>Quản lý tạm vắng</h2>
        <p class="pp">
          Theo dõi và quản lý thông tin cư dân tạm vắng trong khu chung cư
        </p>

        <!-- Tìm kiếm và thêm mới -->
        <div class="row mb-3">
          <div class="col-md-6 col-12">
            <form class="input-group" action="QuanLyTamVang" method="get">
              <input
                type="text"
                class="form-control"
                placeholder="Tìm kiếm cư dân"
                name="search"
              />
              <button type="" class="btn btn-search">Tìm kiếm</button>
            </form>
          </div>
          <div class="col-md-6 col-12 text-md-end mt-2 mt-md-0">
            <button
              class="btn btn-primary"
              data-bs-toggle="modal"
              data-bs-target="#addModal"
            >
              Thêm mới tạm vắng
            </button>
          </div>
        </div>
        <!-- Bảng danh sách tạm vắng -->
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
      
            <c:if test="${tamvangs != null}">
              <c:forEach var="tamvang" items="${tamvangs}">
                  <tr>
                      <td>${tamvang.hovaten}</td>
                      <td>${tamvang.cccd}</td>
                      <td>${tamvang.sonha}</td>
                      <td>${tamvang.ngaybatdau}</td>
                      <td>${tamvang.ngayketthuc}</td>
                      <td>${tamvang.lydo}</td>
                    
                      <!-- Kiểm tra ngày kết thúc -->
                      <td>
                          <c:choose>
                              <c:when test="${tamvang.ngayketthuc < today}">
                                  <span class="text-danger">Hết hạn</span>
                              </c:when>
                              <c:otherwise>
                                  <span class="text-success">Đang tạm vắng</span>
                              </c:otherwise>
                          </c:choose>
                      </td>
                      <td>
                          <button
                              class="btn btn-warning btn-sm"
                              data-bs-toggle="modal"
                              data-bs-target="#editModal"
                              onclick="edit('${tamvang.hovaten}','${tamvang.cccd}','${tamvang.sonha}','${tamvang.ngaybatdau}','${tamvang.ngayketthuc}','${tamvang.lydo}')"
                          >
                              Chỉnh sửa
                          </button>
                          <button
                              class="btn btn-danger btn-sm"
                              data-bs-toggle="modal"
                              data-bs-target="#deleteModal"
                              onclick="deletePerson('${tamvang.cccd}')"
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

      <!-- Modal Thêm mới tạm vắng -->
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
              <h5 class="modal-title" id="addModalLabel">Thêm mới tạm vắng</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <form action="TamVang" method="post">
                <input type="hidden" id="xuly" name="xuly" value="1">
                <div class="mb-3">
                  <label for="name" class="form-label">Họ và tên</label>
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
                  <label for="startDate" class="form-label">Ngày bắt đầu</label>
                  <input
                    type="date"
                    class="form-control"
                    id="startDate"
                    name="startDate"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="endDate" class="form-label">Ngày kết thúc</label>
                  <input
                    type="date"
                    class="form-control"
                    id="endDate"
                    name="endDate"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="reason" class="form-label">Lý do tạm vắng</label>
                  <input
                    type="text"
                    class="form-control"
                    id="reason"
                    name="reason"
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
              <h5 class="modal-title" id="editModalLabel">
                Chỉnh sửa tạm vắng
              </h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              <form action="TamVang" method="post">
                <input type="hidden" id="xuly" name="xuly" value="3">
                <div class="mb-3">
                  <label for="name" class="form-label">Họ và tên</label>
                  <input type="text" class="form-control" id="name1" name="name" required />
                </div>
                <div class="mb-3">
                  <label for="idCard" class="form-label">CMND/CCCD</label>
                  <input
                    type="text"
                    class="form-control"
                    id="idCard1"
                    name="idCard"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="apartment" class="form-label">Căn hộ</label>
                  <input
                    type="text"
                    class="form-control"
                    id="apartment1"
                    name="apartment"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="startDate" class="form-label">Ngày bắt đầu</label>
                  <input
                    type="date"
                    class="form-control"
                    id="startDate1"
                    name="startDate"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="endDate" class="form-label">Ngày kết thúc</label>
                  <input
                    type="date"
                    class="form-control"
                    id="endDate1"
                    name="endDate"
                    required
                  />
                </div>
                <div class="mb-3">
                  <label for="reason" class="form-label">Lý do tạm vắng</label>
                  <input
                    type="text"
                    class="form-control"
                    id="reason1"
                    name="reason"
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
                  <button type="submit" class="btn btn-primary">Cập nhật</button>
                </div>
              </form>

            </div>
            
          </div>
        </div>
      </div>
      <!-- Modal Xóa tạm vắng -->
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
              Bạn có chắc chắn muốn xóa tạm vắng này không?
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Hủy
              </button>
              <button type="" class="btn btn-danger" onclick="confirmDelete()">
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
            <div class="modal-body">Tạm vắng đã được xóa thành công.</div>
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
    <form id="deleteForm" action="TamVang" method="POST" >
      <input type="hidden" id="cccdXoa" name="cccdXoa" value="">
      <input type="hidden" id="xuly" name="xuly" value="2">
    </form>
    <script>
      let cccdToDelete = '';
    
      function deletePerson(cccd) {
        cccdToDelete = cccd;
      }
    
      function confirmDelete() {
        // Thực hiện hành động xóa ở đây
        var deleteModal = bootstrap.Modal.getInstance(
          document.getElementById("deleteModal")
        );
        deleteModal.hide();
        
        // Hiển thị modal thông báo xóa thành công
        var successModal = new bootstrap.Modal(
          document.getElementById("successModal")
        );
        successModal.show();
        document.getElementById('cccdXoa').value = cccdToDelete;
        document.getElementById('deleteForm').submit();
      }
    </script>
    <script>
        function edit(name, cccd, apartment, startDate, endDate, reason) {
          document.getElementById("name1").value = name;
          document.getElementById("idCard1").value = cccd;
          document.getElementById("apartment1").value = apartment;
          document.getElementById("startDate1").value = startDate;
          document.getElementById("endDate1").value = endDate;
          document.getElementById("reason1").value = reason;
      }

    </script>
    <script>
      function deleteTamVang() {
        // Thực hiện hành động xóa ở đây
        // Thay thế bằng mã xóa thực tế
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
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
