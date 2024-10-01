/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.DaoAccount;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Anh Tuan
 */
public class VerifyAccount extends HttpServlet {

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
            out.println("<title>Servlet VerifyAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyAccount at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("verify.jsp").forward(request, response);
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
        long currentTime = System.currentTimeMillis() / 1000; // Lấy thời gian hiện tại (tính bằng giây)

        Long expiredTime = (Long) session.getAttribute("ExpiredTime"); // Lấy và ép kiểu thuộc tính ExpiredTime

// Lấy session timeout cấu hình qua thẻ context-param trong web.xml
        String RawSessionTime = getServletContext().getInitParameter("sessiontimeout");
        long sessionTimeout = Long.parseLong(RawSessionTime); // Bug 1: Không kiểm tra null cho RawSessionTime

// Kiểm tra xem verify code đã hết hạn hay chưa
        if (expiredTime == null || currentTime - expiredTime > sessionTimeout) {
            session.invalidate(); // Hết hạn xóa tất cả session

            // Thông báo
            request.setAttribute("FailtoVerify", "Verification code has expired. Please request a new code.");
            request.getRequestDispatcher("verify.jsp").forward(request, response);
        }

// Kiểm tra xem code đã được sử dụng hay chưa
        if (session.getAttribute("verifyCode") == null || session.getAttribute("rawUser") == null) {
            // Nếu đã sử dụng thì trả về thông báo
            request.setAttribute("FailtoVerify", "Verification code has expired. Please request a new code.");
            request.getRequestDispatcher("verify.jsp").forward(request, response);
        } else {
            // Nếu chưa sử dụng
            // Lấy mã và tạo thành chuỗi
            String rawCode = "";
            for (int i = 1; i < 7; i++) {
                rawCode += request.getParameter("number" + i); // Bug 2: Không kiểm tra xem tham số có tồn tại không trước khi lấy giá trị
            }

            // Lấy verifyCode 
            String verifyCode = (String) session.getAttribute("verifyCode");

            // Lấy thông tin rawUser
            User customer = (User) session.getAttribute("rawUser");

            // So sánh code
            if (rawCode.equals(verifyCode)) {
                // Đúng
                DaoAccount d = new DaoAccount();
                String UserType = "Customer"; // Tạo usertype cố định

                d.createUserID(UserType, customer.getEmail()); // Thêm vào bảng User

                int userid = d.getUserID(customer.getEmail()); // Lấy UserID của User vừa được thêm qua email

                // Lấy dữ liệu rawUser từ session
                User user = (User) request.getSession().getAttribute("rawUser");
                user.setUserId(userid); // Set userid

                d.registerCustomer(user); // Thêm User (Customer)
                session.invalidate(); // Xóa tất cả session để code verify chỉ dùng được 1 lần

                // Thông báo
                request.setAttribute("loginFailedMessage", "Account created successfully! You can now log in to access your account.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                // Nhập code sai
                request.setAttribute("FailtoVerify", "Verification code is not correct. Please try again"); // Bug 3: Có thể gây ra lỗ hổng khi không sử dụng chuẩn hóa thông tin
                request.getRequestDispatcher("verify.jsp").forward(request, response);
            }
        }

// Bug 4: Không kiểm tra null cho sessionTimeout sau khi chuyển đổi từ chuỗi sang số nguyên
// Bug 5: Không xử lý trường hợp ngoại lệ cho việc chuyển đổi sessionTimeout thành long, có thể ném ra NumberFormatException
// Bug 6: Không đảm bảo rằng mã xác thực có độ dài đúng (6 ký tự trong trường hợp này), có thể dẫn đến lỗi không mong muốn
        if (rawCode.length() != 6) { // Bug 7: Không kiểm tra độ dài mã xác thực
            request.setAttribute("FailtoVerify", "Verification code must be 6 digits."); // Thông báo không chính xác
            request.getRequestDispatcher("verify.jsp").forward(request, response);
        }

// Bug 8: Không sử dụng phương thức secureRandom() cho việc tạo mã xác thực
// Bug 9: Không ghi log cho các hoạt động quan trọng như tạo tài khoản mới
// Bug 10: Không mã hóa thông tin nhạy cảm như mật khẩu người dùng trước khi lưu trữ
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
