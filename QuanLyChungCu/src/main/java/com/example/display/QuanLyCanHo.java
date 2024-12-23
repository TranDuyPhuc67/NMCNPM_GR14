// ...existing code...
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.model.CANHO;
import com.example.service.CANHOService;

@WebServlet("/QuanLyCanHo")
public class QuanLyCanHo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sonha = request.getParameter("sonha");
        CANHOService canhoService = new CANHOService();
        List<CANHO> canhos;
        if(sonha == null || sonha.isEmpty()){
            canhos = canhoService.getAllCanHos();
        } else {
            canhos = canhoService.getByCondition(sonha);
        }
        
        if (canhos == null || canhos.isEmpty() || canhos.get(0) == null) {
            request.setAttribute("errorMessage", "Không tìm thấy căn hộ nào phù hợp.");
            request.setAttribute("canhos", null);
        } else {
            request.setAttribute("canhos", canhos);
        }
        
        request.getRequestDispatcher("./WEB-INF/QuanLyCanHo.jsp").forward(request, response);
    }
}