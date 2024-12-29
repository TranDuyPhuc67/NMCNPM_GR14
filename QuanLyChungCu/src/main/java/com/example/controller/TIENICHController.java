package com.example.controller;

import java.io.IOException;
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
        } catch (IllegalArgumentException e) {
            handleError(request, response, e, "Dữ liệu không hợp lệ: ");
        } catch (Exception e) {
            handleError(request, response, e, "Lỗi trong quá trình xử lý yêu cầu POST.");
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
                searchTienIch(request, response);
            } else {
                loadTienIchList(request, response);
            }
        } catch (IllegalArgumentException e) {
            handleError(request, response, e, "Dữ liệu không hợp lệ: ");
        } catch (Exception e) {
            handleError(request, response, e, "Đã xảy ra lỗi khi xử lý yêu cầu GET.");
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
            int idcanho = parseInt(request.getParameter("idcanho"));
            String hanthu = request.getParameter("hanthu");

            if (hanthu == null || hanthu.trim().isEmpty()) {
                throw new IllegalArgumentException("Hạn thu không được để trống.");
            }

            boolean success = tienIchService.deleteTienIch(idcanho, hanthu);

            if (success) {
                request.setAttribute("message", "Xóa tiện ích thành công!");
            } else {
                request.setAttribute("message", "Không thể xóa tiện ích. Vui lòng kiểm tra dữ liệu.");
            }
        } catch (Exception e) {
            handleError(request, response, e, "Lỗi khi xóa tiện ích.");
        }
        loadTienIchList(request, response);
    }

    private void loadTienIchList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Lấy giá trị tìm kiếm từ session
            Integer month = (Integer) request.getSession().getAttribute("month");
            Integer year = (Integer) request.getSession().getAttribute("year");

            List<Map<String, Object>> tienIchList;
            if (month != null && year != null) {
                // Tìm kiếm theo điều kiện tháng và năm đã lưu
                tienIchList = tienIchService.searchTienIch(null, null, month, year);
            } else {
                // Lấy toàn bộ danh sách nếu không có điều kiện tìm kiếm
                tienIchList = tienIchService.getAllTienIchWithChuHo();
            }

            if (tienIchList == null || tienIchList.isEmpty()) {
                request.setAttribute("message", "Danh sách tiện ích trống.");
            }
            request.setAttribute("tienIchList", tienIchList);
            request.getRequestDispatcher("WEB-INF/tienich.jsp").forward(request, response);
        } catch (Exception e) {
            handleError(request, response, e, "Đã xảy ra lỗi khi tải danh sách tiện ích.");
        }
    }

    private void searchTienIch(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String sonha = request.getParameter("sonha");
            String tenChuHo = request.getParameter("tenChuHo");
            Integer month = parseNullableInt(request.getParameter("month"));
            Integer year = parseNullableInt(request.getParameter("year"));

            // Lưu điều kiện tìm kiếm vào session
            request.getSession().setAttribute("month", month);
            request.getSession().setAttribute("year", year);

            List<Map<String, Object>> tienIchList = tienIchService.searchTienIch(sonha, tenChuHo, month, year);

            if (tienIchList.isEmpty()) {
                request.setAttribute("message", "Không tìm thấy tiện ích phù hợp.");
            }
            request.setAttribute("tienIchList", tienIchList);
            request.getRequestDispatcher("WEB-INF/tienich.jsp").forward(request, response);
        } catch (Exception e) {
            handleError(request, response, e, "Lỗi khi tìm kiếm tiện ích.");
        }
    }

    private TIENICH parseTienIchFromRequest(HttpServletRequest request) {
        int idcanho = parseInt(request.getParameter("idcanho"));
        int sodien = parseInt(request.getParameter("sodien"));
        int sonuoc = parseInt(request.getParameter("sonuoc"));
        int internet = parseInt(request.getParameter("internet"));
        String hanthu = request.getParameter("hanthu");

        if (hanthu == null || !hanthu.matches("\\d{4}-\\d{2}")) {
            throw new IllegalArgumentException("Hạn thu phải đúng định dạng YYYY-MM.");
        }

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

    private Integer parseNullableInt(String param) {
        if (param == null || param.trim().isEmpty()) {
            return null;
        }
        return parseInt(param);
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, Exception e, String userMessage)
            throws ServletException, IOException {
        e.printStackTrace();
        request.setAttribute("message", userMessage + " " + e.getMessage());
        loadTienIchList(request, response);
    }
}
