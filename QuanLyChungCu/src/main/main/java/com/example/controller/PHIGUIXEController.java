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
            forwardToPage(req, resp, "phiguixe.jsp");
            return;
        }

        ArrayList<PHIGUIXE> phiguixeList = phiguixeService.getPHIGUIXEByMonthAndYear(month, year);
        ArrayList<Map<String, Object>> chuHoList = canhoService.getAllChuHoWithCanHo();

        List<Map<String, Object>> combinedList = combineData(phiguixeList, chuHoList);

        req.setAttribute("combinedList", combinedList);
        forwardToPage(req, resp, "phiguixe.jsp");
    }

    private void handleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Phương thức handleAdd được gọi.");

        Integer month = (Integer) req.getSession().getAttribute("searchMonth");
        Integer year = (Integer) req.getSession().getAttribute("searchYear");

        if (month == null || year == null) {
            req.setAttribute("message", "Vui lòng nhập tháng và năm trước khi thêm.");
            forwardToPage(req, resp, "phiguixe.jsp");
            return;
        }

        String tienxemayStr = req.getParameter("tienxemay");
        String tienxeotoStr = req.getParameter("tienxeoto");

        if (tienxemayStr == null || tienxeotoStr == null) {
            throw new IllegalArgumentException("Thông tin không đầy đủ.");
        }

        int tienxemay = Integer.parseInt(tienxemayStr);
        int tienxeoto = Integer.parseInt(tienxeotoStr);
        String hanthu = year + "-" + String.format("%02d", month);

        int affectedRows = phiguixeService.applyFeeForAll(tienxemay, tienxeoto, hanthu);

        req.setAttribute("message", "Áp dụng phí thành công cho " + affectedRows + " căn hộ.");
        loadList(req, resp);
    }

    private void handleSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Phương thức handleSearch được gọi.");

        String tenChuHo = req.getParameter("tenChuHo");
        String sonha = req.getParameter("sonha");
        String monthStr = req.getParameter("month");
        String yearStr = req.getParameter("year");

        Integer month = (monthStr != null && !monthStr.trim().isEmpty()) ? Integer.parseInt(monthStr) : null;
        Integer year = (yearStr != null && !yearStr.trim().isEmpty()) ? Integer.parseInt(yearStr) : null;

        req.getSession().setAttribute("searchMonth", month);
        req.getSession().setAttribute("searchYear", year);

        ArrayList<Map<String, Object>> searchResults = phiguixeService.searchPHIGUIXE(tenChuHo, sonha, month, year);

        req.setAttribute("combinedList", searchResults);
        forwardToPage(req, resp, "phiguixe.jsp");
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Phương thức handleDelete được gọi.");

        Integer month = (Integer) req.getSession().getAttribute("searchMonth");
        Integer year = (Integer) req.getSession().getAttribute("searchYear");

        if (month == null || year == null) {
            req.setAttribute("message", "Vui lòng nhập tháng và năm trước khi xóa.");
            forwardToPage(req, resp, "phiguixe.jsp");
            return;
        }

        try {
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
            loadList(req, resp);
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
