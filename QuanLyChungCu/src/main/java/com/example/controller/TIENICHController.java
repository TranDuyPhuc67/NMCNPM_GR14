package com.example.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.TIENICH;
import com.example.service.TIENICHService;

@WebServlet("/TIENICH")
public class TIENICHController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TIENICHService tienIchService;

	@Override
	public void init() throws ServletException {
		tienIchService = new TIENICHService();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		try {
			if ("add".equals(action)) {
				addTienIch(request, response);
			} else if ("update".equals(action)) {
				updateTienIch(request, response);
			} else {
				throw new IllegalArgumentException("Yêu cầu không hợp lệ.");
			}
		} catch (Exception e) {
			handleError(request, response, e, "Đã xảy ra lỗi trong quá trình xử lý yêu cầu POST.");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String action = request.getParameter("action");

	    try {
	        if ("delete".equals(action)) {
	            deleteTienIch(request, response);
	        } else if ("search".equals(action)) {
	            searchTienIch(request, response); // Gọi phương thức tìm kiếm
	        } else {
	            loadTienIchList(request, response); // Mặc định tải danh sách tiện ích
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("message", "Đã xảy ra lỗi khi xử lý yêu cầu.");
	        request.getRequestDispatcher("tienich.jsp").forward(request, response);
	    }
	}


	private void addTienIch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TIENICH tienIch = parseTienIchFromRequest(request);
		int result = tienIchService.addTienIch(tienIch);

		if (result > 0) {
			request.setAttribute("message", "Thêm tiện ích thành công!");
		} else {
			request.setAttribute("message", "Không thể thêm tiện ích. Vui lòng kiểm tra dữ liệu.");
		}
		loadTienIchList(request, response);
	}

	private void updateTienIch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TIENICH tienIch = parseTienIchFromRequest(request);
		int result = tienIchService.updateTienIch(tienIch);

		if (result > 0) {
			request.setAttribute("message", "Cập nhật tiện ích thành công!");
		} else {
			request.setAttribute("message", "Không thể cập nhật tiện ích. Vui lòng kiểm tra dữ liệu.");
		}
		loadTienIchList(request, response);
	}

	private void deleteTienIch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String idcanhoStr = request.getParameter("idcanho");
			String hanthuStr = request.getParameter("hanthu");

			// Debugging
			System.out.println("DEBUG - idcanho: " + idcanhoStr);
			System.out.println("DEBUG - hanthu: " + hanthuStr);

			if (idcanhoStr == null || idcanhoStr.isEmpty() || hanthuStr == null || hanthuStr.isEmpty()) {
				throw new IllegalArgumentException("Thiếu tham số idcanho hoặc hanthu.");
			}

			int idcanho = Integer.parseInt(idcanhoStr);
			Date hanthu = Date.valueOf(hanthuStr);

			boolean success = tienIchService.deleteTienIch(idcanho, hanthu);

			if (success) {
				request.setAttribute("message", "Xóa tiện ích thành công!");
			} else {
				request.setAttribute("message", "Không thể xóa tiện ích. Vui lòng kiểm tra dữ liệu.");
			}
		} catch (IllegalArgumentException e) {
			request.setAttribute("message", e.getMessage());
		} catch (Exception e) {
			request.setAttribute("message", "Đã xảy ra lỗi không mong muốn.");
			e.printStackTrace();
		}
		loadTienIchList(request, response);
	}

	private void loadTienIchList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Map<String, Object>> tienIchList = tienIchService.getAllTienIchWithChuHo();

			if (tienIchList == null || tienIchList.isEmpty()) {
				System.out.println("DEBUG - Danh sách tiện ích trống.");
				request.setAttribute("message", "Danh sách tiện ích trống.");
			} else {
				System.out.println("DEBUG - Dữ liệu tiện ích từ cơ sở dữ liệu:");
				for (Map<String, Object> item : tienIchList) {
					System.out.println("Idcanho: " + item.get("Idcanho") + ", Hanthu: " + item.get("Hanthu"));
				}
			}

			request.setAttribute("tienIchList", tienIchList);
			request.getRequestDispatcher("tienich.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "Đã xảy ra lỗi khi tải danh sách tiện ích.");
			request.getRequestDispatcher("tienich.jsp").forward(request, response);
		}
	}

	private void handleError(HttpServletRequest request, HttpServletResponse response, Exception e, String userMessage)
			throws ServletException, IOException {
		e.printStackTrace();
		request.setAttribute("message", userMessage + " Chi tiết: " + e.getMessage());
		loadTienIchList(request, response);
	}
	
	private void searchTienIch(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	        String sonha = request.getParameter("sonha");
	        String tenChuHo = request.getParameter("tenChuHo");
	        String hanthuStr = request.getParameter("hanthu");
	        String monthStr = request.getParameter("month");
	        String yearStr = request.getParameter("year");

	        Integer day = null, month = null, year = null;

	        // Ưu tiên xử lý hanthu (ngày-tháng-năm)
	        if (hanthuStr != null && !hanthuStr.trim().isEmpty()) {
	            String[] parts = hanthuStr.split("-");
	            if (parts.length == 3) {
	                year = Integer.valueOf(parts[0]);
	                month = Integer.valueOf(parts[1]);
	                day = Integer.valueOf(parts[2]);
	            }
	        } else {
	            // Xử lý tháng/năm riêng lẻ
	            if (monthStr != null && !monthStr.trim().isEmpty()) {
	                month = Integer.valueOf(monthStr);
	            }
	            if (yearStr != null && !yearStr.trim().isEmpty()) {
	                year = Integer.valueOf(yearStr);
	            }
	        }

	        // Gọi service để tìm kiếm
	        List<Map<String, Object>> tienIchList = tienIchService.searchTienIch(sonha, tenChuHo, day, month, year);

	        if (tienIchList.isEmpty()) {
	            request.setAttribute("message", "Không tìm thấy tiện ích phù hợp.");
	        }
	        request.setAttribute("tienIchList", tienIchList);
	        request.getRequestDispatcher("tienich.jsp").forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("message", "Đã xảy ra lỗi khi tìm kiếm.");
	        loadTienIchList(request, response);
	    }
	}


	private TIENICH parseTienIchFromRequest(HttpServletRequest request) {
		int idcanho = parseInt(request.getParameter("idcanho"));
		int sodien = parseInt(request.getParameter("sodien"));
		int sonuoc = parseInt(request.getParameter("sonuoc"));
		int internet = parseInt(request.getParameter("internet"));
		Date hanthu = parseDate(request.getParameter("hanthu"));

		if (sodien < 0 || sonuoc < 0 || internet < 0) {
			throw new IllegalArgumentException("Số điện, nước, và internet không được âm.");
		}

		return new TIENICH(idcanho, sodien, sonuoc, internet, hanthu);
	}

	private int parseInt(String param) {
		try {
			return Integer.parseInt(param);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Giá trị " + param + " không hợp lệ.");
		}
	}

	private Date parseDate(String param) {
		try {
			return Date.valueOf(param);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Định dạng ngày không hợp lệ: " + param);
		}
	}
}
