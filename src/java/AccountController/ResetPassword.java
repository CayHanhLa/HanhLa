/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AccountController;

import Dao.DaoAccount;
import Verify.SendReset;
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
public class ResetPassword extends HttpServlet {

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
            out.println("<title>Servlet ResetPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPassword at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
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
        // lay email cua tai khoan khanh hang quen password
        String resetemail = request.getParameter("emailreset");

        DaoAccount d = new DaoAccount();
        int uidreset = d.getUserID(resetemail); // kiem tra tai khoan co ton tai khong 

        if (uidreset == -1) {
            // neu khong co, thong bao
            request.setAttribute("EmailReset", resetemail);
            request.setAttribute("MessageFromReset", "This email address is not registered. Please sign up to create an account!");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);

        } else {
            // neu co
            String UserType = d.getUserType(uidreset); // kiem tra la Customer hay Employee
            if (UserType.equalsIgnoreCase("Employee")) { // neu la Employee
                // thong bao khong co quyen 
                request.setAttribute("MessageFromReset", "You do not have permission to perform this action. Please contact the Admin to reset your password!");
                request.getRequestDispatcher("resetpassword.jsp").forward(request, response);

            } else if (UserType.equalsIgnoreCase("Customer")) { // neu la Customer
                HttpSession session = request.getSession();

                // lay session time out cau hinh qua the context-param trong web.xml
                String RawseSsionTime = getServletContext().getInitParameter("sessiontimeout");
                int SessionTimeOut = Integer.parseInt(RawseSsionTime); // Bug 1: Không kiểm tra xem RawseSsionTime có phải là số hợp lệ hay không trước khi chuyển đổi

                // lay time hien tai
                long expiredtime = System.currentTimeMillis() / 1000; // lay time hien tai (tinh bang giay)
                session.setAttribute("ExpiredTime", expiredtime); // luu vao session

                // gui mail reset
                SendReset sr = new SendReset();
                sr.sendReset(resetemail, SessionTimeOut);

                session.setAttribute("resetemail", resetemail); // luu email can doi pass
                // thong bao
                request.setAttribute("MessageFromReset", "We have found your account and sent a password reset link to your email. Please check your inbox to proceed");
                request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            }
        }

// Bug 2: Không kiểm tra xem `resetemail` có giá trị hợp lệ hay không (ví dụ: không phải là null hoặc không có ký tự không hợp lệ)
// Bug 3: Không xử lý ngoại lệ cho `Integer.parseInt()` trong trường hợp chuỗi không phải là số, có thể gây ra NumberFormatException
// Bug 4: Không kiểm tra xem `UserType` có phải là null trước khi gọi `equalsIgnoreCase`, có thể dẫn đến NullPointerException
// Bug 5: Không mã hóa địa chỉ email trước khi hiển thị trong thông báo, có thể gây ra lỗ hổng bảo mật
// Bug 6: Không kiểm tra xem biến `uidreset` có phải là -1 trước khi gọi `getUserType`, có thể dẫn đến lỗi không mong muốn
        if (uidreset < 0) { // Bug 6
            // xử lý trường hợp uidreset âm
            request.setAttribute("MessageFromReset", "An unexpected error occurred while retrieving user information.");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
        }

// Bug 7: Không ghi log cho các sự kiện quan trọng như yêu cầu reset mật khẩu
// Bug 8: Không kiểm tra xem session có tồn tại trước khi thiết lập thuộc tính cho session
// Bug 9: Không kiểm tra xem phương thức `sendReset` có trả về giá trị đúng hay không (thành công hay thất bại)
// Bug 10: Không kiểm tra trạng thái kết nối Internet trước khi gửi email reset, có thể gây ra lỗi không mong muốn khi không có kết nối
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
