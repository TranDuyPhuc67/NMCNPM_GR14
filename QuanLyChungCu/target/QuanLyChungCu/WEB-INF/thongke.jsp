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
<title>Thống kê tổng khoản thu</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	rel="stylesheet" />
<style>
.navbar {
	background-color: rgba(21, 35, 45, 0.9) !important;
	/* Màu nền khi cuộn */
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

.table th, .table td {
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

.form-control1::-webkit-inner-spin-button, .form-control1::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	/* Loại bỏ nút tăng giảm trên Chrome, Safari */
	margin: 0; /* Bỏ khoảng cách */
	text-align: center;
}

.form-control1 {
	text-align: center; /* Căn giữa nội dung */
	border-radius: 5px;
	border: 1px solid #e5e5e5;
}

.form-control1:focus {
	color: #212529; /* Màu chữ khi focus */
	background-color: #fff; /* Màu nền khi focus */
	border-color: #80bdff; /* Màu viền khi focus */
	outline: 0; /* Bỏ outline */
	box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
	/* Hiệu ứng viền xanh mờ */
}
</style>
</head>
<body>
	<!-- navbar -->
	<nav class="navbar navbar-expand-lg">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.jsp" style="margin-left: 30px">
				<img src="Image/Remove-bg.ai_1730519657240.png" alt="Logo"
				height="50" />
			</a>
			<button aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation" class="navbar-toggler"
				data-bs-target="#navbarNav" data-bs-toggle="collapse" type="button">
				<span class="navbar-toggler-icon"> </span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a aria-current="page"
						class="nav-link active" href="index.jsp"> Trang chủ </a></li>
					<li class="nav-item"><a class="nav-link" href="#">Tiện ích
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#"> Dịch vụ
							khác</a></li>
					<li class="nav-item"><a class="nav-link" href="#"> Liên hệ
							hỗ trợ</a></li>
					<li class="nav-item"><a class="nav-link" href="#"> </a></li>
					<li class="nav-item"><a class="btn btn-outline-secondary"
						href="#" style="color: rgb(216, 216, 216); margin-right: 10px">
							<i class="fas fa-user"></i>
					</a></li>
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
					<form action="TONGTHANHTOAN" method="GET" class="row mb-3">
						<input type="hidden" name="action" value="search" />
						<div class="col-md-3">
							<input type="text" name="sonha" class="form-control"
								placeholder="Tìm theo số nhà" value="${param.sonha}" />
						</div>
						<div class="col-md-3">
							<input type="text" name="hotenchuho" class="form-control"
								placeholder="Tìm theo tên chủ hộ" value="${param.hotenchuho}" />
						</div>

						<!-- Tìm theo tháng -->
						<div class="col-md-2">
							<select name="month" class="form-control">
								<option value="">--Chọn tháng--</option>
								<c:forEach var="i" begin="1" end="12">
									<option value="${i}" ${param.month == i ? "selected" : ""}>
										Tháng ${i}</option>
								</c:forEach>
							</select>
						</div>

						<!-- Tìm theo năm -->
						<div class="col-md-2">
							<input type="number" name="year" class="form-control"
								placeholder="Năm" value="${param.year}" min="1900" max="2100" />
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
						<th>Tên chủ hộ</th>
						<th>Căn hộ</th>
						<th>Phí chung cư</th>
						<th>Phí gửi xe</th>
						<th>Phí tiện ích</th>
						<th>Tổng</th>
						<th>Số tiền đã nộp</th>
						<th>Dư/Nợ tổng</th>
						<th>Dư/Nợ trước</th>
						<th>Hạn thu</th>
						<th>Trạng thái</th>
						<th>Thao tác</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${tongThanhToanList}">
						<tr>
							<td>${item.hotenchuho}</td>
							<td>${item.sonha}</td>
							<td>${item.tongphichungcu}</td>
							<td>${item.tongtienich}</td>
							<td>${item.tongguixe}</td>
							<td>${item.tongphi}</td>
							<td>${item.sotiendanop}</td>
							<td>${item.sodu}</td>
							<td>${item.sodu_thang_truoc}</td>
							<td>${item.thoigianthu}</td>
							<td>${item.trangthai}</td>
							<td>
								<!-- Form cập nhật số tiền đã nộp -->
								<form method="post" action="TONGTHANHTOAN">
									<input type="hidden" name="action" value="update" /> <input
										type="hidden" name="idcanho" value="${item.idcanho}" /> <input
										type="hidden" name="hanthu" value="${item.hanthu}" />
									<!-- Thêm giá trị tháng và năm hiện tại -->
									<input type="hidden" name="month" value="${param.month}" /> <input
										type="hidden" name="year" value="${param.year}" />
									<div class="col-12 d-flex align-items-center"
										style="width: 130px;">
										<input type="number" name="sotiendanop" class="form-control1"
											placeholder="Nhập số tiền" required
											style="width: 70px; font-size: 12px; padding: 5px;" />
										<button type="submit" class="btn btn-primary btn-sm"
											style="font-size: 10px; padding: 3px 8px; margin-left: 5px;">
											Cập nhật</button>
									</div>
								</form>

							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty tongThanhToanList}">
						<tr>
							<td colspan="10" class="text-center">Không có dữ liệu để
								hiển thị.</td>
						</tr>
					</c:if>
				</tbody>
			</table>

			<!-- modal xác nhận tháng của thống kê-->
			<div class="modal fade" id="thongKeModal" tabindex="-1"
				aria-labelledby="thongKeModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content modal-thangNam">
						<div class="modal-header">
							<h5 class="modal-title" id="thongKeModalLabel">Nhập tháng
								năm bạn muốn thống kê</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form action="TONGTHANHTOAN" method="GET">
								<input type="hidden" name="action" value="filter" /> <label
									for="month">Tháng:</label> <input type="number" id="month"
									name="month" class="form-control" required min="1" max="12" /><br />
								<label for="year">Năm:</label> <input type="number" id="year"
									name="year" class="form-control" required min="1900" max="2100" /><br />
								<button type="submit" class="btn btn-primary">Xác nhận</button>
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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
