<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Quản lý phí chung cư</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet" />
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

.table th, .table td {
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
			<a class="navbar-brand" href="HomePage.html"
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
						class="nav-link active" href="HomePage.html"> Trang chủ </a></li>
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

	<div class="container mt-4">
		<h2>Quản lý phí chung cư</h2>
		<p class="pp">Bao gồm phí quản lý và phí dịch vụ</p>
		<!-- Form tìm kiếm -->
		<div class="row mb-3">
			<div class="col-md-10 col-12">
				<form action="PHICHUNGCU" method="GET" class="row mb-3">
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
		<!-- Bảng danh sách phí chung cư -->
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th>Tên chủ hộ</th>
					<th>Số nhà</th>
					<th>Diện tích (m²)</th>
					<th>Phí dịch vụ</th>
					<th>Phí quản lý</th>
					<th>Tổng Phí</th>
					<th>Thời hạn thu</th>
					<th>Hành động</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${combinedList}">
					<tr>
						<td>${item.hotenchuho != null ? item.hotenchuho : "Không xác định"}</td>
						<td>${item.sonha}</td>
						<td>${item.dientich}</td>
						<td>${item.phidichvu}</td>
						<td>${item.phiquanly}</td>
						<td>${item.tongphichungcu}</td>
						<td>${item.hanthu}</td>
						<td>
							<form action="PHICHUNGCU" method="GET">
								<input type="hidden" name="action" value="delete"> <input
									type="hidden" name="idCanHo" value="${item.idcanho}"> <input
									type="hidden" name="hanthu" value="${item.hanthu}">
								<!-- Thêm giá trị tháng và năm hiện tại -->
								<input type="hidden" name="month" value="${param.month}">
								<input type="hidden" name="year" value="${param.year}">
								<button type="submit" class="btn btn-danger btn-sm"
									onclick="return confirm('Bạn có chắc chắn muốn xóa không?');">Xóa</button>
							</form>
						</td>
					</tr>
				</c:forEach>

				<c:if test="${empty combinedList}">
					<tr>
						<td colspan="8" class="text-center">Không có dữ liệu để hiển
							thị</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>

	<!-- Modal Thêm phí -->
	<div class="modal fade" id="addModal" tabindex="-1"
		aria-labelledby="addModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="addModalLabel">Thêm phí chung cư</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="PHICHUNGCU" method="POST">
						<input type="hidden" name="action" value="add" />
						    <input type="hidden" name="month" value="${param.month}">
    						<input type="hidden" name="year" value="${param.year}">
						<div class="mb-3">
							<label for="phichungcum2" class="form-label">Phí dịch vụ
								(trên 1m²):</label> <input type="number" id="phichungcum2"
								name="phichungcum2" class="form-control"
								placeholder="Nhập phí dịch vụ (VNĐ)" required min="0" />
						</div>
						<div class="mb-3">
							<label for="phiquanlym2" class="form-label">Phí quản lý
								(trên 1m²):</label> <input type="number" id="phiquanlym2"
								name="phiquanlym2" class="form-control"
								placeholder="Nhập phí quản lý (VNĐ)" required min="0" />
						</div>
						<div class="mb-3">
							<label for="hanthu" class="form-label">Thời hạn:</label> <input
								type="text" id="hanthu" name="hanthu" class="form-control"
								placeholder="Chọn ngày thu phí" required />
						</div>
						<div class="mb-3">
							<label for="thoigianthu" class="form-label">Thời gian
								thu:</label> <input type="date" id="thoigianthu" name="thoigianthu"
								class="form-control" placeholder="Thời hạn đóng phí" required />
						</div>

						<button type="submit" class="btn btn-primary">Áp dụng</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal xác nhận tháng phí chung cư -->
	<div class="modal fade" id="chungCuModal" tabindex="-1"
		aria-labelledby="chungCuModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content modal-thangNam">
				<div class="modal-header">
					<h5 class="modal-title" id="chungCuModalLabel">Nhập tháng và
						năm thu phí chung cư</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="PHICHUNGCU" method="GET">
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
            document.getElementById("chungCuModal")
          );
          modal.show();

          // Xóa trạng thái sau khi hiển thị để tránh hiển thị lại khi reload
          sessionStorage.removeItem("fromLinkA");
        }
      });
    </script>
</body>
</html>
