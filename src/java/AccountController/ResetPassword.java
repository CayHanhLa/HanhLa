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
                int SessionTimeOut = Integer.parseInt(RawseSsionTime);

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
