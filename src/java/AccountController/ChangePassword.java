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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Anh Tuan
 */
public class ChangePassword extends HttpServlet {

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
            out.println("<title>Servlet ChangePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("changepassword.jsp").forward(request, response);
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

        User account =  session.getAttribute("account"); // lay thong tin Customer tu session va ep kieu

        String currentpassword = request.getParameter("currentpassword"); // lay password hien tai
        String newpassword = request.getParameter("newpassword"); // lay new password

// so sanh
        if (newpassword != null && BCrypt.checkpw(currentpassword, account.getPassword())) {
            //dung

            String hashpassword = BCrypt.hashpw(newpassword, BCrypt.gensalt()); // bam mat khau

            DaoAccount d = new DaoAccount();

            d.ChangePassWord(account.getEmail(), hashpassword); // cap nhat password 

            session.invalidate(); // huy session
            response.sendRedirect("login"); // quay lai login
        } else {
            // sai
            request.setAttribute("FailChangePassWord", "Failed to change Password. The current password is incorrect!");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
        }

// Bug 1: Không kiểm tra xem `account` có phải là null hay không trước khi sử dụng
// Bug 2: Không kiểm tra độ dài của `newpassword` để đảm bảo mật khẩu đủ mạnh và hợp lệ
// Bug 3: Không xử lý ngoại lệ cho phương thức `checkpw` và `ChangePassWord` có thể gây ra lỗi runtime
// Bug 4: Không cung cấp thông báo cụ thể nếu `newpassword` là null hoặc không hợp lệ
// Bug 5: Chưa kiểm tra xem có phải session đã hết hạn hay không trước khi thực hiện thao tác
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
