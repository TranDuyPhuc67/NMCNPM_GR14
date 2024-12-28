<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>Chung cư BlueMoon</title>
<link crossorigin="anonymous"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	rel="stylesheet" />
<style>
body {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
}

.navbar {
	background-color: transparent !important; /* Làm trong suốt ban đầu */
	transition: background-color 0.3s ease, box-shadow 0.3s ease;
}

.navbar.scrolled {
	background-color: rgba(21, 35, 45, 0.9) !important;
	/* Màu nền khi cuộn */
	box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Đổ bóng */
}

.navbar.scrolled .navbar-nav .nav-link {
	color: #d3d3d3;
}

.navbar-nav .nav-link {
	color: #abcdee;
}

.navbar-nav .nav-link.active {
	font-weight: 700;
	color: #ffffff;
}

.navbar-nav .btn {
	margin-left: 10px;
}

.header {
	text-align: center;
	margin-top: 50px;
}

.heroo {
	position: relative;
	background: url("Image/banner.jpg") no-repeat center center/cover;
	height: 700px;
	color: white;
	text-align: center;
	display: grid;
	justify-items: center;
	align-content: start;
}

.overlay {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5); /* Màu đen với độ trong suốt */
	z-index: 0;
}

.header {
	position: relative;
	z-index: 2;
	margin-top: 90px;
}

.header h1 {
	font-family: "Roboto", sans-serif; /* Áp dụng font Roboto */
	font-size: 42px; /* Điều chỉnh kích thước font */
	font-weight: 700; /* Đặt trọng lượng font đậm */
	text-align: center; /* Căn giữa tiêu đề */
	margin-top: 20px; /* Khoảng cách trên */
	margin-bottom: 40px;
}

.header p {
	color: #ffffff;
	margin-bottom: 40px;
}

.card {
	border: 1px solid #8d8d8e;
	border-radius: 8px;
	color: #4f4f4f;
	background-color: #ededed;
	padding: 20px;
	margin: 20px;
	text-align: center;
}

.card i {
	font-size: 24px;
	color: #3a3d61;
	margin-bottom: 10px;
}

.modal-content {
	background-color: rgba(220, 220, 220, 0.95);
	/* Màu nền với độ trong suốt 80% */
	border-radius: 15px;
}

.modal-dialog {
	max-width: 40%;
	padding: 15px;
}

.card-modal {
	border: 1px solid #8d8d8e;
	border-radius: 15px;
	color: #e5e5e5;
	background-color: #318cbd;
	padding: 15px;
	margin: 25px;
	text-align: center;
}

.modal-body a {
	text-decoration: none;
}

.modal-thangNam {
	background-color: #efeff0;
}

.modal-thangNam .modal-header {
	background-color: #318cbd;
}

.modal-thangNam .modal-body {
	color: #2b2b2b;
	text-align: left;
}

.footer {
	background-color: #f1f3f5;
	padding: 20px 0;
	text-align: center;
	font-size: 14px;
	color: #6c757d;
}

.footer .left, .footer .right {
	display: inline-block;
	width: 45%;
	vertical-align: top;
}

.footer .left {
	text-align: left;
}

.footer .right {
	text-align: right;
}

.footer .left h5, .footer .right h5 {
	color: rgb(28, 87, 136);
	font-weight: bold;
}

.row a {
	text-decoration: none;
}

.card:hover {
	transform: scale(1.05); /* Phóng to card khi hover */
	transition: transform 0.3s; /* Thêm hiệu ứng chuyển tiếp */
}

