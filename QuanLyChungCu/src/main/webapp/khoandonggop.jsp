<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Quản lý khoản đóng góp</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
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
/* .table tbody tr input {
        font-size: 13px;
      } */
.form-control::-webkit-inner-spin-button, .form-control::-webkit-outer-spin-button
	{
	-webkit-appearance: none;
	/* Loại bỏ nút tăng giảm trên Chrome, Safari */
	margin: 0; /* Bỏ khoảng cách */
	text-align: center;
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

	<div class="hero">
		<div class="container mt-4">
			<h2>Quản lý khoản đóng góp</h2>
			<p class="pp">Quản lý các khoản đóng góp tự nguyện như quỹ biển
				đảo, quỹ vì người nghèo, quỹ khuyến học...</p>
			<!-- Form tìm kiếm chỉ theo số nhà, tên chủ hộ -->
			<div class="row mb-3">
				<div class="col-md-10 col-12">
					<form action="${pageContext.request.contextPath}/KHOANDONGGOP"
						method="GET" class="row mb-3">
						<input type="hidden" name="action" value="search" />

						<!-- Tìm theo số nhà -->
						<div class="col-md-3 col-12 mb-2 mb-md-0">
							<input type="text" name="sonha" class="form-control"
								placeholder="Tìm theo số nhà" value="${param.sonha}" />
						</div>

						<!-- Tìm theo tên chủ hộ -->
						<div class="col-md-3 col-12 mb-2 mb-md-0">
							<input type="text" name="tenChuHo" class="form-control"
								placeholder="Tìm theo tên chủ hộ" value="${param.tenChuHo}" />
						</div>

						<!-- Nút tìm kiếm -->
						<div class="col-md-2 col-12 mb-2 mb-md-0">
							<button type="submit" class="btn btn-primary w-100">Tìm
								kiếm</button>
						</div>
					</form>
				</div>
				<div class="col-md-2 col-12 text-center">
					<p class="text-muted m-0">
						<c:set var="firstItem" value="${khoanDongGopList[0]}" />
					<div class="row mb-3 align-items-center">
						<!-- Hiển thị ngày thu -->
						<div class="col-md-12 col-12 text-center">
							<p class="text-muted m-0 d-inline">
								Ngày thu: <strong>${firstItem.NgayThu != null ? firstItem.NgayThu : "Chưa có ngày thu"}</strong>
							</p>
						</div>
					</div>
				</div>

			</div>
			<!-- Nút nhập ngày thu -->
			<div class="col-md-2 col-6">
				<button type="button" class="btn btn-success w-100"
					data-bs-toggle="modal" data-bs-target="#addNgayThuModal">
					Nhập ngày thu</button>
			</div>
			<!-- Modal Nhập ngày thu -->
			<div class="modal fade" id="addNgayThuModal" tabindex="-1"
				aria-labelledby="addNgayThuModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<!-- Header của Modal -->
						<div class="modal-header">
							<h5 class="modal-title" id="addNgayThuModalLabel">Nhập Ngày
								Thu</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>

						<!-- Body của Modal -->
						<div class="modal-body">
							<div id="errorAlert" class="alert alert-danger d-none"
								role="alert">
								<!-- Nơi hiển thị lỗi nếu có -->
							</div>
							<form action="${pageContext.request.contextPath}/KHOANDONGGOP"
								method="POST">
								<input type="hidden" name="action" value="updateAllDateColumns" />

								<!-- Nhập ngày thu -->
								<div class="mb-3">
									<label for="newDate" class="form-label">Ngày thu mới</label> <input
										type="date" name="newDate" id="newDate" class="form-control"
										required />
								</div>

								<!-- Nhập ghi chú -->
								<div class="mb-3">
									<label for="ghiChuInput" class="form-label">Ghi chú</label>
									<textarea name="ghiChu" id="ghiChuInput" class="form-control"
										placeholder="Ghi chú về ngày thu (nếu có)"></textarea>
								</div>

								<!-- Nút lưu -->
								<button type="submit" class="btn btn-primary">Lưu</button>
							</form>
						</div>

						<!-- Footer của Modal -->
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Đóng</button>
						</div>
					</div>
				</div>
			</div>



			<!-- Hiển thị thông báo -->
			<c:if test="${not empty message}">
				<div class="alert alert-info">${message}</div>
			</c:if>

			<!-- Bảng đóng góp tự nguyện -->
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th style="width: 13%">Tên chủ hộ</th>
						<th style="width: 6%">Căn hộ</th>
						<th style="width: 9%">Quỹ vì người nghèo</th>
						<th style="width: 9%">Quỹ vì biển đảo VN</th>
						<th style="width: 9%">Quỹ tổ dân phố</th>
						<th style="width: 9%">Quỹ vì trẻ thơ</th>
						<th style="width: 9%">Quỹ nhân đạo, từ thiện</th>
						<th style="width: 9%">Quỹ tình nghĩa</th>
						<th style="width: 9%">Quỹ khuyến học</th>
						<th style="width: 9%">Quỹ người cao tuổi</th>
						<th style="width: 9%">Tổng cộng</th>
						<th>Hành động</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${khoanDongGopList}">
						<!-- Mỗi dòng là một form riêng -->
						<tr>
							<form action="${pageContext.request.contextPath}/KHOANDONGGOP"
								method="post">
								<td>
									<!-- Hiển thị tên chủ hộ --> ${item.TenChuHo} <!-- Input ẩn để gửi CCCDchuho -->
									<input type="hidden" name="cccdchuho" value="${item.CCCDchuho}" />
								</td>
								<td>${item.SoNha}</td>
								<td><input type="number" name="quyVNN"
									value="${item.QuyVNN}" class="form-control" min="0"></td>
								<td><input type="number" name="quyVBD"
									value="${item.QuyVBD}" class="form-control" min="0"></td>
								<td><input type="number" name="quyTDP"
									value="${item.QuyTDP}" class="form-control" min="0"></td>
								<td><input type="number" name="quyVTT"
									value="${item.QuyVTT}" class="form-control" min="0"></td>
								<td><input type="number" name="quyVNDTT"
									value="${item.QuyVNDTT}" class="form-control" min="0">
								</td>
								<td><input type="number" name="quyTN" value="${item.QuyTN}"
									class="form-control" min="0"></td>
								<td><input type="number" name="quyKH" value="${item.QuyKH}"
									class="form-control" min="0"></td>
								<td><input type="number" name="quyNCT"
									value="${item.QuyNCT}" class="form-control" min="0"></td>
								<td>${item.TongThu}</td>
								<td>
									<!-- Nút lưu cho dòng này -->
									<button type="submit" class="btn btn-primary">Lưu</button>
								</td>
							</form>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script>

    <!-- JavaScript để hiển thị modal khi tải trang -->
    <script>
      document.addEventListener("DOMContentLoaded", () => {
        // Kiểm tra trạng thái trong sessionStorage
        const fromLinkA = sessionStorage.getItem("fromLinkA");

        if (fromLinkA === "true") {
          // Hiển thị modal
          const modal = new bootstrap.Modal(
            document.getElementById("tienIchModal")
          );
          modal.show();

          // Xóa trạng thái sau khi hiển thị để tránh hiển thị lại khi reload
          sessionStorage.removeItem("fromLinkA");
        }
      });
    </script>
</body>
</html>

