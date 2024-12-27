import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.model.NHANKHAU;
import com.example.service.NHANKHAUService;

@WebServlet("/QuanLyCuDan")
public class QuanLyCuDan extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String macanho = request.getParameter("macanho");
        // CANHOService canhoService = new CANHOService();
        NHANKHAUService nhankhauService = new NHANKHAUService();
        List<NHANKHAU> nhankhaus;
        if(macanho == null || macanho.isEmpty()){
            nhankhaus = nhankhauService.getAllNHANKHAU();
        } else {
            nhankhaus = nhankhauService.getAllNHANKHAUMCH(macanho);
        }
        
        if (nhankhaus == null || nhankhaus.isEmpty() || nhankhaus.get(0) == null) {
            request.setAttribute("errorMessage", "Không tìm thấy căn hộ nào phù hợp.");
            request.setAttribute("nhankhaus", null);
        } else {
            request.setAttribute("nhankhaus", nhankhaus);
        }
        
        request.getRequestDispatcher("./WEB-INF/QuanLyCuDan.jsp").forward(request, response);
    }
}