.dropdown-item:hover {
	background-color: #4f4f4f;
}
</style>
</head>
<body>
	<div class="hide-navbar">
		<nav class="navbar navbar-expand-lg fixed-top">
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
						<li class="nav-item"><a class="nav-link" href="#">Tiện
								ích </a></li>
						<li class="nav-item"><a class="nav-link" href="#"> Dịch
								vụ khác</a></li>
						<li class="nav-item"><a class="nav-link" href="#"> Liên
								hệ hỗ trợ</a></li>
						<li class="nav-item">
							<%
							String username = (String) session.getAttribute("username");
							if (username != null) {
							%>
							<div class="dropdown">
								<button class="btn btn-secondary dropdown-toggle" type="button"
									id="notificationDropdown" data-bs-toggle="dropdown"
									aria-expanded="false">
									<i class="fas fa-user"> </i>
								</button>
								<ul class="dropdown-menu dropdown-menu-end"
									aria-labelledby="notificationDropdown"
									style="border-radius: 10px; background-color: rgba(52, 52, 52, 0.8);">
									<li>
										<p class="dropdown-item" style="color: #efeff0">Bạn chưa
											có thông báo mới</p>
									</li>

									<li>
										<hr class="dropdown-divider" style="background-color: #efeff0" />
									</li>
									<li><a class="dropdown-item" href="#"
										style="color: #efeff0">Trang cá nhân</a></li>
									<li><a href="Logout" style="text-decoration: none">
											<button class="dropdown-item" style="color: #efeff0">
												Đăng xuất</button>
									</a></li>
								</ul>
							</div> <%
 } else {
 %> <a class="btn btn-outline-secondary" href="Login.jsp">Đăng
								nhập</a> <%
 }
 %>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<div class="heroo">
		<div class="overlay"></div>
		<div class="header">
			<h1>
				Chào mừng đến với website <br />quản lý chung cư
			</h1>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-lg-4">
					<div class="card" data-bs-toggle="modal"
						data-bs-target="#checkModal" style="cursor:pointer">
						<i class="fas fa-receipt"> </i>
						<h5 class="card-title">Quản lý khoản thu</h5>
						<p class="card-text">Theo dõi, tổ chức và kiểm soát toàn bộ
							các khoản thu trong căn hộ.</p>
					</div>
					<!-- </a> -->
				</div>
				<div class="col-md-6 col-lg-4">
					<a href="QuanlyCanHo.html">
						<div class="card">
							<i class="fas fa-building"> </i>
							<h5 class="card-title">Quản lý căn hộ</h5>
							<p class="card-text">Theo dõi và tổ chức thông tin chi tiết
								về từng căn hộ.</p>
						</div>
					</a>
				</div>
				<div class="col-md-6 col-lg-4">
					<a href="QuanLyHoGiaDinh.html">
						<div class="card">
							<i class="fas fa-users"> </i>
							<h5 class="card-title">Quản lý hộ gia đình</h5>
							<p class="card-text">Theo dõi và tổ chức thông tin của các hộ
								gia đình trong khu căn hộ.</p>
						</div>
					</a>
				</div>
				<div class="col-md-6 col-lg-4">
					<a href="QuanLyCuDan.html">
						<div class="card">
							<i class="fas fa-building"> </i>
							<h5 class="card-title">Quản lý cư dân</h5>
							<p class="card-text">Theo dõi và tổ chức thông tin chi tiết
								về tất cả cư dân trong khu chung cư.</p>
						</div>
					</a>
				</div>
				<div class="col-md-6 col-lg-4">
					<a href="QuanLyTamTru.html">
						<div class="card">
							<i class="fas fa-id-card"> </i>
							<h5 class="card-title">Quản lý tạm trú</h5>
							<p class="card-text">Thông tin cư dân tạm trú tại căn hộ
								trong một khoảng thời gian nhất định.</p>
						</div>
					</a>
				</div>
				<div class="col-md-6 col-lg-4">
					<a href="QuanLyTamVang.html">
						<div class="card">
							<i class="fas fa-id-card"> </i>
							<h5 class="card-title">Quản lý tạm vắng</h5>
							<p class="card-text">Thông tin các cư dân rời khỏi căn hộ
								trong một khoảng thời gian nhất định.</p>
						</div>
					</a>
				</div>
			</div>

			<!-- Modal xác lựa chọn -->
			<div class="modal fade" id="checkModal" tabindex="-1"
				aria-labelledby="checkModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-body" style="color: #4f4f4f; text-align: left">
							<div class="col-12">
								<a href="phichungcu.jsp"
									onclick="sessionStorage.setItem('fromLinkA', 'true')">
									<div class="card-modal">
										<h5 class="card-title">Quản lý phí chung cư</h5>
									</div>
								</a>
							</div>
							<div class="col-12">
								<a href="tienich.jsp"
									onclick="sessionStorage.setItem('fromLinkA', 'true')">
									<div class="card-modal">
										<h5 class="card-title">Quản lý phí tiện ích</h5>
									</div>
								</a>
							</div>
							<div class="col-12">
								<a href="phiguixe.jsp"
									onclick="sessionStorage.setItem('fromLinkA', 'true')">
									<div class="card-modal">
										<h5 class="card-title">Quản lý phí gửi xe</h5>
									</div>
								</a>
							</div>
							<div class="col-12">
								<a href="thongke.jsp"
									onclick="sessionStorage.setItem('fromLinkA', 'true')">
									<div class="card-modal">
										<h5 class="card-title">Thống kê các khoản thu</h5>
									</div>
								</a>
							</div>
							<div class="col-12" data-bs-toggle="modal"
								data-bs-target="#Modal">
								<a href="/QuanLyChungCu/KHOANDONGGOP"
									onclick="sessionStorage.setItem('fromLinkA', 'true')">
									<div class="card-modal">
										<h5 class="card-title">Quản lý khoản đóng góp tự nguyện</h5>
									</div>
								</a>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>

	<div class="footer">
		<div class="left">
			<h5>BlueMoon Apartment</h5>
			<p>
				Quản lý chung cư hiệu quả<br />
			</p>
		</div>
		<div class="right">
			<h5>Nhóm 14</h5>
			<p>Nhập môn công nghệ phần mềm</p>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script>
      // Lấy phần tử navbar
      const navbar = document.querySelector(".navbar");

      // Lắng nghe sự kiện cuộn trang
      window.addEventListener("scroll", () => {
        if (window.scrollY > 70) {
          // Nếu cuộn xuống quá 50px
          navbar.classList.add("scrolled"); // Thêm lớp "scrolled"
        } else {
          navbar.classList.remove("scrolled"); // Xóa lớp "scrolled"
        }
      });
    </script>
</body>
</html>