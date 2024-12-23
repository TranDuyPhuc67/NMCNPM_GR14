<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản lý Phí Gửi Xe</title>
</head>
<body>
    <h2>Thêm/Sửa Phí Gửi Xe</h2>
    <form action="PHIGUIXE" method="POST">
        <label for="CCCDchuho">CCCD Chủ Hộ:</label>
        <input type="text" id="CCCDchuho" name="CCCDchuho" required><br><br>
        <label for="CCCDchuho">Họ Tên Chủ Hộ:</label>
        <input type="text" name="CCCDchuho" required><br><br>
        <label for="Soxemay">Số Xe Máy:</label>
        <input type="number" id="Soxemay" name="Soxemay" required><br><br>

        <label for="Sooto">Số Ô Tô:</label>
        <input type="number" id="Sooto" name="Sooto" required><br><br>

        <label for="Thoihan">Thời Hạn:</label>
        <input type="date" id="Thoihan" name="Thoihan" required><br><br>

        <button type="submit">Thêm/Cập nhật</button>
    </form>

    <c:if test="${not empty message}">
        <p>${message}</p>
    </c:if>

    <h2>Danh sách Phí Gửi Xe</h2>
    <table border="1">
        <thead>
            <tr>
                <th>CCCD Chủ Hộ</th>
                <th>Số Xe Máy</th>
                <th>Phí Xe Máy</th>
                <th>Số Ô Tô</th>
                <th>Phí Ô Tô</th>
                <th>Tổng Phí</th>
                <th>Thời Hạn</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${phiguixeList}">
                <tr>
                    <td>${item.CCCDchuho}</td>
                    <td>${item.Soxemay}</td>
                    <td>${item.Soxemay * 100000}</td> 
                    <td>${item.Sooto}</td>
                    <td>${item.Sooto * 500000}</td> 
                    <td>${item.Soxemay * 100000 + item.Sooto * 500000}</td>
                    <td>${item.Thoihan}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
