<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>Quản lý phí gửi xe</title>
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
	color: #ffffff;
}
</style>
</head>
<body>
	<!-- navbar -->
	<nav class="navbar navbar-expand-lg">
		<div class="container-fluid">
			<a class="navbar-brand" href="index.jsp"
				style="margin-left: 30px"> <img
				src="Image/Remove-bg.ai_1730519657240.png" alt="Logo" height="50" />
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
	<div class="container mt-4">
		<h2>Quản lý phí gửi xe</h2>
		<!-- thay đổi từ A202 thành id căn hộ -->
		<p class="pp">Thống kê chi tiết tất cả các khoản phí gửi xe hàng
			tháng của từng căn hộ</p>
		<!-- Form tìm kiếm -->
		<div class="row mb-3">
			<div class="col-md-10 col-12">
				<form action="PHIGUIXE" method="GET" class="row mb-3">
					<input type="hidden" name="action" value="search" />
					<!-- Tìm theo số nhà -->
					<div class="col-md-3">
						<input type="text" name="sonha" class="form-control"
							placeholder="Tìm theo số nhà"
							value="${param.sonha != null ? param.sonha : ''}" />
					</div>
					<!-- Tìm theo tên chủ hộ -->
					<div class="col-md-3">
						<input type="text" name="tenChuHo" class="form-control"
							placeholder="Tìm theo tên chủ hộ"
							value="${param.tenChuHo != null ? param.tenChuHo : ''}" />
					</div>
					<!-- Tìm theo tháng -->
					<div class="col-md-2">
						<select name="month" class="form-control">
							<option value="">--Chọn tháng--</option>
							<c:forEach var="i" begin="1" end="12">
								<option value="${i}"
									${param.month != null && param.month == i ? 'selected' : ''}>
									Tháng ${i}</option>
							</c:forEach>
						</select>
					</div>
					<!-- Tìm theo năm -->
					<div class="col-md-2">
						<input type="number" name="year" class="form-control"
							placeholder="Năm" value="${param.year != null ? param.year : ''}"
							min="1900" max="2100" />
					</div>
					<!-- Nút tìm kiếm -->
					<div class="col-md-2">
						<button type="submit" class="btn btn-primary">Tìm kiếm</button>
					</div>
				</form>

			</div>
			<!-- Nút cập nhật phí -->
			<div class="col-md-2 col-12 text-md-end mt-2 mt-md-0">
				<button class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#addModal">Cập nhật phí</button>
			</div>
		</div>

		<!-- Hiển thị thông báo -->
		<c:if test="${not empty message}">
			<div class="alert alert-info">${message}</div>
		</c:if>
		<!-- Phí gửi xe -->
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Tên Chủ Hộ</th>
					<th>Số Nhà</th>
					<th>SL ô tô</th>
					<th>Phí ô tô</th>
					<th>SL xe máy</th>
					<th>Phí xe máy</th>
					<th>Tổng phí</th>
					<th>Tháng thu</th>
					<th>Hành động</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${combinedList}">
					<tr>
						<td>${item.hotenchuho != null ? item.hotenchuho : "Không xác định"}</td>
						<td>${item.sonha}</td>
						<td>${item.sooto}</td>
						<td>${item.tienxeoto}</td>
						<td>${item.soxemay}</td>
						<td>${item.tienxemay}</td>
						<td>${item.tongguixe}</td>
						<td>${item.hanthu}</td>
						<td>
							<form action="PHIGUIXE" method="GET">
								<input type="hidden" name="action" value="delete"> <input
									type="hidden" name="idCanHo" value="${item.idcanho}"> <input
									type="hidden" name="hanthu" value="${item.hanthu}"> <input
									type="hidden" name="month" value="${param.month}"> <input
									type="hidden" name="year" value="${param.year}">
								<button type="submit" class="btn btn-danger btn-sm"
									onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">Xóa</button>
							</form>
						</td>
					</tr>
				</c:forEach>
				<c:if test="${empty combinedList}">
					<tr>
						<td colspan="8" class="text-center">Không có tiện ích nào để
							hiển thị.</td>
					</tr>
				</c:if>
			</tbody>
		</table>

		<!-- Modal cập nhật phí gửi xe -->
		<div class="modal fade" id="addModal" tabindex="-1"
			aria-labelledby="updateModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="updateModalLabel">Cập nhật phí
							gửi xe</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form action="PHIGUIXE" method="POST">
							<input type="hidden" name="action" value="add" /> <input
								type="hidden" name="month" value="${param.month}"> <input
								type="hidden" name="year" value="${param.year}"><input
								type="hidden" id="update-idcanho" name="idcanho" /> <label
								for="phixeoto">Phí ô tô (1 xe/tháng):</label> <input
								type="number" id="phixeoto" name="tienxeoto"
								class="form-control" required min="0" /><br /> <label
								for="phixemay">Phí xe máy (1 xe/tháng):</label> <input
								type="number" id="phixemay" name="tienxemay"
								class="form-control" required min="0" /><br /> <label
								for="hanthu">Tháng năm thu phí (YYYY-MM):</label> <input
								type="text" id="hanthu" name="hanthu" class="form-control"
								required pattern="\d{4}-\d{2}" title="Định dạng YYYY-MM" /><br />

							<button type="submit" class="btn btn-primary">Cập nhật</button>
						</form>
					</div>
				</div>
			</div>
		</div>


		<!-- modal xác nhận tháng phí gửi xe-->
		<div class="modal fade" id="guiXeModal" tabindex="-1"
			aria-labelledby="guiXeModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content modal-thangNam">
					<div class="modal-header">
						<h5 class="modal-title" id="guiXeModalLabel">Nhập tháng năm
							thu phí gửi xe</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form action="PHIGUIXE" method="GET">
							<input type="hidden" name="action" value="search" /> <label
								for="month">Tháng:</label> <input type="number" id="month"
								name="month" class="form-control" placeholder="Nhập tháng"
								required min="1" max="12" /><br /> <label for="year">Năm:</label>
							<input type="number" id="year" name="year" class="form-control"
								placeholder="Nhập năm" required min="1900" max="2100" /><br />
							<button type="submit" class="btn btn-primary">Xác nhận</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<!-- JavaScript để hiển thị modal khi tải trang -->
	<script>
      document.addEventListener("DOMContentLoaded", () => {
        // Kiểm tra trạng thái trong sessionStorage
        const fromLinkA = sessionStorage.getItem("fromLinkA");

        if (fromLinkA === "true") {
          // Hiển thị modal
          const modal = new bootstrap.Modal(
            document.getElementById("guiXeModal")
          );
          modal.show();

          // Xóa trạng thái sau khi hiển thị để tránh hiển thị lại khi reload
          sessionStorage.removeItem("fromLinkA");
        }
      });
    </script>
</body>
</html>
