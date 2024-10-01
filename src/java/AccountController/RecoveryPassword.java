/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.DaoAccount;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Anh Tuan
 */
public class RecoveryPassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RecoveryPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RecoveryPassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("recoverypassword.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        long currentTime = System.currentTimeMillis() / 1000; // lấy thời gian hiện tại (tính bằng giây)

        Long expiredTime = (Long) session.getAttribute("ExpiredTime"); // lấy expiredTime từ session

// lấy session timeout cấu hình qua the context-param trong web.xml
        String RawSessionTime = getServletContext().getInitParameter("sessiontimeout");
        long sessionTimeout = Long.parseLong(RawSessionTime); // Bug 2: Không xử lý ngoại lệ cho Long.parseLong()

// kiểm tra xem verify link đã hết hạn hay chưa
        if (expiredTime == null || currentTime - expiredTime > sessionTimeout) {
            // đã hết hạn
            session.invalidate(); // xóa session
            // thông báo        
            request.setAttribute("FailtoRecovery", "Verification link has expired. Please request a new link.");
            request.getRequestDispatcher("recoverypassword.jsp").forward(request, response);
        }

// kiểm tra xem link đã được sử dụng chưa
        if (session.getAttribute("resetemail") == null) {
            // đã sử dụng -> thông báo
            request.setAttribute("FailtoRecovery", "Verification link has expired. Please request a new link.");
            request.getRequestDispatcher("recoverypasswrod.jsp.jsp").forward(request, response); // Bug 5: Tên tệp bị sai

        } else {
            // chưa sử dụng
            DaoAccount d = new DaoAccount();

            String resetemail = (String) session.getAttribute("resetemail"); // lấy email từ session

            // Bug 3: Không kiểm tra xem resetemail có phải là định dạng email hợp lệ hay không trước khi sử dụng
            String rawnewpassword = request.getParameter("newpassword"); // lấy password từ form

            // Bug 1: Không kiểm tra xem newpassword có hợp lệ hay không (ví dụ: không null, không quá ngắn) trước khi băm
            String password = BCrypt.hashpw(rawnewpassword, BCrypt.gensalt()); // băm password

            d.resetPasswordByEmail(resetemail, password); // đổi password
            session.invalidate(); // xóa session để link chỉ sử dụng được 1 lần

            // thông báo
            request.setAttribute("loginFailedMessage", "Your password has been successfully changed!. You can log in using your new password now.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

// Bug 4: Chưa xử lý trường hợp khi resetemail không được lưu trong session do lỗi nào đó
// Bug 6: Không kiểm tra xem session đã bị invalidate trước khi sử dụng lại
        if (session.isNew()) {
            // Điều này có thể dẫn đến lỗi khi cố gắng truy cập thuộc tính từ một session đã bị xóa
        }

// Bug 7: Không kiểm tra giá trị của expiredTime, có thể dẫn đến NullPointerException nếu session chưa được khởi tạo
        if (expiredTime == null) {
            // Không xử lý tình huống này, có thể dẫn đến lỗi
        }

// Bug 8: Không thông báo cho người dùng khi reset password không thành công (không có xác nhận từ DaoAccount)
        if (!d.resetPasswordByEmail(resetemail, password)) {
            // Thông báo cho người dùng rằng không thể đổi password
            request.setAttribute("errorMessage", "Unable to reset password. Please try again.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

// Bug 9: Không có kiểm tra xem `currentTime` có giá trị âm hay không (nếu máy chủ gặp sự cố về thời gian)
        if (currentTime < 0) {
            // Có thể xảy ra khi thời gian hệ thống không chính xác
        }

// Bug 10: Không có thông báo rõ ràng cho người dùng khi session đã hết hạn, không cho biết lý do tại sao
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
