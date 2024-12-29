package com.example.controller;



import java.io.IOException;

import java.util.ArrayList;

import java.util.Map;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.KHOANDONGGOP;
import com.example.service.KHOANDONGGOPService;

@WebServlet("/KHOANDONGGOP")
public class KHOANDONGGOPController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private KHOANDONGGOPService khoandonggopService;

	@Override
	public void init() throws ServletException {
		// Khởi tạo service
		this.khoandonggopService = new KHOANDONGGOPService();
		// Đồng bộ hóa dữ liệu từ HOGIADINH sang KHOANDONGGOP (nếu còn cần)
		// Đảm bảo mỗi CCCDchuho đều có 1 dòng trong KHOANDONGGOP (với giá trị 0)
		khoandonggopService.synchronizeData();
	}

	// KHOANDONGGOPController.java

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Lấy tham số 'action' từ request
			String action = request.getParameter("action");

			ArrayList<Map<String, Object>> list;

			if ("search".equalsIgnoreCase(action)) {
				// Thực hiện tìm kiếm
				String sonha = request.getParameter("sonha");
				String tenChuHo = request.getParameter("tenChuHo");

				// Gọi Service để tìm kiếm
				list = khoandonggopService.searchKHOANDONGGOP(sonha, tenChuHo);

				if (list == null || list.isEmpty()) {
					request.setAttribute("message", "Không tìm thấy kết quả phù hợp với tiêu chí tìm kiếm.");
				}
			} else {
				// Không phải tìm kiếm, lấy tất cả dữ liệu
				list = khoandonggopService.getAllDetails();

				if (list == null || list.isEmpty()) {
					request.setAttribute("message", "Không có dữ liệu để hiển thị.");
				}
			}

			// Đặt danh sách vào request
			request.setAttribute("khoanDongGopList", list);

			// Chuyển tiếp tới JSP để hiển thị
			request.getRequestDispatcher("WEB-INF/khoandonggop.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi tải dữ liệu khoản đóng góp.");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Lấy action từ request để phân biệt các hành động
			String action = request.getParameter("action");

			if ("updateAllDateColumns".equalsIgnoreCase(action)) {
				// Xử lý cập nhật tất cả các ngày thu
				String newDateStr = request.getParameter("newDate");

				// Kiểm tra giá trị hợp lệ
				if (newDateStr == null || newDateStr.isEmpty()) {
					throw new IllegalArgumentException("Ngày thu không được để trống.");
				}

				java.util.Date utilDate = java.sql.Date.valueOf(newDateStr);
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

				// Gọi Service để cập nhật tất cả ngày thu
				int rowsUpdated = khoandonggopService.updateAllDateColumns(sqlDate);

				System.out.println("Đã cập nhật ngày thu cho " + rowsUpdated + " bản ghi.");

				// Redirect về trang KHOANDONGGOP với thông báo thành công
				response.sendRedirect("KHOANDONGGOP?action=updateSuccess");

			} else if ("updateKHOANDONGGOP".equalsIgnoreCase(action)) {
				// Xử lý cập nhật thông tin khoản đóng góp
				// Lấy CCCD chủ hộ từ form
				String cccdchuho = request.getParameter("cccdchuho");

				// Lấy các giá trị quỹ từ form (nếu không có, đặt mặc định là 0)
				int quyVNN = parseIntOrDefault(request.getParameter("quyVNN"), 0);
				int quyVBD = parseIntOrDefault(request.getParameter("quyVBD"), 0);
				int quyTDP = parseIntOrDefault(request.getParameter("quyTDP"), 0);
				int quyVTT = parseIntOrDefault(request.getParameter("quyVTT"), 0);
				int quyVNDTT = parseIntOrDefault(request.getParameter("quyVNDTT"), 0);
				int quyTN = parseIntOrDefault(request.getParameter("quyTN"), 0);
				int quyKH = parseIntOrDefault(request.getParameter("quyKH"), 0);
				int quyNCT = parseIntOrDefault(request.getParameter("quyNCT"), 0);

				// Tính tổng thu
				int tongthu = quyVNN + quyVBD + quyTDP + quyVTT + quyVNDTT + quyTN + quyKH + quyNCT;

				System.out.println("Tổng thu: " + tongthu);

				// Tạo model KHOANDONGGOP
				KHOANDONGGOP donggop = new KHOANDONGGOP();
				donggop.setCCCDchuho(cccdchuho);
				donggop.setQuyVNN(quyVNN);
				donggop.setQuyVBD(quyVBD);
				donggop.setQuyTDP(quyTDP);
				donggop.setQuyVTT(quyVTT);
				donggop.setQuyVNDTT(quyVNDTT);
				donggop.setQuyTN(quyTN);
				donggop.setQuyKH(quyKH);
				donggop.setQuyNCT(quyNCT);
				donggop.setTongthu(tongthu);

				// Gọi Service để cập nhật khoản đóng góp
				khoandonggopService.updateKHOANDONGGOP(donggop);

				System.out.println("Dữ liệu đã được lưu/cập nhật thành công.");

				// Redirect về trang KHOANDONGGOP (load lại danh sách)
				response.sendRedirect("KHOANDONGGOP");

			} else {
				// Hành động không hợp lệ
				throw new IllegalArgumentException("Action không hợp lệ.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println("Lỗi: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Lỗi khi xử lý dữ liệu khoản đóng góp: " + e.getMessage());
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi xử lý dữ liệu khoản đóng góp.");
		}
	}

	/**
	 * Hàm tiện ích parse số nguyên, nếu lỗi thì trả về defaultValue.
	 */
	private int parseIntOrDefault(String value, int defaultValue) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
}
