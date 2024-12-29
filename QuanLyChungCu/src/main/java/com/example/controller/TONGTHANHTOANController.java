package com.example.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.service.TONGTHANHTOANService;

@WebServlet("/TONGTHANHTOAN")
public class TONGTHANHTOANController extends HttpServlet {
	private static final long serialVersionUID = -7418484588273074825L;
	private TONGTHANHTOANService service;

	@Override
	public void init() throws ServletException {
		service = new TONGTHANHTOANService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("---- Start: Handling doGet in TONGTHANHTOANController ----");

		try {
			// Cập nhật hoặc chèn dữ liệu vào bảng TONGTHANHTOAN
			service.insertOrUpdateTongThanhToan();

			// Lấy thông tin tìm kiếm từ request
			String sonha = request.getParameter("sonha");
			String hotenchuho = request.getParameter("hotenchuho");
			String month = request.getParameter("month");
			String year = request.getParameter("year");

			List<Map<String, Object>> list;

			if ((sonha != null && !sonha.isEmpty()) || (hotenchuho != null && !hotenchuho.isEmpty())
					|| (month != null && !month.isEmpty()) || (year != null && !year.isEmpty())) {
				System.out.println("Thực hiện tìm kiếm với các điều kiện:");
				list = service.search(sonha, hotenchuho, month, year);
				if (list.isEmpty()) {
					request.setAttribute("message", "Không tìm thấy dữ liệu phù hợp với điều kiện tìm kiếm.");
				}
			} else {
				System.out.println("Lấy toàn bộ danh sách tổng thanh toán");
				list = service.getAll();
			}

			request.setAttribute("tongThanhToanList", list);

			// Chuyển tiếp đến file JSP
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/thongke.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.err.println("Lỗi trong doGet: " + e.getMessage());
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra khi xử lý yêu cầu.");
		}

		System.out.println("---- End: Handling doGet in TONGTHANHTOANController ----");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter("action");

			if ("update".equals(action)) {
				// Nhận thông tin từ request
				int idCanHo = Integer.parseInt(request.getParameter("idcanho"));
				String hanThu = request.getParameter("hanthu");
				int soTienDaNop = Integer.parseInt(request.getParameter("sotiendanop"));

				// Gọi service để cập nhật số tiền đã nộp
				service.updateSoTienDaNopForCurrentMonth(idCanHo, hanThu, soTienDaNop);

				// Gọi lại doGet để làm mới danh sách
				doGet(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Hành động không hợp lệ!");
			}
		} catch (NumberFormatException e) {
			System.out.println("Lỗi định dạng dữ liệu: " + e.getMessage());
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dữ liệu đầu vào không đúng định dạng!");
		} catch (Exception e) {
			System.out.println("Lỗi trong doPost: " + e.getMessage());
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Có lỗi xảy ra khi xử lý yêu cầu.");
		}
	}

}
