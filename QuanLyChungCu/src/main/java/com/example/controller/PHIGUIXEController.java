package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.PHIGUIXE;
import com.example.service.CANHOService;
import com.example.service.PHIGUIXEService;

@WebServlet("/PHIGUIXE")
public class PHIGUIXEController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PHIGUIXEService phiguixeService;
	private CANHOService canhoService;

	@Override
	public void init() throws ServletException {
		phiguixeService = new PHIGUIXEService();
		canhoService = new CANHOService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println("Phương thức GET được gọi. Hành động: " + action);

		if (action == null || action.isEmpty()) {
			action = "list";
		}

		try {
			switch (action) {
			case "list":
				loadList(req, resp);
				break;
			case "search":
				handleSearch(req, resp);
				break;
			case "delete":
				handleDelete(req, resp);
				break;
			default:
				sendError(resp, "Hành động không hợp lệ!");
			}
		} catch (Exception e) {
			handleError(req, resp, e, "Lỗi khi xử lý yêu cầu GET.");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		System.out.println("Phương thức POST được gọi. Hành động: " + action);

		try {
			if ("add".equals(action)) {
				handleAdd(req, resp);
			} else {
				sendError(resp, "Hành động không hợp lệ!");
			}
		} catch (Exception e) {
			handleError(req, resp, e, "Lỗi khi xử lý yêu cầu POST.");
		}
	}

	private void loadList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Phương thức loadList được gọi.");

		Integer month = (Integer) req.getSession().getAttribute("searchMonth");
		Integer year = (Integer) req.getSession().getAttribute("searchYear");

		if (month == null || year == null) {
			req.setAttribute("message", "Vui lòng nhập tháng và năm để hiển thị danh sách.");
			forwardToPage(req, resp, "WEB-INF/phiguixe.jsp");
			return;
		}

		ArrayList<PHIGUIXE> phiguixeList = phiguixeService.getPHIGUIXEByMonthAndYear(month, year);
		ArrayList<Map<String, Object>> chuHoList = canhoService.getAllChuHoWithCanHo();

		List<Map<String, Object>> combinedList = combineData(phiguixeList, chuHoList);

		req.setAttribute("combinedList", combinedList);
		forwardToPage(req, resp, "WEB-INF/phiguixe.jsp");
	}

	private void handleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Phương thức handleAdd được gọi.");

		// Lấy giá trị từ form
		String hanthu = req.getParameter("hanthu");
		String tienxemayStr = req.getParameter("tienxemay");
		String tienxeotoStr = req.getParameter("tienxeoto");

		// Kiểm tra dữ liệu nhập vào
		if (hanthu == null || !hanthu.matches("\\d{4}-\\d{2}")) {
			req.setAttribute("message", "Định dạng tháng năm không hợp lệ. Vui lòng nhập theo định dạng YYYY-MM.");
			forwardToPage(req, resp, "WEB-INF/phiguixe.jsp");
			return;
		}

		if (tienxemayStr == null || tienxeotoStr == null || tienxemayStr.isEmpty() || tienxeotoStr.isEmpty()) {
			req.setAttribute("message", "Vui lòng nhập đầy đủ phí xe máy và ô tô.");
			forwardToPage(req, resp, "WEB-INF/phiguixe.jsp");
			return;
		}

		try {
			// Chuyển đổi giá trị phí xe máy và ô tô sang số nguyên
			int tienxemay = Integer.parseInt(tienxemayStr);
			int tienxeoto = Integer.parseInt(tienxeotoStr);

			// Gọi service để áp dụng phí gửi xe
			int affectedRows = phiguixeService.applyFeeForAll(tienxemay, tienxeoto, hanthu);

			// Lấy tháng và năm từ session để duy trì trạng thái tìm kiếm
			Integer month = (Integer) req.getSession().getAttribute("searchMonth");
			Integer year = (Integer) req.getSession().getAttribute("searchYear");

			// Nếu không có tháng và năm trong session, phân tích từ `hanthu`
			if (month == null || year == null) {
				String[] hanthuParts = hanthu.split("-");
				year = Integer.parseInt(hanthuParts[0]);
				month = Integer.parseInt(hanthuParts[1]);
				req.getSession().setAttribute("searchMonth", month);
				req.getSession().setAttribute("searchYear", year);
			}

			// Thông báo kết quả
			req.setAttribute("message", "Áp dụng phí thành công cho " + affectedRows + " căn hộ.");

			// Tiếp tục tìm kiếm với tháng và năm đã cập nhật
			handleSearch(req, resp);

		} catch (NumberFormatException e) {
			req.setAttribute("message", "Phí xe máy và ô tô phải là số hợp lệ.");
			forwardToPage(req, resp, "WEB-INF/phiguixe.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("message", "Đã xảy ra lỗi trong quá trình xử lý.");
			forwardToPage(req, resp, "WEB-INF/phiguixe.jsp");
		}
	}

	private void handleSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Phương thức handleSearch được gọi.");

		// Lấy tham số từ request
		String tenChuHo = req.getParameter("tenChuHo");
		String sonha = req.getParameter("sonha");
		String monthStr = req.getParameter("month");
		String yearStr = req.getParameter("year");

		// Chuyển đổi tham số
		Integer month = (monthStr != null && !monthStr.trim().isEmpty()) ? Integer.parseInt(monthStr) : null;
		Integer year = (yearStr != null && !yearStr.trim().isEmpty()) ? Integer.parseInt(yearStr) : null;

		// Lưu tháng và năm vào session để sử dụng sau
		req.getSession().setAttribute("searchMonth", month);
		req.getSession().setAttribute("searchYear", year);

		// Gọi service tìm kiếm
		ArrayList<Map<String, Object>> searchResults = phiguixeService.searchPHIGUIXE(tenChuHo, sonha, month, year);

		// Thiết lập kết quả tìm kiếm vào request
		req.setAttribute("combinedList", searchResults);
		forwardToPage(req, resp, "WEB-INF/phiguixe.jsp");
	}

	private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Phương thức handleDelete được gọi.");

		// Lấy tháng và năm từ session
		Integer month = (Integer) req.getSession().getAttribute("searchMonth");
		Integer year = (Integer) req.getSession().getAttribute("searchYear");

		if (month == null || year == null) {
			req.setAttribute("message", "Vui lòng nhập tháng và năm trước khi xóa.");
			forwardToPage(req, resp, "WEB-INF/phiguixe.jsp");
			return;
		}

		try {
			// Lấy tham số idCanHo và thực hiện xóa
			String idCanHoStr = req.getParameter("idCanHo");
			String hanthu = year + "-" + String.format("%02d", month);

			if (idCanHoStr == null) {
				req.setAttribute("message", "IdCanHo không được để trống.");
				loadList(req, resp);
				return;
			}

			int idCanHo = Integer.parseInt(idCanHoStr);
			boolean isDeleted = phiguixeService.deletePHIGUIXE(idCanHo, hanthu);

			req.setAttribute("message", isDeleted ? "Xóa phí thành công!" : "Không tìm thấy phí để xóa.");

			// Sau khi xóa, tiếp tục tìm kiếm với tháng và năm
			handleSearch(req, resp);
		} catch (NumberFormatException e) {
			req.setAttribute("message", "IdCanHo phải là số hợp lệ.");
			loadList(req, resp);
		}
	}

	private List<Map<String, Object>> combineData(ArrayList<PHIGUIXE> phiguixeList,
			ArrayList<Map<String, Object>> chuHoList) {
		List<Map<String, Object>> combinedList = new ArrayList<>();

		for (PHIGUIXE phiguixe : phiguixeList) {
			Map<String, Object> combinedItem = new HashMap<>();
			combinedItem.put("idcanho", phiguixe.getIdcanho());
			combinedItem.put("tienxemay", phiguixe.getTienxemay());
			combinedItem.put("tienxeoto", phiguixe.getTienxeoto());
			combinedItem.put("tongguixe", phiguixe.getTongguixe());
			combinedItem.put("hanthu", phiguixe.getHanthu());

			boolean foundChuHo = false;

			for (Map<String, Object> chuHo : chuHoList) {
				if (chuHo.get("idcanho").equals(phiguixe.getIdcanho())) {
					combinedItem.put("hotenchuho", chuHo.get("hotenchuho"));
					combinedItem.put("sonha", chuHo.get("sonha"));
					combinedItem.put("soxemay", chuHo.get("soxemay"));
					combinedItem.put("sooto", chuHo.get("sooto"));
					foundChuHo = true;
					break;
				}
			}

			if (!foundChuHo) {
				combinedItem.put("hotenchuho", "Không xác định");
				combinedItem.put("sonha", "Không xác định");
				combinedItem.put("soxemay", 0);
				combinedItem.put("sooto", 0);
			}

			combinedList.add(combinedItem);
		}

		return combinedList;
	}

	private void forwardToPage(HttpServletRequest req, HttpServletResponse resp, String page)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher(page);
		dispatcher.forward(req, resp);
	}

	private void handleError(HttpServletRequest req, HttpServletResponse resp, Exception e, String message)
			throws ServletException, IOException {
		req.setAttribute("errorMessage", message + e.getMessage());
		forwardToPage(req, resp, "error.jsp");
	}

	private void sendError(HttpServletResponse resp, String message) throws IOException {
		resp.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
	}
}
