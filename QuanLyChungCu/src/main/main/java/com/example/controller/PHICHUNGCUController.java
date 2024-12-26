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

import com.example.model.PHICHUNGCU;
import com.example.service.CANHOService;
import com.example.service.PHICHUNGCUService;

@WebServlet("/PHICHUNGCU")
public class PHICHUNGCUController extends HttpServlet {

	private static final long serialVersionUID = 3990255580285744744L;
	private PHICHUNGCUService phichungcuService;
	private CANHOService canhoService;

	@Override
	public void init() throws ServletException {
		phichungcuService = new PHICHUNGCUService();
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

	/**
	 * Xử lý tải danh sách phí chung cư.
	 */
	private void loadList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Phương thức loadList được gọi.");

		ArrayList<PHICHUNGCU> phichungList = phichungcuService.getAllPHICHUNGCU();
		ArrayList<Map<String, Object>> chuHoList = canhoService.getAllChuHoWithCanHo();
		List<Map<String, Object>> combinedList = combineData(phichungList, chuHoList);

		req.setAttribute("combinedList", combinedList);
		forwardToPage(req, resp, "phichungcu.jsp");
	}

	/**
	 * Xử lý thêm phí cho tất cả căn hộ.
	 */
	private void handleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Phương thức handleAdd được gọi.");

		String phichungcum2Str = req.getParameter("phichungcum2");
		String phiquanlym2Str = req.getParameter("phiquanlym2");
		String hanthu = req.getParameter("hanthu");

		if (phichungcum2Str == null || phiquanlym2Str == null || hanthu == null) {
			throw new IllegalArgumentException("Thông tin không đầy đủ.");
		}

		int phichungcum2 = Integer.parseInt(phichungcum2Str);
		int phiquanlym2 = Integer.parseInt(phiquanlym2Str);

		int affectedRows = phichungcuService.applyFeeForAll(phichungcum2, phiquanlym2, hanthu);

		req.setAttribute("message", "Áp dụng phí thành công cho " + affectedRows + " căn hộ.");
		loadList(req, resp);
	}

	/**
	 * Xử lý tìm kiếm phí chung cư.
	 */
	private void handleSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Phương thức handleSearch được gọi.");

		String tenChuHo = req.getParameter("tenChuHo");
		String sonha = req.getParameter("sonha");
		String monthStr = req.getParameter("month");
		String yearStr = req.getParameter("year");

		Integer month = (monthStr != null && !monthStr.trim().isEmpty()) ? Integer.parseInt(monthStr) : null;
		Integer year = (yearStr != null && !yearStr.trim().isEmpty()) ? Integer.parseInt(yearStr) : null;

		// Gọi phương thức tìm kiếm trong Service
		ArrayList<Map<String, Object>> searchResults = phichungcuService.searchPHICHUNGCU(tenChuHo, sonha, month, year);
		System.out.println("Kết quả từ searchPHICHUNGCU: " + searchResults);

		// Lấy danh sách chủ hộ
		ArrayList<Map<String, Object>> chuHoList = canhoService.getAllChuHoWithCanHo();
		System.out.println("Danh sách chủ hộ từ CANHOService: " + chuHoList);

		// Kết hợp dữ liệu từ searchResults và chuHoList
		for (Map<String, Object> result : searchResults) {
			boolean foundChuHo = false;
			for (Map<String, Object> chuHo : chuHoList) {
				if (chuHo.get("idcanho").equals(result.get("idcanho"))) {
					result.put("hotenchuho", chuHo.get("hotenchuho"));
					result.put("sonha", chuHo.get("sonha"));
					result.put("dientich", chuHo.get("dientich"));
					foundChuHo = true;
					break;
				}
			}

			if (!foundChuHo) {
				result.put("hotenchuho", "Không xác định");
			}
		}

		System.out.println("Kết quả sau khi kết hợp: " + searchResults);

		// Gửi kết quả về giao diện
		req.setAttribute("combinedList", searchResults);
		forwardToPage(req, resp, "phichungcu.jsp");
	}

	/**
	 * Xử lý xóa phí chung cư.
	 */
	private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Phương thức handleDelete được gọi.");

		try {
			String idCanHoStr = req.getParameter("idCanHo");
			String hanthu = req.getParameter("hanthu");

			if (idCanHoStr == null || idCanHoStr.trim().isEmpty() || hanthu == null || hanthu.trim().isEmpty()) {
				req.setAttribute("message", "IdCanHo hoặc HanThu không được để trống.");
				loadList(req, resp);
				return;
			}

			int idCanHo = Integer.parseInt(idCanHoStr);

			boolean isDeleted = phichungcuService.deletePHICHUNGCU(idCanHo, hanthu);

			req.setAttribute("message", isDeleted ? "Xóa phí thành công!" : "Không tìm thấy phí để xóa.");
			loadList(req, resp);
		} catch (NumberFormatException e) {
			System.err.println("Lỗi chuyển đổi IdCanHo: " + e.getMessage());
			req.setAttribute("message", "IdCanHo phải là một số hợp lệ.");
			loadList(req, resp);
		} catch (Exception e) {
			System.err.println("Lỗi khi xử lý xóa: " + e.getMessage());
			req.setAttribute("message", "Đã xảy ra lỗi khi xóa: " + e.getMessage());
			loadList(req, resp);
		}
	}

	private List<Map<String, Object>> combineData(ArrayList<PHICHUNGCU> phichungList,
			ArrayList<Map<String, Object>> chuHoList) {
		System.out.println("Kết hợp dữ liệu từ phichungList và chuHoList.");

		List<Map<String, Object>> combinedList = new ArrayList<>();
		for (PHICHUNGCU phichung : phichungList) {
			Map<String, Object> combinedItem = new HashMap<>();
			combinedItem.put("idcanho", phichung.getIdcanho());
			combinedItem.put("phidichvu", phichung.getPhidichvu());
			combinedItem.put("phiquanly", phichung.getPhiquanly());
			combinedItem.put("tongphichungcu", phichung.getTongphichungcu());
			combinedItem.put("hanthu", phichung.getHanthu());

			boolean foundChuHo = false;
			for (Map<String, Object> chuHo : chuHoList) {
				if (Integer.valueOf(chuHo.get("idcanho").toString()).equals(phichung.getIdcanho())) {
					combinedItem.put("hotenchuho", chuHo.get("hotenchuho"));
					combinedItem.put("sonha", chuHo.get("sonha"));
					combinedItem.put("dientich", chuHo.get("dientich"));
					foundChuHo = true;
					break;
				}
			}

			if (!foundChuHo) {
				combinedItem.put("hotenchuho", "Không xác định");
			}

			combinedList.add(combinedItem);
		}

		System.out.println("Danh sách kết hợp: " + combinedList);
		return combinedList;
	}

	private void forwardToPage(HttpServletRequest req, HttpServletResponse resp, String page)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher(page);
		dispatcher.forward(req, resp);
	}

	private void handleError(HttpServletRequest req, HttpServletResponse resp, Exception e, String message)
			throws ServletException, IOException {
		System.err.println(message + e.getMessage());
		e.printStackTrace();
		req.setAttribute("errorMessage", message + e.getMessage());
		forwardToPage(req, resp, "error.jsp");
	}

	private void sendError(HttpServletResponse resp, String message) throws IOException {
		resp.sendError(HttpServletResponse.SC_BAD_REQUEST, message);
	}
}
