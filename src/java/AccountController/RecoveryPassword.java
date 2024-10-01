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
        long currentTime = System.currentTimeMillis() / 1000; // lay time hien tai (tinh bang giay)

        Long expiredTime = (Long) session.getAttribute("ExpiredTime"); // lay expiredTime tu session

        // lay session time out cau hinh qua the context-param trong web.xml
        String RawSessionTime = getServletContext().getInitParameter("sessiontimeout");
        long sessionTimeout = Long.parseLong(RawSessionTime);

        // kiem tra xem verify link da het han hay chua
        if (expiredTime == null || currentTime - expiredTime > sessionTimeout) {
            // da het han
            session.invalidate(); //xoa session
            // thong bao        
            request.setAttribute("FailtoRecovery", "Verification link has expired. Please request a new link.");
            request.getRequestDispatcher("recoverypassword.jsp").forward(request, response);
        }

        // kiem tra xem link da duoc su dung chua
        if (session.getAttribute("resetemail") == null) {
            // da dung -> thong bao
            request.setAttribute("FailtoRecovery", "Verification link has expired. Please request a new link.");
            request.getRequestDispatcher("recoverypasswrod.jsp.jsp").forward(request, response);

        } else {

            // chua dung
            DaoAccount d = new DaoAccount();

            String resetemail = (String) session.getAttribute("resetemail"); // lay email tu session
            String rawnewpassword = request.getParameter("newpassword"); // lay password tu form

            String password = BCrypt.hashpw(rawnewpassword, BCrypt.gensalt()); // bam password

            d.resetPasswordByEmail(resetemail, password); // doi password
            session.invalidate(); // xoa session de link chi xai duoc 1 lan

            // thong bao
            request.setAttribute("loginFailedMessage", "Your password has been successfully changed!. You can log in using your new password now.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
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
