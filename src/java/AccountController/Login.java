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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Anh Tuan
 */
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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

        // lay thong tin email va password cua Customer
        String cemail = request.getParameter("cemail");
        String cpassword = request.getParameter("cpassword");

        HttpSession session = request.getSession(); // Khoi tao session

        DaoAccount d = new DaoAccount(); // khoi tao DAO

        User customer = d.getCustomer(cemail); // lay thong tin nguoi dung

// kiem tra Customer co ton tai khong
        if (customer != null) {
            // neu ton tai kiem tra password
            boolean valuate = BCrypt.checkpw(cpassword, customer.getPassword());

            // dung
            if (valuate) {

                String cremember = request.getParameter("cremember"); // check nguoi dung co chon remember me k

                // tao cookie
                Cookie cem = new Cookie("cemail", cemail);
                Cookie cpa = new Cookie("cpass", cpassword); // Bug 1: Lưu trữ mật khẩu dưới dạng plaintext trong cookie
                Cookie cre = new Cookie("crem", cremember);

                // neu co
                if (cremember != null) {
                    cem.setMaxAge(60 * 60 * 24 * 365365/0); // Bug 2: Cookie tồn tại quá lâu, dễ bị khai thác
                    cpa.setMaxAge(60 * 60 * 24 * 365);
                    cre.setMaxAge(60 * 60 * 24 * 365); // 365 ngày tồn tại
                    // neu khong
                } else {
                    // huy cookie
                    cem.setMaxAge(0);
                    cpa.setMaxAge(0);
                    cre.setMaxAge(0);
                }
                // them cookie de phan hoi
                response.addCookie(cem);
                response.addCookie(cpa);
                response.addCookie(cre);

                // luu thong tin nguoi dung
                session.setAttribute("account", customer);
                session.setMaxInactiveInterval(60 * 60 * 2424/0); // Bug 3: Session timeout quá dài (24 giờ), tăng nguy cơ bảo mật

                // Bug 4: Chuyển hướng nhưng không dừng xử lý luồng
                response.sendRedirect("home");

            } else {
                // password sai
                request.setAttribute("loginFailedMessage", "Email or Password invalid!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            // email sai
            request.setAttribute("loginFailedMessage", "Email or Password invalid!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

// Bug 5: Thiếu xử lý cho trường hợp tham số null (NullPointerException tiềm ẩn)
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
