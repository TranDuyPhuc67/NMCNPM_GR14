<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- Nếu request không có danh sách tiện ích (tienIchList), chuyển hướng về Servlet --%>
<%
if (request.getAttribute("tienIchList") == null) {
	response.sendRedirect("TIENICH");
	return;
}
%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Quản lý Tiện ích</title>
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
			<h2>Quản lý tiện ích</h2>
			<p class="pp">Thêm các khoản tiện ích bao gồm phí điện, nước,
				internet và quản lý dữ liệu.</p>

			<!-- Form tìm kiếm -->
			<div class="row mb-3">
				<div class="col-md-10 col-12">
					<form action="TIENICH" method="GET" class="row mb-3">
						<input type="hidden" name="action" value="search" />
						<div class="col-md-3">
							<input type="text" name="sonha" class="form-control"
								placeholder="Tìm theo số nhà"
								value="${param.sonha != null ? param.sonha : ''}" />
						</div>
						<div class="col-md-3">
							<input type="text" name="tenChuHo" class="form-control"
								placeholder="Tìm theo tên chủ hộ"
								value="${param.tenChuHo != null ? param.tenChuHo : ''}" />
						</div>
						<div class="col-md-2">
							<select name="month" class="form-control">
								<option value="">--Chọn tháng--</option>
								<c:forEach var="i" begin="1" end="12">
									<option value="${i}" ${i == param.month ? "selected" : ""}>Tháng
										${i}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-2">
							<input type="number" name="year" class="form-control"
								placeholder="Năm"
								value="${param.year != null ? param.year : ''}" min="1900"
								max="2100" />
						</div>
						<div class="col-md-2">
							<button type="submit" class="btn btn-primary">Tìm kiếm</button>
						</div>
					</form>

				</div>
				<div class="col-md-2 col-12 text-md-end mt-2 mt-md-0">
					<button class="btn btn-primary" data-bs-toggle="modal"
						data-bs-target="#addModal">Thêm tiện ích</button>
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
						<th>Tiền Điện</th>
						<th>Tiền Nước</th>
						<th>Internet</th>
						<th>Tổng Phí</th>
						<th>Tháng thu</th>
						<th>Hành động</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${tienIchList}">
						<tr>
							<td>${item.TenChuHo != null ? item.TenChuHo : 'Không xác định'}
							</td>
							<td>${item.Sonha != null ? item.Sonha : 'Không xác định'}</td>
							<td>${item.Sodien}</td>
							<td>${item.Sonuoc}</td>
							<td>${item.Internet}</td>
							<td>${item.Tongtienich}</td>
							<td>${item.Hanthu}</td>
							<td>
								<button class="btn btn-warning btn-sm" data-bs-toggle="modal"
									data-bs-target="#updateModal" data-id="${item.Idcanho}"
									data-sodien="${item.Sodien}" data-sonuoc="${item.Sonuoc}"
									data-internet="${item.Internet}" data-hanthu="${item.Hanthu}">
									Cập nhật</button> <a
								href="TIENICH?action=delete&idcanho=${item.Idcanho}&hanthu=${item.Hanthu}"
								class="btn btn-danger btn-sm"
								onclick="return confirm('Bạn có chắc chắn muốn xóa tiện ích này?')">
									Xóa </a>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty tienIchList}">
						<tr>
							<td colspan="8" class="text-center">Không có tiện ích nào để
								hiển thị.</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</div>

	<!-- Modal Thêm tiện ích -->
	<div class="modal fade" id="addModal" tabindex="-1"
		aria-labelledby="addModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="addModalLabel">Thêm tiện ích</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="TIENICH" method="POST">
						<input type="hidden" name="action" value="add" /> <label
							for="idcanho">ID Căn Hộ:</label> <input type="number"
							id="idcanho" name="idcanho" class="form-control" required min="0" /><br />
						<label for="sodien">Tiền Điện:</label> <input type="number"
							id="sodien" name="sodien" class="form-control" required min="0" /><br />
						<label for="sonuoc">Tiền Nước:</label> <input type="number"
							id="sonuoc" name="sonuoc" class="form-control" required min="0" /><br />

						<label for="internet">Internet:</label> <input type="number"
							id="internet" name="internet" class="form-control" required
							min="0" /><br /> <label for="hanthu">Thời gian:</label> <input
							type="text" id="hanthu" name="hanthu" class="form-control"
							required /><br />

						<button type="submit" class="btn btn-primary">Thêm tiện
							ích</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Cập nhật tiện ích -->
	<div class="modal fade" id="updateModal" tabindex="-1"
		aria-labelledby="updateModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="updateModalLabel">Cập nhật tiện
						ích</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="TIENICH" method="POST">
						<input type="hidden" name="action" value="update" /> <input
							type="hidden" id="update-idcanho" name="idcanho" /> <label
							for="update-sodien">Tiền Điện:</label> <input type="number"
							id="update-sodien" name="sodien" class="form-control" required
							min="0" /><br /> <label for="update-sonuoc">Tiền Nước:</label>
						<input type="number" id="update-sonuoc" name="sonuoc"
							class="form-control" required min="0" /><br /> <label
							for="update-internet">Internet:</label> <input type="number"
							id="update-internet" name="internet" class="form-control"
							required min="0" /><br /> <label for="update-hanthu">Thời
							gian thu</label> <input type="text" id="update-hanthu" name="hanthu"
							class="form-control" required /><br />
						<button type="submit" class="btn btn-primary">Cập nhật
							tiện ích</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal Tìm kiếm theo tháng và năm -->
	<div class="modal fade" id="tienIchModal" tabindex="-1"
		aria-labelledby="tienIchModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="tienIchModalLabel">Nhập tháng năm
						bạn muốn thống kê</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form action="TIENICH" method="GET">
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